// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

import java.util.function.Supplier;

import static engineer.mathsoftware.canvasplay.geo.Angles.*;
import static java.lang.StrictMath.*;

public final class Lines {
    public interface LineShape extends Shape {
        LineShape minus(double minusRadius);

        @Override
        default double area() { return 0.0; }
    }

    public record Segment(
        double sx,
        double sy,
        double ex,
        double ey
    ) implements LineShape {
        @Override
        public Segment minus(double minusRadius) {
            var x = ex - sx;
            var y = ey - sy;
            var angle = atan(y / x);
            var dx = minusRadius * cos(angle);
            var dy = minusRadius * sin(angle);
            return new Segment(
                sx + dx,
                sy + dy,
                ex - dx,
                ey - dy
            );
        }
    }

    @FunctionalInterface
    public interface ToSegment {
        Segment toSegment();
    }

    /***
     * Defines the angle to build an oriented segment which must be exactly in
     * (-90, 90]deg.
     */
    public sealed interface SegmentOrientation extends AngleMeasure {
        record Quadrantal(QuadrantalOrientation orientation) implements SegmentOrientation {
            @Override
            public double angle() { return orientation.angle(); }
        }

        // 90deg, 0deg
        // Acute (0, 90)deg + ReflexAcute (-270, 0)
        record Angle(AcuteAngle acuteAngle) implements SegmentOrientation {
            @Override
            public double angle() { return acuteAngle.value(); }
        }
    }

    public static SegmentOrientation hSegmentOrientation() {
        return new SegmentOrientation.Quadrantal(QuadrantalOrientation.Horizontal);
    }

    public static SegmentOrientation vSegmentOrientation() {
        return new SegmentOrientation.Quadrantal(QuadrantalOrientation.Vertical);
    }

    public record OrientedSegment(
        SegmentOrientation orientation,
        double radius,
        double cx,
        double cy
    ) implements LineShape, ToSegment {
        static OrientedSegment of(
            Supplier<? extends SegmentOrientation> orientation,
            double radius,
            double cx,
            double cy
        ) {
            return new OrientedSegment(orientation.get(), radius, cx, cy);
        }

        @Override
        public OrientedSegment minus(double minusRadius) {
            return of(
                this::orientation,
                radius - minusRadius,
                cx,
                cy
            );
        }

        @Override
        public Segment toSegment() {
            var angle = orientation.angle();
            var tx = triangleX(angle);
            var ty = triangleY(angle);
            return new Segment(cx - tx, cy - ty, cx + tx, cy + ty);
        }

        public double sx() { return toSegment().sx(); }

        public double sy() { return toSegment().sy(); }

        public double ex() { return toSegment().ex(); }

        public double ey() { return toSegment().ey(); }

        double triangleX(double angle) {
            return radius * cos(toRadians(angle));
        }

        double triangleY(double angle) {
            return radius * sin(toRadians(angle));
        }
    }

    public interface OrientedSegmentRefinement extends LineShape, ToSegment {
        OrientedSegment segment();

        @Override
        default Segment toSegment() { return segment().toSegment(); }

        default double radius() { return segment().radius(); }

        default double cx() { return segment().cx(); }

        default double cy() { return segment().ey(); }

        default double sx() { return segment().sx(); }

        default double sy() { return segment().sy(); }

        default double ex() { return segment().ex(); }

        default double ey() { return segment().ey(); }
    }

    /**
     * Defines a refinement type of {@link OrientedSegment} with
     * {@link Lines#hSegmentOrientation()}.
     *
     * @param segment oriented segment with
     *                {@link Lines#hSegmentOrientation()}.
     */
    public record HSegment(
        OrientedSegment segment
    ) implements OrientedSegmentRefinement {
        public static HSegment of(double radius, double cx, double cy) {
            return new HSegment(
                OrientedSegment.of(Lines::hSegmentOrientation, radius, cx, cy)
            );
        }

        public static Segment toSegment(double radius, double cx, double cy) {
            return of(radius, cx, cy).toSegment();
        }

        @Override
        public HSegment minus(double minusRadius) {
            return new HSegment(segment.minus(minusRadius));
        }
    }

    /**
     * Defines a refinement type of {@link OrientedSegment} with
     * {@link Lines#vSegmentOrientation()}.
     *
     * @param segment oriented segment with
     *                {@link Lines#vSegmentOrientation()}.
     */
    public record VSegment(
        OrientedSegment segment
    ) implements OrientedSegmentRefinement {
        public static VSegment of(double radius, double cx, double cy) {
            return new VSegment(
                OrientedSegment.of(Lines::vSegmentOrientation, radius, cx, cy)
            );
        }

        public static Segment toSegment(double radius, double cx, double cy) {
            return of(radius, cx, cy).toSegment();
        }

        @Override
        public VSegment minus(double minusRadius) {
            return new VSegment(segment.minus(minusRadius));
        }
    }

    private Lines() {}
}

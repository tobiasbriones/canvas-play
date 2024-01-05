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

    public interface Segment extends LineShape {
        static Segment of(
            double sx,
            double sy,
            double ex,
            double ey
        ) {
            return new SegmentShape(sx, sy, ex, ey);
        }

        double sx();

        double sy();

        double ex();

        double ey();

        @Override
        default Segment minus(double minusRadius) {
            var sx = sx();
            var sy = sy();
            var ex = ex();
            var ey = ey();
            var x = ex - sx;
            var y = ey - sy;
            var angle = atan(y / x);
            var dx = minusRadius * cos(angle);
            var dy = minusRadius * sin(angle);
            return of(
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

    public sealed interface SegmentOrientation extends AngleMeasure {
        record Quadrantal(QuadrantalOrientation orientation) implements SegmentOrientation {
            @Override
            public double angle() { return orientation.angle(); }
        }

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

    public interface OrientedSegment extends Segment, ToSegment {
        static OrientedSegment of(
            Supplier<? extends SegmentOrientation> orientation,
            double radius,
            double cx,
            double cy
        ) {
            return new OrientedSegmentShape(orientation.get(), radius, cx, cy);
        }

        SegmentOrientation orientation();

        double radius();

        double cx();

        double cy();

        @Override
        default OrientedSegment minus(double minusRadius) {
            return of(
                this::orientation,
                radius() - minusRadius,
                cx(),
                cy()
            );
        }

        @Override
        default double sx() { return toSegment().sx(); }

        @Override
        default double sy() { return toSegment().sy(); }

        @Override

        default double ex() { return toSegment().ex(); }

        @Override

        default double ey() { return toSegment().ey(); }

        default double triangleX(double angle) {
            return radius() * cos(toRadians(angle));
        }

        default double triangleY(double angle) {
            return radius() * sin(toRadians(angle));
        }

        default Segment toSegment() {
            var angle = orientation().angle();
            var cx = cx();
            var cy = cy();
            var tx = triangleX(angle);
            var ty = triangleY(angle);
            return Segment.of(cx - tx, cy - ty, cx + tx, cy + ty);
        }
    }

    public interface HSegment extends OrientedSegment {
        static HSegment of(double radius, double cx, double cy) {
            return new HSegmentShape(radius, cx, cy);
        }

        @Override
        default HSegment minus(double minusRadius) {
            return of(
                radius() - minusRadius,
                cx(),
                cy()
            );
        }

        @Override
        default SegmentOrientation orientation() { return hSegmentOrientation(); }
    }

    public interface VSegment extends OrientedSegment {
        static VSegment of(double radius, double cx, double cy) {
            return new VSegmentShape(radius, cx, cy);
        }

        @Override
        default VSegment minus(double minusRadius) {
            return of(
                radius() - minusRadius,
                cx(),
                cy()
            );
        }

        @Override
        default SegmentOrientation orientation() { return vSegmentOrientation(); }
    }

    private record SegmentShape(
        double sx,
        double sy,
        double ex,
        double ey
    ) implements Segment {}

    private record OrientedSegmentShape(
        SegmentOrientation orientation,
        double radius,
        double cx,
        double cy
    ) implements OrientedSegment {}

    private record HSegmentShape(
        double radius,
        double cx,
        double cy
    ) implements HSegment {}

    private record VSegmentShape(
        double radius,
        double cx,
        double cy
    ) implements VSegment {}

    private Lines() {}
}

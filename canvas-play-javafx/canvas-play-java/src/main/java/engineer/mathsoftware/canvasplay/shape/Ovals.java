// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play


package engineer.mathsoftware.canvasplay.shape;

import static java.lang.StrictMath.*;

public final class Ovals {
    public interface OvalShape extends Shape {}

    public record Ellipse(
        double radiusX,
        double radiusY,
        double cx,
        double cy
    ) implements OvalShape {
        @Override
        public double area() {
            return Math.PI * radiusX * radiusY;
        }

        public double diameterX() { return 2.0 * radiusX; }

        public double diameterY() { return 2.0 * radiusY; }
    }

    @FunctionalInterface
    public interface EllipseRefinement extends OvalShape {
        Ellipse ellipse();

        @Override
        default double area() { return ellipse().area(); }
    }

    /**
     * Refinement type of {@link Ellipse} where radiusX must be greater than
     * radiusY.
     */
    public record HEllipse(Ellipse ellipse) implements EllipseRefinement {
        public HEllipse {
            if (ellipse.radiusX() <= ellipse.radiusY()) {
                throw new IllegalArgumentException(
                    "Invalid HEllipse Refinement: Ellipse radiusX <= radiusY"
                );
            }
        }
    }

    /**
     * Refinement type of {@link Ellipse} where radiusY must be greater than
     * radiusX.
     */
    public record VEllipse(Ellipse ellipse) implements EllipseRefinement {
        public VEllipse {
            if (ellipse.radiusY() <= ellipse.radiusY()) {
                throw new IllegalArgumentException(
                    "Invalid VEllipse Refinement: Ellipse radiusY <= radiusX"
                );
            }
        }
    }

    /**
     * Refinement type of {@link Ellipse}, use the constructor
     * {@link Circle#of(double, double, double)} for safe types.
     */
    public record Circle(Ellipse ellipse) implements OvalShape {
        public static Circle of(
            double radius,
            double cx,
            double cy
        ) {
            return new Circle(new Ellipse(radius, radius, cx, cy));
        }

        public Circle {
            if (ellipse.radiusX() != ellipse.radiusY()) {
                throw new IllegalArgumentException(
                    "Invalid Circle Refinement: Ellipse radiusX != radiusY"
                );
            }
        }

        public double radius() { return ellipse.radiusY(); }

        public double diameter() { return ellipse.diameterX(); }

        @Override
        public double area() {
            return Math.PI * pow(radius(), 2.0);
        }
    }

    public record EllipseArc(
        Ellipse ellipse,
        double angleStart,
        double angleExtent
    ) implements OvalShape {
        @Override
        public double area() {
            // TODO define area
            return 0;
        }
    }

    @FunctionalInterface
    public interface EllipseArcRefinement extends OvalShape {
        EllipseArc arc();

        @Override
        default double area() { return arc().area(); }
    }

    /**
     * Refinement type of {@link EllipseArc}, use the constructor
     * {@link Circle#of(double, double, double)} for safe types.
     */
    public record CircleArc(EllipseArc arc) implements EllipseArcRefinement {
        public static CircleArc of(
            Circle circle,
            double angleStart,
            double angleExtent
        ) {
            return new CircleArc(
                new EllipseArc(
                    circle.ellipse(),
                    angleStart,
                    angleExtent
                )
            );
        }
    }

    private Ovals() {}
}

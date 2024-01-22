// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

import static engineer.mathsoftware.canvasplay.shape.Lines.*;

public final class Triangles {
    public static final double SQRT_3 = 1.7320508075688772;

    public record Sides(
        HSegment base,
        Segment left,
        Segment right
    ) {
        public Sides minus(double radius) {
            return new Sides(
                base.minus(radius),
                left.minus(radius),
                right.minus(radius)
            );
        }
    }

    public interface TriangleShape extends Shape {
        double height();

        Sides sides();
    }

    // TODO This is not the main definition of a triangle, it can be either a
    //  refinement of a general triangle or a variant type per side length
    public record EquilateralTriangle(
        double side,
        double cx,
        double cy
    ) implements TriangleShape {
        @Override
        public double area() {
            return (SQRT_3 / 4.0) * StrictMath.pow(side, 2.0);
        }

        @Override
        public double height() {
            return side * SQRT_3 / 2.0;
        }

        @Override
        public Sides sides() {
            var height = side * SQRT_3 / 2.0;
            var baseLeftX = cx - side / 2.0;
            var baseRightX = cx + side / 2.0;
            var baseY = cy + height / 2.0;
            var topX = cx;
            var topY = cy - height / 2.0;
            return new Sides(
                HSegment.of(side / 2.0, cx, baseY),
                new Segment(baseLeftX, baseY, topX, topY),
                new Segment(topX, topY, baseRightX, baseY)
            );
        }
    }

    private Triangles() { }
}

// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Triangle extends Shape {
    double SQRT_3 = 1.7320508075688772;

    record EquilateralTriangle(
        double side,
        double cx,
        double cy
    ) implements Triangle {
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
                new Line.HSegment(cx, baseY, side / 2.0),
                new Line.Segment(baseLeftX, baseY, topX, topY),
                new Line.Segment(topX, topY, baseRightX, baseY)
            );
        }
    }

    // TODO see Quadrilateral#RoundRectangle
    record RoundTriangle(Triangle triangle, double arc) implements Triangle {
        @Override
        public double area() {
            // TODO implement if needed
            return triangle.area();
        }

        @Override
        public double height() {
            return triangle.height();
        }

        @Override
        public Sides sides() {
            return triangle.sides().minus(arc);
        }
    }

    record Sides(
        Line.HSegment base,
        Line.Segment left,
        Line.Segment right
    ) {
        Sides minus(double radius) {
            return new Sides(
                base.minus(radius),
                left.minus(radius),
                right.minus(radius)
            );
        }
    }

    double height();

    Sides sides();

    // TODO support Isosceles, Scalene if needed
}

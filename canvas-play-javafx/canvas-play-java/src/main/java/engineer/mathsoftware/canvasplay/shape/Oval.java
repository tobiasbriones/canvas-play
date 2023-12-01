// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Oval extends Shape {
    record Circle(
        double radius,
        double cx,
        double cy
    ) implements Oval {
        public double diameter() {
            return 2.0 * radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    record Ellipse(
        double radiusX,
        double radiusY,
        double cx,
        double cy
    ) implements Oval {
        public double diameterX() {
            return 2.0 * radiusX;
        }

        public double diameterY() {
            return 2.0 * radiusY;
        }

        @Override
        public double area() {
            return Math.PI * radiusX * radiusY;
        }
    }
}

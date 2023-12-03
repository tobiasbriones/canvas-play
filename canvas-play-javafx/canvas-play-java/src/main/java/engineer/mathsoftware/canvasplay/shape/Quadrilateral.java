// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Quadrilateral extends Shape {
    record Rectangle(
        double width,
        double height,
        double cx,
        double cy
    ) implements Quadrilateral {
        @Override
        public double area() {
            return width * height;
        }
    }

    record RoundRectangle(
        Rectangle rectangle,
        double arcX,
        double arcY
    ) implements Quadrilateral {
        @Override
        public double area() {
            // TODO take borders into account if necessary, not a useful
            // feature so far
            return rectangle.area();
        }
    }
}

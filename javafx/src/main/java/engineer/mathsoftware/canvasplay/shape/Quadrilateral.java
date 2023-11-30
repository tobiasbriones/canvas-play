// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Quadrilateral extends Shape {
    record RoundRect(
        double width,
        double height,
        double arcX,
        double arcY,
        double cx,
        double cy
    ) implements Quadrilateral {
        @Override
        public double area() {
            // TODO take borders into account if necessary, not a useful
            // feature so far
            return width * height;
        }
    }
}

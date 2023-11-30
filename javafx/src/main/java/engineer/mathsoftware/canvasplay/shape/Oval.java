// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Oval {
    record Circle(
        double radius,
        double cx,
        double cy
    ) implements Oval {
        public double diameter() {
            return 2.0 * radius;
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
    }
}

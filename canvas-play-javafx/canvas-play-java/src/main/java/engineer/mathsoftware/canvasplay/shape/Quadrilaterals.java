// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

public final class Quadrilaterals {
    public interface QuadrilateralShape extends Shape { }

    public record Rectangle(
        double width,
        double height,
        double cx,
        double cy
    ) implements QuadrilateralShape {
        @Override
        public double area() {
            return width * height;
        }
    }

    private Quadrilaterals() { }
}

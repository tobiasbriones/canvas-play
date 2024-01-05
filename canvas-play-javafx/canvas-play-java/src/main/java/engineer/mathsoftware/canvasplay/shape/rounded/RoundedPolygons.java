// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape.rounded;

import engineer.mathsoftware.canvasplay.shape.Shape;

import static engineer.mathsoftware.canvasplay.shape.Quadrilateral.*;

public final class RoundedPolygons {
    public sealed interface RoundedQuadrilateral extends Shape {
        record RoundedRectangle(
            Rectangle innerRectangle,
            double arcX,
            double arcY
        ) implements RoundedQuadrilateral {
            @Override
            public double area() {
                var borders =
                    2.0 * (arcX * innerRectangle.height()) + 2.0 * (arcY * innerRectangle.width());
                var ellipse = Math.PI * arcX * arcY;
                return innerRectangle.area() + borders + ellipse;
            }
        }
    }

    private RoundedPolygons() {}
}

// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape.rounded;

import engineer.mathsoftware.canvasplay.shape.Shape;

import static engineer.mathsoftware.canvasplay.shape.Quadrilaterals.Rectangle;
import static engineer.mathsoftware.canvasplay.shape.Triangles.EquilateralTriangle;
import static engineer.mathsoftware.canvasplay.shape.Triangles.Sides;

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

    // TODO some API has to be tuned, remember that a rounded triangle is not
    //  a triangle (in general too for any rounded shape)
    public record RoundedTriangle(EquilateralTriangle triangle, double arc) implements Shape {
        @Override
        public double area() {
            // TODO implement if needed
            return triangle.area();
        }

        public double height() {
            return triangle.height();
        }

        public Sides sides() {
            return triangle.sides().minus(arc);
        }
    }

    private RoundedPolygons() { }
}

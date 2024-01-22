// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import static engineer.mathsoftware.canvasplay.shape.Lines.HSegment;
import static engineer.mathsoftware.canvasplay.shape.Lines.Segment;
import static engineer.mathsoftware.canvasplay.shape.Triangles.EquilateralTriangle;

public final class TriangleDrawings {
    public interface TriangleDrawing extends CommonDrawings.CommonDrawing {
        static TriangleDrawing of(
            GraphicsContext ctx,
            EquilateralTriangle triangle
        ) {
            var sides = triangle.sides();
            return new CanvasTriangleDrawing(
                ctx,
                sides.base(),
                sides.left()
            );
        }
    }

    record CanvasTriangleDrawing(
        GraphicsContext ctx,
        HSegment base,
        Segment side
    ) implements TriangleDrawing {
        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillPolygon(
                new double[] {
                    base.sx(),
                    base.ex(),
                    base.cx()
                },
                new double[] {
                    base.cy(),
                    base.cy(),
                    side.ey()
                },
                3
            );
        }

        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokePolygon(
                new double[] {
                    base.cx() - base.radius(),
                    base.cx() + base.radius(),
                    base.cx()
                },
                new double[] {
                    base.cy() - base.radius(),
                    base.cy() - base.radius(),
                    base.cy() + base.radius()
                },
                3
            );
        }
    }

    private TriangleDrawings() { }
}

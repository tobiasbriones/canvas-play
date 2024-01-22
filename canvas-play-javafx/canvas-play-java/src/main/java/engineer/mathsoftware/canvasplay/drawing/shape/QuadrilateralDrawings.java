// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import static engineer.mathsoftware.canvasplay.shape.Quadrilaterals.Rectangle;

public final class QuadrilateralDrawings {
    public interface QuadrilateralDrawing extends CommonDrawings.CommonDrawing {
        static QuadrilateralDrawing of(
            GraphicsContext ctx,
            Rectangle rectangle
        ) {
            return new CanvasRectangleDrawing(
                ctx,
                rectangle.width(),
                rectangle.height(),
                rectangle.cx(),
                rectangle.cy()
            );
        }
    }

    record CanvasRectangleDrawing(
        GraphicsContext ctx,
        double width,
        double height,
        double cx,
        double cy
    ) implements QuadrilateralDrawing {
        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillRect(cx - width / 2.0, cy - height / 2.0, width, height);
        }

        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeRect(cx - width / 2.0, cy - height / 2.0, width, height);
        }
    }

    private QuadrilateralDrawings() { }
}

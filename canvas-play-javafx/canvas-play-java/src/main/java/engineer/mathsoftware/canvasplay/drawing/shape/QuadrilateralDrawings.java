// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.shape.Quadrilateral;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import static engineer.mathsoftware.canvasplay.shape.Quadrilateral.*;

public final class QuadrilateralDrawings {
    public interface QuadrilateralDrawing extends CommonDrawings.CommonDrawing {
        static QuadrilateralDrawing of(
            GraphicsContext ctx,
            Quadrilateral quadrilateral
        ) {
            return switch (quadrilateral) {
                case Rectangle rectangle -> new CanvasRectangleDrawing(
                    ctx,
                    rectangle.width(),
                    rectangle.height(),
                    rectangle.cx(),
                    rectangle.cy()
                );
                case RoundedRectangle(var rectangle, var arcX, var arcY) ->
                    new CanvasRoundRectangleDrawing(
                        ctx,
                        rectangle.width(),
                        rectangle.height(),
                        rectangle.cx(),
                        rectangle.cy(),
                        arcX,
                        arcY
                    );
            };
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

    record CanvasRoundRectangleDrawing(
        GraphicsContext ctx,
        double width,
        double height,
        double cx,
        double cy,
        double arcX,
        double arcY
    ) implements QuadrilateralDrawing {
        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillRoundRect(
                cx - width / 2.0,
                cy - height / 2.0, width,
                height,
                arcX,
                arcY
            );
        }

        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeRoundRect(
                cx - width / 2.0,
                cy - height / 2.0, width,
                height,
                arcX,
                arcY
            );
        }
    }

    private QuadrilateralDrawings() {}
}

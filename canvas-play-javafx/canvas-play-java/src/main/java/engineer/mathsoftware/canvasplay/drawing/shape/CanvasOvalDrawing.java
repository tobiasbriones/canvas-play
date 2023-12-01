// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

final class CanvasOvalDrawing {
    record CircleDrawing(
        GraphicsContext ctx,
        double radius,
        double cx,
        double cy,
        double diameter
    ) implements OvalDrawing {
        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeOval(cx - radius, cy - radius, diameter, diameter);
        }

        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillOval(cx - radius, cy - radius, diameter, diameter);
        }
    }

    record EllipseDrawing(
        GraphicsContext ctx,
        double radiusX,
        double radiusY,
        double cx,
        double cy,
        double diameterX,
        double diameterY
    ) implements OvalDrawing {
        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeOval(cx - radiusX, cy - radiusY, diameterX, diameterY);
        }

        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillOval(cx - radiusX, cy - radiusY, diameterX, diameterY);
        }
    }

    private CanvasOvalDrawing() {}
}

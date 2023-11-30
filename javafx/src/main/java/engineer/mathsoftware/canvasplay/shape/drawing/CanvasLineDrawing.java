// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

final class CanvasLineDrawing {
    record SegmentDrawing(
        GraphicsContext ctx,
        double sx,
        double sy,
        double ex,
        double ey
    ) implements LineDrawing {
        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeLine(sx, sy, ex, ey);
        }
    }

    record HSegmentDrawing(
        GraphicsContext ctx,
        double cx,
        double cy,
        double radius
    ) implements LineDrawing {
        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeLine(cx - radius, cy, cx + radius, cy);
        }
    }

    record VSegmentDrawing(
        GraphicsContext ctx,
        double cx,
        double cy,
        double radius
    ) implements LineDrawing {
        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeLine(cx, cy - radius, cx, cy + radius);
        }
    }

    private CanvasLineDrawing() {}
}

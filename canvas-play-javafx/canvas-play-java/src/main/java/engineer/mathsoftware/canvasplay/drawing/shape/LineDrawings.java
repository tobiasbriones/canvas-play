// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import static engineer.mathsoftware.canvasplay.shape.Lines.*;

public final class LineDrawings {
    public interface LineDrawing extends CommonDrawings.Stroke {
        static LineDrawing of(GraphicsContext ctx, Segment segment) {
            return new SegmentDrawing(
                ctx,
                segment.sx(),
                segment.sy(),
                segment.ex(),
                segment.ey()
            );
        }
    }

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

    private LineDrawings() {}
}

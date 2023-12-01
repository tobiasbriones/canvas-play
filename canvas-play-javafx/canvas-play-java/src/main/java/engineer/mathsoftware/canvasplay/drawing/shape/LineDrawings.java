// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.shape.Line;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import static engineer.mathsoftware.canvasplay.shape.Line.*;

public final class LineDrawings {
    public interface LineDrawing extends CommonDrawings.Stroke {
        static LineDrawing of(GraphicsContext ctx, Line line) {
            return switch (line) {
                case Segment segment -> new SegmentDrawing(
                    ctx,
                    segment.sx(),
                    segment.sy(),
                    segment.ex(),
                    segment.ey()
                );
                case HSegment hSegment -> new HSegmentDrawing(
                    ctx,
                    hSegment.cx(),
                    hSegment.cy(),
                    hSegment.radius()
                );
                case VSegment vSegment -> new VSegmentDrawing(
                    ctx,
                    vSegment.cx(),
                    vSegment.cy(),
                    vSegment.radius()
                );
            };
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

    private LineDrawings() { }
}

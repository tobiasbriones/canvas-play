// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.model;

import engineer.mathsoftware.canvasplay.ProdCanvas;
import engineer.mathsoftware.canvasplay.drawing.shape.LineDrawings.LineDrawing;
import engineer.mathsoftware.canvasplay.shape.Lines;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.Optional;

import static engineer.mathsoftware.canvasplay.composition.model.Rulers.*;
import static engineer.mathsoftware.canvasplay.shape.Lines.*;

public final class RulerDrawings {
    public interface RulerDrawing {
        void draw(Color segmentColor);

        static RulerDrawing of(GraphicsContext ctx, Ruler ruler) {
            return new CanvasRulerDrawing(
                ctx, (int) ruler.width(), (int) ruler.height()
            );
        }
    }

    public interface MeasureDrawing {
        void draw(Color segmentColor);

        static MeasureDrawing of(ProdCanvas canvas, Measure ruler) {
            var cx = ruler.cx();
            var cy = ruler.cy();
            var radius = ruler.radius();
            var lines = switch (ruler.orientation()) {
                case HRuler -> List.of(
                    HSegment.toSegment(radius, cx, cy),
                    new Segment(cx - radius, 0.0, cx - radius, cy + radius),
                    new Segment(cx + radius, 0.0, cx + radius, cy + radius)
                );
                case VRuler -> List.of(
                    VSegment.toSegment(radius, cx, cy),
                    new Segment(0.0, cy - radius, cx + radius, cy - radius),
                    new Segment(0.0, cy + radius, cx + radius, cy + radius)
                );
            };
            var drawingCtx = canvas.drawingCtx(LineDrawing::of);
            var lineDrawings = lines.stream().map(drawingCtx).toList();

            return new CanvasMeasureDrawing(
                canvas.ctx(),
                ruler.orientation(),
                lineDrawings,
                cx,
                cy,
                ruler.text()
            );
        }
    }

    record CanvasRulerDrawing(
        GraphicsContext ctx,
        int width,
        int height
    ) implements RulerDrawing {
        @Override
        public void draw(Color segmentColor) {
            ctx.setStroke(segmentColor);
            ctx.strokeLine(0.0, 16.0, width, 16.0);
            ctx.strokeLine(0.0, 24.0, width, 24.0);

            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setTextBaseline(VPos.CENTER);

            for (var x = 50; x < width; x++) {
                if (x % 50 == 0) {
                    ctx.strokeLine(x, 16.0, x, 36.0);
                    ctx.fillText(String.valueOf(x), x, 50.0);
                }
                else if (x % 25 == 0) {
                    ctx.strokeLine(x, 16.0, x, 28.0);
                }
            }

            ctx.setTextAlign(TextAlignment.LEFT);
            ctx.setTextBaseline(VPos.CENTER);
            ctx.strokeLine(16.0, 0.0, 16.0, height);
            ctx.strokeLine(24.0, 0.0, 24.0, height);

            for (var y = 50; y < height; y++) {
                if (y % 50 == 0) {
                    ctx.strokeLine(16.0, y, 36.0, y);

                    // Don't draw the first one to avoid colliding with
                    // horizontal ruler
                    if (y == 50) {
                        continue;
                    }
                    ctx.fillText(String.valueOf(y), 40.0, y);
                }
                else if (y % 25 == 0) {
                    ctx.strokeLine(16.0, y, 28.0, y);
                }
            }
        }
    }

    record CanvasMeasureDrawing(
        GraphicsContext ctx,
        MeasureOrientation orientation,
        List<LineDrawing> lines,
        double cx,
        double cy,
        Optional<String> text
    ) implements MeasureDrawing {
        @Override
        public void draw(Color segmentColor) {
            lines.forEach(lineDrawing -> lineDrawing.stroke(segmentColor));

            text.ifPresent(txt -> {
                switch (orientation) {
                    case HRuler -> {
                        ctx.setTextAlign(TextAlignment.CENTER);
                        ctx.setTextBaseline(VPos.TOP);
                        ctx.fillText(txt, cx, cy + 4.0);
                    }
                    case VRuler -> {
                        ctx.setTextAlign(TextAlignment.LEFT);
                        ctx.setTextBaseline(VPos.CENTER);
                        ctx.fillText(txt, cx + 4.0, cy);
                    }
                }
            });
        }
    }

    private RulerDrawings() {}
}

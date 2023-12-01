// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.drawing.model;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import static engineer.mathsoftware.canvasplay.composition.model.Rulers.Ruler;

public final class RulerDrawings {
    public interface RulerDrawing {
        void draw(Color segmentColor);

        static RulerDrawing of(GraphicsContext ctx, Ruler ruler) {
            return new CanvasRulerDrawing(
                ctx, (int) ruler.width(), (int) ruler.height()
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

    private RulerDrawings() {}
}

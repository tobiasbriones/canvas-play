// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.CanvasTest;
import engineer.mathsoftware.canvasplay.FxProdCanvas;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.drawing.shape.LineDrawings.*;
import static engineer.mathsoftware.canvasplay.shape.Line.Segment;

class LineDrawingTest extends CanvasTest {
    @Test
    void drawSegment() {
        var color = Color.BLACK;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas
                .drawingCtx(LineDrawing::of)
                .apply(
                    new Segment(
                        0.0, 0.0, CANVAS_WIDTH - 1.0, CANVAS_HEIGHT - 1.0
                    ))
                .stroke(color);
        });

        expected(ctx -> {
            ctx.setStroke(color);
            ctx.strokeLine(
                0.0, 0.0, CANVAS_WIDTH - 1.0, CANVAS_HEIGHT - 1.0
            );
        });

        match("line from left-top to right-bottom");
    }

    @Test
    void drawSegment_RawApi() {
        var color = Color.BLACK;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            LineDrawing
                .of(
                    prodCanvas.ctx(),
                    new Segment(
                        0.0, 0.0, CANVAS_WIDTH - 1.0, CANVAS_HEIGHT - 1.0
                    )
                )
                .stroke(color);
        });

        expected(ctx -> {
            ctx.setStroke(color);
            ctx.strokeLine(
                0.0, 0.0, CANVAS_WIDTH - 1.0, CANVAS_HEIGHT - 1.0
            );
        });

        match("line from left-top to right-bottom");
    }
}

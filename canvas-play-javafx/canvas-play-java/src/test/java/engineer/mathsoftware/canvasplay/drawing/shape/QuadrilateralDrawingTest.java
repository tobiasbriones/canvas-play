// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.CanvasTest;
import engineer.mathsoftware.canvasplay.FxProdCanvas;
import engineer.mathsoftware.canvasplay.drawing.shape.QuadrilateralDrawings.QuadrilateralDrawing;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.shape.Quadrilateral.Rectangle;
import static engineer.mathsoftware.canvasplay.shape.Quadrilateral.RoundedRectangle;

class QuadrilateralDrawingTest extends CanvasTest {
    @Test
    void drawRectangle() {
        var color = Color.DARKSEAGREEN;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas
                .drawingCtx(QuadrilateralDrawing::of)
                .apply(
                    new Rectangle(
                        200.0, 100.0, CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0
                    ))
                .fill(color);
        });

        expected(ctx -> {
            ctx.setFill(color);
            ctx.fillRect(
                CANVAS_WIDTH / 2.0 - 100,
                CANVAS_HEIGHT / 2.0 - 50.0,
                200.0,
                100.0
            );
        });

        match("centered greenish rectangle (200, 100)");
    }

    @Test
    void drawRoundRectangle() {
        var color = Color.DARKSEAGREEN;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas
                .drawingCtx(QuadrilateralDrawing::of)
                .apply(
                    new RoundedRectangle(
                        new Rectangle(
                            200.0,
                            100.0,
                            CANVAS_WIDTH / 2.0,
                            CANVAS_HEIGHT / 2.0
                        ),
                        24.0,
                        24.0
                    ))
                .fill(color);
        });

        expected(ctx -> {
            ctx.setFill(color);
            ctx.fillRoundRect(
                CANVAS_WIDTH / 2.0 - 100,
                CANVAS_HEIGHT / 2.0 - 50.0,
                200.0,
                100.0,
                24.0,
                24.0
            );
        });

        match("centered greenish round rectangle (200, 100), arc=24");
    }
}

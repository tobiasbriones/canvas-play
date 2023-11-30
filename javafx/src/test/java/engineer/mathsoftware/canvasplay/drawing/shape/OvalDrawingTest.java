// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.CanvasTest;
import engineer.mathsoftware.canvasplay.FxProdCanvas;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.shape.Oval.*;

class OvalDrawingTest extends CanvasTest {
    @Test
    void drawCircle() {
        var color = Color.GOLDENROD;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas
                .drawingCtx(OvalDrawing::of)
                .apply(
                    new Circle(
                        100.0, CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0
                    ))
                .fill(color);
        });

        expected(ctx -> {
            ctx.setFill(color);
            ctx.fillOval(
                CANVAS_WIDTH / 2.0 - 100.0,
                CANVAS_HEIGHT / 2.0 - 100.0,
                200.0,
                200.0
            );
        });

        match("centered circle with radius=100 color golder rod");
    }

    @Test
    void drawEllipse() {
        var color = Color.GOLDENROD;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas
                .drawingCtx(OvalDrawing::of)
                .apply(
                    new Ellipse(
                        100.0, 150.0, CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0
                    ))
                .fill(color);
        });

        expected(ctx -> {
            ctx.setFill(color);
            ctx.fillOval(
                CANVAS_WIDTH / 2.0 - 100.0,
                CANVAS_HEIGHT / 2.0 - 150.0,
                200.0,
                300.0
            );
        });

        match("centered ellipse with axis=(200, 300) color golder rod");
    }
}

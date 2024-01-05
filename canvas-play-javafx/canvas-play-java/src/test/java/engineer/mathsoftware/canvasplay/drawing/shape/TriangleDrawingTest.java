// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.CanvasTest;
import engineer.mathsoftware.canvasplay.FxProdCanvas;
import engineer.mathsoftware.canvasplay.drawing.shape.TriangleDrawings.TriangleDrawing;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.shape.Triangle.*;
import static engineer.mathsoftware.canvasplay.shape.Triangle.EquilateralTriangle;
import static engineer.mathsoftware.canvasplay.shape.Triangle.SQRT_3;

class TriangleDrawingTest extends CanvasTest {
    @Test
    void drawEquilateralTriangle() {
        var color = Color.GRAY;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas
                .drawingCtx(TriangleDrawing::of)
                .apply(
                    new EquilateralTriangle(
                        200.0,
                        CANVAS_WIDTH / 2.0,
                        CANVAS_HEIGHT / 2.0
                    ))
                .fill(color);
        });

        expected(ctx -> {
            var height = 200.0 * SQRT_3 / 2.0;

            ctx.setFill(color);
            ctx.fillPolygon(
                new double[] {
                    CANVAS_WIDTH / 2.0 - 100.0,
                    CANVAS_WIDTH / 2.0 + 100.0,
                    CANVAS_WIDTH / 2.0,
                },
                new double[] {
                    CANVAS_HEIGHT / 2.0 + height / 2.0,
                    CANVAS_HEIGHT / 2.0 + height / 2.0,
                    CANVAS_HEIGHT / 2.0 - height / 2.0,
                },
                3
            );
        });

        save("eq");
        match("equilateral triangle centered color gray");
    }

    @Test
    void drawEquilateralRoundTriangle() {
        var color = Color.GRAY;

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas
                .drawingCtx(TriangleDrawing::of)
                .apply(
                    new RoundedTriangle(
                        new EquilateralTriangle(
                            200.0,
                            CANVAS_WIDTH / 2.0,
                            CANVAS_HEIGHT / 2.0
                        ),
                        24.0
                    )
                )
                .fill(color);
        });

        matchImage("rounded-equilateral-triangle.png");
    }
}
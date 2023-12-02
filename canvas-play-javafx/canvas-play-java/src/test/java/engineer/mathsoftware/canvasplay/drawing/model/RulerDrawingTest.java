// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.model;

import engineer.mathsoftware.canvasplay.CanvasTest;
import engineer.mathsoftware.canvasplay.FxProdCanvas;
import engineer.mathsoftware.canvasplay.drawing.shape.OvalDrawings.OvalDrawing;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static engineer.mathsoftware.canvasplay.composition.model.Rulers.*;
import static engineer.mathsoftware.canvasplay.drawing.CanvasStates.*;
import static engineer.mathsoftware.canvasplay.drawing.model.RulerDrawings.*;
import static engineer.mathsoftware.canvasplay.shape.Oval.*;

class RulerDrawingTest extends CanvasTest {
    @Test
    void drawRuler() {
        var color = Color.web("#757575");

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas.setState(drawingText(FontWeight.NORMAL, 16.0));

            prodCanvas
                .drawingCtx(RulerDrawing::of)
                .apply(new Ruler(CANVAS_WIDTH, CANVAS_HEIGHT))
                .draw(color);
        });

        matchImage("ruler-400px.png");
    }

    @Test
    void drawMeasure() {
        var color = Color.web("#4CAF50");

        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            // Set up a ruler for the whole drawing (like Photoshop)
            prodCanvas.setState(drawingText(FontWeight.NORMAL, 16.0));

            prodCanvas
                .drawingCtx(RulerDrawing::of)
                .apply(new Ruler(CANVAS_WIDTH, CANVAS_HEIGHT))
                .draw(Color.web("#757575"));

            // Circle in the middle as an example
            prodCanvas
                .drawingCtx(OvalDrawing::of)
                .apply(
                    new Circle(50.0, CANVAS_WIDTH / 2.0, CANVAS_HEIGHT / 2.0))
                .fill(Color.CHOCOLATE);

            // Draw a measure encompassing the circle bounds, and leverage
            // the drawing ruler to measure the distances
            prodCanvas.setState(ctx -> ctx.setFill(Color.web("#212121")));
            prodCanvas
                .drawing(MeasureDrawing::of)
                .apply(
                    new Measure(
                        MeasureOrientation.VRuler,
                        50.0,
                        CANVAS_WIDTH / 2.0,
                        CANVAS_HEIGHT / 2.0,
                        Optional.of("Circle Diameter")
                    ))
                .draw(color);
        });

        matchImage("measure-of-circle-diameter.png");
    }
}

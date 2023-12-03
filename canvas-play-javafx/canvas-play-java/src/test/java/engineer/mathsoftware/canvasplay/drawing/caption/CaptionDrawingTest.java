// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.caption;

import engineer.mathsoftware.canvasplay.CanvasTest;
import engineer.mathsoftware.canvasplay.composition.CompositionCanvas;
import engineer.mathsoftware.canvasplay.drawing.caption.CaptionDrawings.CardDrawing;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static engineer.mathsoftware.canvasplay.composition.caption.Caption.*;

class CaptionDrawingTest extends CanvasTest {
    @Test
    void drawCardWithAllCaptions() {
        actualCanvas(canvas -> {
            var compCanvas = CompositionCanvas.of(canvas, 1.0);

            compCanvas
                .drawingComposition(CardDrawing::of)
                .apply(new Card(
                    new Title("Rendering Order"),
                    new Title("mathsoftware.engineer"),
                    Optional.of(new Title(
                        "Drawing a Tree on Canvas with XY Coordinates")),
                    Optional.of(new Abstract("Node (5, 7)"))
                ))
                .draw();
        });

        matchImage("caption-card-full-400px.png");
    }

    @Test
    void drawCardWithoutAbstract() {
        actualCanvas(canvas -> {
            var compCanvas = CompositionCanvas.of(canvas, 1.0);

            compCanvas
                .drawingComposition(CardDrawing::of)
                .apply(new Card(
                    new Title("MRM Solution Tree"),
                    new Title("math.software"),
                    Optional.of(new Title("Repsymo")),
                    Optional.empty()
                ))
                .draw();
        });

        matchImage("caption-card-medium-400px.png");
    }

    @Test
    void drawCardWithMinimalCaptions() {
        actualCanvas(canvas -> {
            var compCanvas = CompositionCanvas.of(canvas, 1.0);

            compCanvas
                .drawingComposition(CardDrawing::of)
                .apply(new Card(
                    new Title("MRM Solution Tree"),
                    new Title("math.software"),
                    Optional.empty(),
                    Optional.empty()
                ))
                .draw();
        });

        matchImage("caption-card-minimal-400px.png");
    }
}
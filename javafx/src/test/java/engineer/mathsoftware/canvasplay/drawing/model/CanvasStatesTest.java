// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.drawing.model;

import engineer.mathsoftware.canvasplay.CanvasTest;
import engineer.mathsoftware.canvasplay.FxProdCanvas;
import javafx.scene.text.FontWeight;
import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.drawing.model.CanvasStates.drawingText;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class CanvasStatesTest extends CanvasTest {
    @Test
    void poppinsFontExistsInProjectResources() {
        // The whole font Poppins must be installed in the resources directory
        // of the project. https://fonts.google.com/specimen/Poppins
        var path = "/fonts/Poppins/Poppins-Light.ttf";
        var res = getClass().getResource(path);

        assertThat(res, is(notNullValue()));
    }

    @Test
    void poppinsFontLoadsCorrectlyByDefault() {
        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            prodCanvas.setState(drawingText(FontWeight.BOLD, 24.0));

            assertThat(prodCanvas.ctx().getFont(), is(notNullValue()));
            assertThat(
                prodCanvas.ctx().getFont().getName(), is("Poppins Bold")
            );
            assertThat(
                prodCanvas.ctx().getFont().getFamily(), is("Poppins")
            );
            assertThat(
                prodCanvas.ctx().getFont().getSize(), is(24.0)
            );
        });
    }
}
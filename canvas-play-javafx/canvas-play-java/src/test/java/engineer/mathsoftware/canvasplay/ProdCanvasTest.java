// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ProdCanvasTest extends CanvasTest {
    @Test
    void createProdCanvas() {
        actualCanvas(canvas -> {
            var prodCanvas = new FxProdCanvas(canvas, 1.0);

            assertThat(prodCanvas.width(), is(canvas.getWidth()));
            assertThat(prodCanvas.height(), is(canvas.getHeight()));
        });
    }
}
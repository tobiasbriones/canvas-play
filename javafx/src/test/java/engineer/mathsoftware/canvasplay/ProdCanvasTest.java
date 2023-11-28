// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProdCanvasTest extends CanvasTest {
    @Test
    public void create() {
        var prodCanvas = new FxProdCanvas(canvas, 1.0);

        assertThat(prodCanvas.width(), is(CanvasApp.WINDOW_WIDTH));
        assertThat(prodCanvas.height(), is(CanvasApp.WINDOW_HEIGHT));
    }
}
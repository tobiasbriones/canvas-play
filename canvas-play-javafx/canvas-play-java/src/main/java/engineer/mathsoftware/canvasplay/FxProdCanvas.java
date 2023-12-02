// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/***
 * Provides a production level {@link javafx.scene.canvas.Canvas} useful for
 * building relatively professional productions like images and animations
 * with scaling for custom resolutions, etc.
 */
public class FxProdCanvas implements ProdCanvas {
    private final Canvas canvas;
    private final double scale;

    public FxProdCanvas(Canvas canvas, double scale) {
        this.canvas = canvas;
        this.scale = scale;
    }

    @Override
    public double width() { return canvas.getWidth() / scale; }

    @Override
    public double height() { return canvas.getHeight() / scale; }

    @Override
    public GraphicsContext ctx() {
        return canvas.getGraphicsContext2D();
    }
}

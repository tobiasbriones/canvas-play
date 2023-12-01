// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.app;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class CanvasView {
    private static final double CANVAS_SCALE = 1.0;
    private static final double CANVAS_WIDTH = 900.0 * CANVAS_SCALE;
    private static final double CANVAS_HEIGHT = 900.0 * CANVAS_SCALE;
    private static final Color bgColor = Color.web("#fafafa");
    private final VBox root;
    private final Canvas canvas;

    CanvasView() {
        root = new VBox();
        canvas = new Canvas();
    }

    Parent getRoot() {
        return root;
    }

    void init() {
        var ctx = canvas.getGraphicsContext2D();

        canvas.setWidth(CANVAS_WIDTH);
        canvas.setHeight(CANVAS_HEIGHT);

        root.getChildren().add(canvas);
        draw(ctx);
    }

    private void draw(GraphicsContext ctx) {
        clean(ctx);

        runPlayground();
    }

    private void runPlayground() {
        var playground = new Playground(canvas, CANVAS_SCALE);
        playground.play();
    }

    private static void clean(GraphicsContext ctx) {
        ctx.setFill(bgColor);
        ctx.fillRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }
}

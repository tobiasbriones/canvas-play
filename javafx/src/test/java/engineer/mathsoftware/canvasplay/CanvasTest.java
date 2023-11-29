// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;

public class CanvasTest extends ApplicationTest {
    private static final double WINDOW_WIDTH = 800.0;
    private static final double WINDOW_HEIGHT = WINDOW_WIDTH / 2.0;
    private static final double CANVAS_HEIGHT = WINDOW_HEIGHT;
    private static final double CANVAS_WIDTH = WINDOW_WIDTH / 2.0;

    protected final Canvas canvas;
    protected final Canvas expectedCanvas;
    protected final GraphicsContext ctx;
    protected final GraphicsContext expected;

    public CanvasTest() {
        super();
        canvas = new Canvas();
        expectedCanvas = new Canvas();
        ctx = canvas.getGraphicsContext2D();
        expected = expectedCanvas.getGraphicsContext2D();
    }

    @Override
    public void start(Stage stage) {
        var root = new HBox(canvas, expectedCanvas);
        var scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        canvas.setWidth(CANVAS_WIDTH);
        canvas.setHeight(CANVAS_HEIGHT);

        expectedCanvas.setWidth(CANVAS_WIDTH);
        expectedCanvas.setHeight(CANVAS_HEIGHT);

        stage.setTitle("JavaFX Canvas Playground Test");
        stage.setScene(scene);
        stage.show();
    }
}

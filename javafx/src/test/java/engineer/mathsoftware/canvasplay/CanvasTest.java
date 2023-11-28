// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

public class CanvasTest extends ApplicationTest {
    private final CanvasApp app = new CanvasApp();
    protected final Canvas canvas = app.canvas;
    protected final GraphicsContext ctx = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage stage) {
        app.start(stage);
    }
}

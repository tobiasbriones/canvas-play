// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CanvasApp {
    public static final double WINDOW_WIDTH = 1360.0;
    public static final double WINDOW_HEIGHT = 640.0;

    final Canvas canvas;

    CanvasApp() {
        canvas = new Canvas();
    }

    void start(Stage primaryStage) {
        var root = new VBox(canvas);
        var scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        canvas.setWidth(WINDOW_WIDTH);
        canvas.setHeight(WINDOW_HEIGHT);

        primaryStage.setTitle("JavaFX Canvas Playground Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

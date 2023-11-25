// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double WINDOW_WIDTH = 1360.0;
    private static final double WINDOW_HEIGHT = 640.0;

    public static void main(String[] args) {
        launch(args);
    }

    private final CanvasView view;

    public Main() {
        super();
        view = new CanvasView();
    }

    @Override
    public void start(Stage primaryStage) {
        var scene = new Scene(view.getRoot(), WINDOW_WIDTH, WINDOW_HEIGHT);

        view.init();

        primaryStage.setTitle("JavaFX Canvas Playground");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

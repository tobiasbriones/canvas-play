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

import java.util.function.Consumer;

import static engineer.mathsoftware.canvasplay.CanvasMatchers.hasDrawing;
import static org.testfx.api.FxAssert.verifyThat;

public class CanvasTest extends ApplicationTest {
    private static final double WINDOW_WIDTH = 800.0;
    private static final double WINDOW_HEIGHT = WINDOW_WIDTH / 2.0;
    protected static final double CANVAS_HEIGHT = WINDOW_HEIGHT;
    protected static final double CANVAS_WIDTH = WINDOW_WIDTH / 2.0;

    private final Canvas actual;
    private final Canvas expected;

    public CanvasTest() {
        super();
        actual = new Canvas();
        expected = new Canvas();
    }

    @Override
    public final void start(Stage stage) {
        var root = new HBox(actual, expected);
        var scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        actual.setWidth(CANVAS_WIDTH);
        actual.setHeight(CANVAS_HEIGHT);

        expected.setWidth(CANVAS_WIDTH);
        expected.setHeight(CANVAS_HEIGHT);

        stage.setTitle("JavaFX Canvas Playground Test");
        stage.setScene(scene);
        stage.show();
    }

    protected final void match() {
        match("");
    }

    protected final void match(String expectedDesc) {
        verifyThat(
            actual,
            hasDrawing(expected, expectedDesc)
        );
    }

    /**
     * It runs the provided code on the actual {@link Canvas} in the JavaFX
     * thread, and waits for it before returning.
     *
     * @param interactActual effects to apply to the actual canvas
     */
    protected final void actualCanvas(Consumer<? super Canvas> interactActual) {
        interact(() -> interactActual.accept(actual));
    }

    /**
     * It runs the provided code on the actual {@link Canvas} via its
     * {@link GraphicsContext} in the JavaFX thread, and waits for it before
     * returning.
     *
     * @param interactActual effects to apply to the actual canvas
     */
    protected final void actual(Consumer<? super GraphicsContext> interactActual) {
        interact(() -> interactActual.accept(actual.getGraphicsContext2D()));
    }

    /**
     * It runs the provided code on the expected {@link Canvas} in the JavaFX
     * thread, and waits for it before returning.
     *
     * @param interactExpected effects to apply to the expected canvas
     */
    protected final void expectedCanvas(Consumer<? super Canvas> interactExpected) {
        interact(() -> interactExpected.accept(expected));
    }

    /**
     * It runs the provided code on the expected {@link Canvas} via its
     * {@link GraphicsContext} in the JavaFX thread, and waits for it before
     * returning.
     *
     * @param interactExpected effects to apply to the expected canvas
     */
    protected final void expected(Consumer<? super GraphicsContext> interactExpected) {
        interact(() -> interactExpected.accept(expected.getGraphicsContext2D()));
    }
}

// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static engineer.mathsoftware.canvasplay.CanvasMatchers.hasDrawing;
import static org.testfx.api.FxAssert.verifyThat;

public class CanvasTest extends ApplicationTest {
    private static final double WINDOW_WIDTH = 800.0;
    protected static final double CANVAS_WIDTH = WINDOW_WIDTH / 2.0;
    private static final double WINDOW_HEIGHT = WINDOW_WIDTH / 2.0;
    protected static final double CANVAS_HEIGHT = WINDOW_HEIGHT;
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
     * It asserts whether the actual canvas drawing in its current state matches
     * the given image. The image path is relative to the test/resources
     * directory.
     *
     * @param path path of the expected image from test/resources
     */
    protected void matchImage(String path) {
        var expectedImage = new Image(Objects
            .requireNonNull(
                getClass()
                    .getClassLoader()
                    .getResourceAsStream(path)));

        verifyThat(
            actual,
            hasDrawing(expectedImage, path)
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

    /**
     * It saves the snapshots of both actual and expected canvases in their
     * current state. The saving directory is the "snapshot" subdirectory of the
     * root of this project. That is, Path.of("", "snapshot", name).
     */
    protected void save(String name) {
        interact(() -> {
            var dir = Path.of("", "snapshot", name);
            var actualSnapshot = actual.snapshot(null, null);
            var expectedSnapshot = expected.snapshot(null, null);

            savePngImage(dir.resolve("actual.png"), actualSnapshot);
            savePngImage(dir.resolve("expected.png"), expectedSnapshot);
        });
    }

    private static void savePngImage(Path path, WritableImage image) {
        try {
            var bufferedImage = SwingFXUtils.fromFXImage(image, null);

            if (Files.isRegularFile(path)) {
                Files.deleteIfExists(path);
            }
            Files.createDirectories(path);

            ImageIO.write(bufferedImage, "png", path.toFile());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

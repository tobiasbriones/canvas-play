// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.hamcrest.Matcher;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static org.testfx.matcher.base.GeneralMatchers.typeSafeMatcher;

public final class CanvasMatchers {
    public static Matcher<Canvas> hasDrawing(Canvas drawing) {
        return hasDrawing(drawing, "a specified drawing");
    }

    /**
     * Creates a matcher that matches when the {@link Canvas} drawing is equal
     * to the given Canvas drawing.
     * <p>
     * That is, it matches when both canvases have the exact graphics, compared
     * pixel by pixel.
     *
     * @param drawing expected canvas to match
     * @param name    short description of what's in the expected drawing
     *
     * @return a matcher that matches when two canvas graphics are exactly the
     * same pixel by pixel
     */
    public static Matcher<Canvas> hasDrawing(Canvas drawing, String name) {
        return typeSafeMatcher(
            Canvas.class,
            "has " + name,
            canvas -> "Actual drawing doesn't match expected drawing",
            canvas -> areDrawingsEqual(canvas, drawing)
        );
    }

    public static Matcher<Canvas> hasDrawing(Image drawing, String name) {
        return typeSafeMatcher(
            Canvas.class,
            "has " + name,
            canvas -> "Actual drawing doesn't match expected image",
            canvas -> areDrawingsEqual(canvas, drawing)
        );
    }

    private static boolean areDrawingsEqual(
        Canvas actualCanvas,
        Canvas expectedCanvas
    ) {
        var expected = snapshot(expectedCanvas);
        return areDrawingsEqual(actualCanvas, expected);
    }

    private static boolean areDrawingsEqual(
        Canvas actualCanvas,
        Image expected
    ) {
        // Assume canvas sizes are integers and both equals //
        var actual = snapshot(actualCanvas);
        var expectedPixels = expected.getPixelReader();
        var actualPixels = actual.getPixelReader();
        var width = (int) actualCanvas.getWidth();
        var height = (int) actualCanvas.getHeight();
        return areImagesEqual(actualPixels, expectedPixels, width, height);
    }

    private static boolean areImagesEqual(
        PixelReader actual,
        PixelReader expected,
        int width,
        int height
    ) {
        record ColorPair(Color a, Color b) {
            boolean match() { return a.equals(b); }
        }

        return IntStream
            .range(0, height)
            .allMatch(y -> IntStream
                .range(0, width)
                .mapToObj(x -> new ColorPair(
                    actual.getColor(x, y),
                    expected.getColor(x, y)
                ))
                .allMatch(ColorPair::match));
    }

    private static WritableImage snapshot(Canvas drawing) {
        var result = new CompletableFuture<WritableImage>();

        Platform.runLater(() -> {
            var snapshot = drawing.snapshot(null, null);

            result.complete(snapshot);
        });

        try {
            return result.get();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private CanvasMatchers() {}
}

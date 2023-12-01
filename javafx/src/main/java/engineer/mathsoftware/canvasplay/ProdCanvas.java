// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.scene.canvas.GraphicsContext;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Defines a production level Canvas useful for building relatively professional
 * productions like images and animations with scaling for custom resolutions,
 * etc.
 */
public interface ProdCanvas {
    double width();

    double height();

    double cx();

    double cy();

    GraphicsContext ctx();

    default void setState(Consumer<? super GraphicsContext> setState) {
        setState.accept(ctx());
    }

    default <D, T> Function<T, D> drawing(
        BiFunction<? super ProdCanvas, ? super T, ? extends D> cons
    ) {
        return shape -> cons.apply(this, shape);
    }

    default <D, T> Function<T, D> drawingCtx(
        BiFunction<? super GraphicsContext, ? super T, ? extends D> cons
    ) {
        return shape -> cons.apply(ctx(), shape);
    }
}

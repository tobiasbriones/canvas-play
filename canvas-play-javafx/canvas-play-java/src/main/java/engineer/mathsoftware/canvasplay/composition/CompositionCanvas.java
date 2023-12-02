// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.composition;

import engineer.mathsoftware.canvasplay.ProdCanvas;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface CompositionCanvas extends Composition, ProdCanvas {
    default <D, T> Function<T, D> drawingComposition(
        BiFunction<? super CompositionCanvas, ? super T, ? extends D> cons
    ) {
        return shape -> cons.apply(this, shape);
    }
}

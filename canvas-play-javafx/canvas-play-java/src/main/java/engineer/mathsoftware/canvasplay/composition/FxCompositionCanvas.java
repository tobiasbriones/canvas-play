// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.composition;

import engineer.mathsoftware.canvasplay.FxProdCanvas;
import javafx.scene.canvas.Canvas;

class FxCompositionCanvas extends FxProdCanvas implements CompositionCanvas {
    FxCompositionCanvas(Canvas canvas, double scale) {
        super(canvas, scale);
    }
}

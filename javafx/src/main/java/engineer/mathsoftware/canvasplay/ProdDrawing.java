// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.scene.canvas.GraphicsContext;

/***
 * Defines production high-level drawing APIs for the {@link ProdCanvas}.
 */
public interface ProdDrawing {
    ProdCanvas canvas();

    GraphicsContext cxt();
}

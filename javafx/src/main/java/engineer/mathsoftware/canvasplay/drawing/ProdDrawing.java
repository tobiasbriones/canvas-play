// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.drawing;

import engineer.mathsoftware.canvasplay.shape.Oval;
import javafx.scene.canvas.GraphicsContext;

public interface ProdDrawing {
    static ProdDrawing of(GraphicsContext ctx) {
        return new FxDrawings.Drawing(ctx);
    }

    OvalDrawing oval(Oval oval);
}

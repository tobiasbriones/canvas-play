// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape.drawing;

import engineer.mathsoftware.canvasplay.shape.Line;
import engineer.mathsoftware.canvasplay.shape.Oval;
import engineer.mathsoftware.canvasplay.shape.Quadrilateral;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public interface ProdDrawing {
    static ProdDrawing of(GraphicsContext ctx) {
        return new FxDrawings.Drawing(ctx);
    }

    LineDrawing line(Line line);

    OvalDrawing oval(Oval oval);

    QuadrilateralDrawing quadrilateral(Quadrilateral quadrilateral);

    List<OvalDrawing> ovals(Oval... ovals);
}

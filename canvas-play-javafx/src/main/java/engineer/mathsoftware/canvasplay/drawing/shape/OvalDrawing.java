// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.shape.Oval;
import javafx.scene.canvas.GraphicsContext;

import static engineer.mathsoftware.canvasplay.shape.Oval.*;
import static engineer.mathsoftware.canvasplay.drawing.shape.CanvasOvalDrawing.*;

public interface OvalDrawing extends CommonDrawings.CommonDrawing {
    static OvalDrawing of(GraphicsContext ctx, Oval oval) {
        return switch (oval) {
            case Circle circle -> new CircleDrawing(
                ctx,
                circle.radius(),
                circle.cx(),
                circle.cy(),
                circle.diameter()
            );
            case Ellipse ellipse -> new EllipseDrawing(
                ctx,
                ellipse.radiusX(),
                ellipse.radiusY(),
                ellipse.cx(),
                ellipse.cy(),
                ellipse.diameterX(),
                ellipse.diameterY()
            );
        };
    }
}

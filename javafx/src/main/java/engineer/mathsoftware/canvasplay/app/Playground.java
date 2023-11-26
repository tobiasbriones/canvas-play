// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.app;

import engineer.mathsoftware.canvasplay.FxProdCanvas;
import engineer.mathsoftware.canvasplay.ProdCanvas;
import engineer.mathsoftware.canvasplay.drawing.OvalDrawing;
import engineer.mathsoftware.canvasplay.drawing.ProdDrawing;
import engineer.mathsoftware.canvasplay.drawing.QuadrilateralDrawing;
import engineer.mathsoftware.canvasplay.shape.Oval;
import engineer.mathsoftware.canvasplay.shape.Quadrilateral;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static engineer.mathsoftware.canvasplay.shape.Oval.*;
import static engineer.mathsoftware.canvasplay.shape.Quadrilateral.*;

class Playground {
    static final Color bgColor = Color.web("#fafafa");
    // Draw a Flower
    final int radius = 40;
    final Color color = Color.GOLDENROD;
    ProdCanvas canvas;
    ProdDrawing drawing;

    Playground(Canvas canvas, double scale) {
        this.canvas = new FxProdCanvas(canvas, scale);
        this.drawing = ProdDrawing.of(canvas.getGraphicsContext2D());
    }

    OvalDrawing flowerCenterDrawing() {
        return drawing.oval(new Circle(radius / 2, canvas.cx(), canvas.cy()));
    }

    QuadrilateralDrawing stemDrawing() {
        var stemWidth = radius / 4.0;
        return drawing.quadrilateral(new RoundRect(
            stemWidth,
            16,
            16,
            radius * 2.5,
            canvas.cx() - stemWidth / 2,
            canvas.cy()
        ));
    }

    void play() {
        // Flower Stem
        stemDrawing().fill(Color.web("#81c784"));

        // Petal
        fillPetals();
        flowerCenterDrawing().fill(Color.AQUAMARINE);
    }

    void fillPetals() {
        var cx = canvas.cx();
        var cy = canvas.cy();

        drawing.ovals(
            new Circle(radius, cx - radius / 2, cy),
            new Circle(radius, cx, cy - radius / 2),
            new Circle(radius, cx + radius / 2, cy),
            new Circle(radius, cx, cy + radius / 2)
        ).forEach(ovalDrawing -> ovalDrawing.fill(color));
    }
}

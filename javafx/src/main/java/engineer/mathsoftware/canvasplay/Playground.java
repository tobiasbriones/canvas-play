// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Playground {
    static final Color bgColor = Color.web("#fafafa");
    final Canvas canvas;
    final GraphicsContext ctx;
    final double scale;

    double width() { return canvas.getWidth(); }

    double height() { return canvas.getHeight(); }

    double cx() { return width() / 2; }

    double cy() { return height() / 2; }

    Playground(Canvas canvas, double scale) {
        this.canvas = canvas;
        this.scale = scale;
        this.ctx = canvas.getGraphicsContext2D();
    }

    // Draw a Flower
    final int radius = 40;
    final Color color = Color.GOLDENROD;

    void play() {
        var stemWidth = radius / 4;

        // Flower Stem
        ctx.setFill(Color.web("#81c784"));
        ctx.fillRoundRect(
            cx() - stemWidth / 2,
            cy(),
            stemWidth,
            radius * 2.5,
            16,
            16
        );

        // Petal
        ctx.setFill(Color.CORAL);
        fillPetals();
        ctx.setFill(Color.AQUAMARINE);
        fillCenteredCircle(radius / 2, cx(), cy(), Color.AQUAMARINE);
    }

    void fillPetals() {
        var cx = cx();
        var cy = cy();

        fillCenteredCircle(radius, cx - radius / 2, cy, color);
        fillCenteredCircle(radius, cx, cy - radius / 2, color);
        fillCenteredCircle(radius, cx + radius / 2, cy, color);
        fillCenteredCircle(radius, cx, cy + radius / 2, color);
    }

    void fillCenteredCircle(
        double radius,
        double cx,
        double cy,
        Color color
    ) {
        var diameter = 2.0 * radius;

        ctx.setFill(color);
        ctx.fillOval(cx - radius, cy - radius, diameter, diameter);
    }
}
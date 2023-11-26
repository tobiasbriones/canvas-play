// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.drawing;

import engineer.mathsoftware.canvasplay.shape.Oval;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.Arrays;
import java.util.List;

import static engineer.mathsoftware.canvasplay.shape.Oval.*;

final class FxDrawings {
    record Drawing(GraphicsContext ctx) implements ProdDrawing {
        @Override
        public OvalDrawing oval(Oval oval) {
            return switch (oval) {
                case Circle circle ->
                    new CircleDrawing(
                        ctx,
                        circle.radius(),
                        circle.cx(),
                        circle.cy(),
                        circle.diameter()
                    );
                case Arc arc ->
                    new ArcDrawing(
                        ctx,
                        arc.radiusX(),
                        arc.radiusY(),
                        arc.cx(),
                        arc.cy(),
                        arc.diameterX(),
                        arc.diameterY()
                    );
            };
        }

        @Override
        public List<OvalDrawing> ovals(Oval ...ovals) {
            return Arrays.stream(ovals).map(this::oval).toList();
        }
    }

    record CircleDrawing(
        GraphicsContext ctx,
        double radius,
        double cx,
        double cy,
        double diameter
    ) implements OvalDrawing {
        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillOval(cx - radius, cy - radius, diameter, diameter);
        }

        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeOval(cx - radius, cy - radius, diameter, diameter);
        }
    }

    record ArcDrawing(
        GraphicsContext ctx,
        double radiusX,
        double radiusY,
        double cx,
        double cy,
        double diameterX,
        double diameterY
    ) implements OvalDrawing {
        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillOval(cx - radiusX, cy - radiusY, diameterX, diameterY);
        }

        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeOval(cx - radiusX, cy - radiusY, diameterX, diameterY);
        }
    }

    private FxDrawings() {}
}

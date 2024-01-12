// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.shape.Oval;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

import static engineer.mathsoftware.canvasplay.shape.Oval.*;

public final class OvalDrawings {
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
                case EllipseArc arc -> new ArcDrawing(
                    ctx,
                    arc.radiusX(),
                    arc.radiusY(),
                    arc.angleStart(),
                    arc.angleExtent(),
                    arc.cx(),
                    arc.cy(),
                    arc.diameterX(),
                    arc.diameterY()
                );
            };
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
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeOval(cx - radius, cy - radius, diameter, diameter);
        }

        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillOval(cx - radius, cy - radius, diameter, diameter);
        }
    }

    record EllipseDrawing(
        GraphicsContext ctx,
        double radiusX,
        double radiusY,
        double cx,
        double cy,
        double diameterX,
        double diameterY
    ) implements OvalDrawing {
        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeOval(cx - radiusX, cy - radiusY, diameterX, diameterY);
        }

        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillOval(cx - radiusX, cy - radiusY, diameterX, diameterY);
        }
    }

    record ArcDrawing(
        GraphicsContext ctx,
        double radiusX,
        double radiusY,
        double angleStart,
        double angleExtent,
        double cx,
        double cy,
        double diameterX,
        double diameterY
    ) implements OvalDrawing {
        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokeArc(
                cx - radiusX,
                cy - radiusY,
                diameterX,
                diameterY,
                angleStart,
                angleExtent,
                ArcType.ROUND
            );
        }

        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillArc(
                cx - radiusX,
                cy - radiusY,
                diameterX,
                diameterY,
                angleStart,
                angleExtent,
                ArcType.ROUND
            );
        }
    }

    private OvalDrawings() {}
}

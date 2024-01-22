// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

import static engineer.mathsoftware.canvasplay.shape.Ovals.Ellipse;
import static engineer.mathsoftware.canvasplay.shape.Ovals.EllipseArc;

public final class OvalDrawings {
    public interface OvalDrawing extends CommonDrawings.CommonDrawing {
        static OvalDrawing of(GraphicsContext ctx, Ellipse ellipse) {
            return new EllipseDrawing(
                ctx,
                ellipse.radiusX(),
                ellipse.radiusY(),
                ellipse.cx(),
                ellipse.cy(),
                ellipse.diameterX(),
                ellipse.diameterY()
            );
        }

        static OvalDrawing ofArc(GraphicsContext ctx, EllipseArc arc) {
            return new ArcDrawing(
                ctx,
                arc.ellipse().radiusX(),
                arc.ellipse().radiusY(),
                arc.angleStart(),
                arc.angleExtent(),
                arc.ellipse().cx(),
                arc.ellipse().cy(),
                arc.ellipse().diameterX(),
                arc.ellipse().diameterY()
            );
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

    private OvalDrawings() { }
}

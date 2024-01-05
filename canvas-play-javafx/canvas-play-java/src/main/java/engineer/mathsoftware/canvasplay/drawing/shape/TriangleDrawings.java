// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.shape.Triangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import static engineer.mathsoftware.canvasplay.shape.Lines.*;
import static engineer.mathsoftware.canvasplay.shape.Triangle.*;

public final class TriangleDrawings {
    public interface TriangleDrawing extends CommonDrawings.CommonDrawing {
        static TriangleDrawing of(
            GraphicsContext ctx,
            Triangle triangle
        ) {
            var sides = triangle.sides();

            return switch (triangle) {
                case EquilateralTriangle ignore -> new CanvasTriangleDrawing(
                    ctx,
                    sides.base(),
                    sides.left()
                );

                case RoundedTriangle(var ignore, var arc) ->
                    new CanvasRoundTriangleDrawing(
                        ctx,
                        sides.base(),
                        sides.left(),
                        sides.right(),
                        triangle.height(),
                        arc
                    );
            };
        }
    }

    record CanvasTriangleDrawing(
        GraphicsContext ctx,
        HSegment base,
        Segment side
    ) implements TriangleDrawing {
        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            ctx.fillPolygon(
                new double[] {
                    base.sx(),
                    base.ex(),
                    base.cx()
                },
                new double[] {
                    base.cy(),
                    base.cy(),
                    side.ey()
                },
                3
            );

            System.out.println(base);
        }

        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            ctx.strokePolygon(
                new double[] {
                    base.cx() - base.radius(),
                    base.cx() + base.radius(),
                    base.cx()
                },
                new double[] {
                    base.cy() - base.radius(),
                    base.cy() - base.radius(),
                    base.cy() + base.radius()
                },
                3
            );
        }
    }

    record CanvasRoundTriangleDrawing(
        GraphicsContext ctx,
        HSegment base,
        Segment left,
        Segment right,
        double height,
        double arc
    ) implements TriangleDrawing {
        @Override
        public void fill(Paint color) {
            ctx.setFill(color);
            trace();
            ctx.fill();
        }

        @Override
        public void stroke(Paint color) {
            ctx.setStroke(color);
            trace();
            ctx.stroke();
        }

        void trace() {
            ctx.beginPath();
            ctx.moveTo(base.sx(), base.cy());
            ctx.lineTo(base.ex(), base.cy());

            ctx.quadraticCurveTo(
                base.ex() + arc,
                base.cy(),
                right.ex(),
                right.ey()
            );

            ctx.lineTo(right.sx(), right.sy());

            ctx.quadraticCurveTo(
                base.cx(),
                base.cy() - height,
                left.ex(),
                left.ey()
            );

            ctx.lineTo(left.sx(), left.sy());

            ctx.quadraticCurveTo(
                base.sx() - arc,
                base.cy(),
                base.sx(),
                base.cy()
            );
        }
    }

    private TriangleDrawings() {}
}

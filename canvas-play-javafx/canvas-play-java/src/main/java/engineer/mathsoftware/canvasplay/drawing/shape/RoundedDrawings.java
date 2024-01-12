// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.shape;

import engineer.mathsoftware.canvasplay.ProdCanvas;
import engineer.mathsoftware.canvasplay.drawing.shape.OvalDrawings.OvalDrawing;
import engineer.mathsoftware.canvasplay.shape.Oval;
import engineer.mathsoftware.canvasplay.shape.Quadrilateral;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.function.Function;

import static engineer.mathsoftware.canvasplay.drawing.shape.QuadrilateralDrawings.*;
import static engineer.mathsoftware.canvasplay.shape.Oval.*;
import static engineer.mathsoftware.canvasplay.shape.Quadrilateral.*;
import static engineer.mathsoftware.canvasplay.shape.rounded.RoundedPolygons.*;
import static engineer.mathsoftware.canvasplay.shape.rounded.RoundedPolygons.RoundedQuadrilateral.*;

public final class RoundedDrawings {
    public interface RoundedQuadrilateralDrawing extends CommonDrawings.CommonDrawing {
        static RoundedQuadrilateralDrawing of(
            ProdCanvas canvas,
            RoundedQuadrilateral quadrilateral
        ) {
            Function<Quadrilateral, QuadrilateralDrawing> quadrilateralDrawing =
                canvas.drawingCtx(QuadrilateralDrawing::of);
            var ovalDrawing =
                canvas.drawingCtx(OvalDrawing::of);

            return switch (quadrilateral) {
                case RoundedRectangle(var rectangle, var arcX, var arcY) ->
                    CanvasRoundRectangleDrawing.of(
                        quadrilateralDrawing,
                        ovalDrawing,
                        rectangle,
                        arcX,
                        arcY
                    );
            };
        }
    }

    public static Color generateRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        return Color.rgb(red, green, blue);
    }

    record CanvasRoundRectangleDrawing(
        List<QuadrilateralDrawing> rectangleDrawings,
        List<OvalDrawing> arcDrawings
    ) implements RoundedQuadrilateralDrawing {
        static CanvasRoundRectangleDrawing of(
            Function<? super Quadrilateral, ? extends QuadrilateralDrawing> quadrilateralDrawing,
            Function<? super Oval, ? extends OvalDrawing> ovalDrawing,
            Rectangle innerRectangle,
            double arcX,
            double arcY
        ) {
            var semiWidth = innerRectangle.width() / 2.0;
            var semiHeight = innerRectangle.height() / 2.0;
            var rx = semiWidth + arcX / 2.0;
            var ry = semiHeight + arcY / 2.0;

            return new CanvasRoundRectangleDrawing(
                List.of(
                    quadrilateralDrawing.apply(innerRectangle),
                    quadrilateralDrawing.apply( // Top Rectangle
                        new Rectangle(
                            innerRectangle.width(),
                            arcY,
                            innerRectangle.cx(),
                            innerRectangle.cy() - ry
                        )
                    ),
                    quadrilateralDrawing.apply( // Bottom Rectangle
                        new Rectangle(
                            innerRectangle.width(),
                            arcY,
                            innerRectangle.cx(),
                            innerRectangle.cy() + ry
                        )
                    ),
                    quadrilateralDrawing.apply( // Left Rectangle
                        new Rectangle(
                            arcX,
                            innerRectangle.height(),
                            innerRectangle.cx() - rx,
                            innerRectangle.cy()
                        )
                    ),
                    quadrilateralDrawing.apply( // Right Rectangle
                        new Rectangle(
                            arcX,
                            innerRectangle.height(),
                            innerRectangle.cx() + rx,
                            innerRectangle.cy()
                        )
                    )
                ),
                List.of(
                    ovalDrawing.apply( // Left-Top Arc
                        new EllipseArc(
                            arcX,
                            arcY,
                            90.0,
                            90.0,
                            innerRectangle.cx() - semiWidth,
                            innerRectangle.cy() - semiHeight
                        )
                    ),
                    ovalDrawing.apply( // Left-Bottom Arc
                        new EllipseArc(
                            arcX,
                            arcY,
                            180.0,
                            90.0,
                            innerRectangle.cx() - semiWidth,
                            innerRectangle.cy() + semiHeight
                        )
                    ),
                    ovalDrawing.apply( // Right-Top Arc
                        new EllipseArc(
                            arcX,
                            arcY,
                            0.0,
                            90.0,
                            innerRectangle.cx() + semiWidth,
                            innerRectangle.cy() - semiHeight
                        )
                    ),
                    ovalDrawing.apply( // Right-Bottom Arc
                        new EllipseArc(
                            arcX,
                            arcY,
                            270.0,
                            90.0,
                            innerRectangle.cx() + semiWidth,
                            innerRectangle.cy() + semiHeight
                        )
                    )
                )
            );
        }

        @Override
        public void fill(Paint color) {
            rectangleDrawings.forEach(rectangle -> rectangle.fill(color));
            arcDrawings.forEach(arc -> arc.fill(color));
        }

        @Override
        public void stroke(Paint color) {
            rectangleDrawings.forEach(rectangle -> rectangle.stroke(color));
            arcDrawings.forEach(arc -> arc.stroke(color));
        }
    }

    private RoundedDrawings() {}
}

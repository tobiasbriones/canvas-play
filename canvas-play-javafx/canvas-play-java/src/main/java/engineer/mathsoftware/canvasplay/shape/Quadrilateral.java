// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Quadrilateral extends Shape {
    record Rectangle(
        double width,
        double height,
        double cx,
        double cy
    ) implements Quadrilateral {
        @Override
        public double area() {
            return width * height;
        }
    }

    // TODO A round shape has to be defined as an actual composition of the
    //  original shape. It's wrong to define a RoundRectangle (etc.) as a
    //  Quadrilateral, since it doesn't have exactly four sides, but more
    //  like arcs as well. A round shape has to be defined via composition, i
    //  .e., it has *four sides* and the four arcs, it can be built from a
    //  Rectangle, arcs are substracted, so the round shape is created. With
    //  this composition, the drawing is efficient as it just has to delegate
    //  the drawing to the four Line denoting the straight rectangle as usual,
    //  and then just draw the arcs. This also removes cycles that come from
    //  this erroneous recursive design (i.e., a RoundRect is not actually a
    //  Quadrilateral because it's not exactly four sides, but this requires
    //  a Rectangle (an actual Quadrilateral) so it's a loop of deps).
    record RoundedRectangle(
        Rectangle rectangle,
        double arcX,
        double arcY
    ) implements Quadrilateral {
        @Override
        public double area() {
            // TODO take borders into account if necessary, not a useful
            // feature so far
            return rectangle.area();
        }
    }
}

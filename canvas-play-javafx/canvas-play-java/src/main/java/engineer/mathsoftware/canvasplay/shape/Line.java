// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Line extends Shape {
    record Segment(
        double sx,
        double sy,
        double ex,
        double ey
    ) implements Line {}

    record HSegment(
        double cx,
        double cy,
        double radius
    ) implements Line {}

    record VSegment(
        double cx,
        double cy,
        double radius
    ) implements Line {}

    @Override
    default double area() {
        return 0.0;
    }
}

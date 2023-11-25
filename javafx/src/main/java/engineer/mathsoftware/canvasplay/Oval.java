// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

public sealed interface Oval {
    record Circle(
        double radius,
        double cx,
        double cy
    ) implements Oval {}

    record Arc(
        double radiusX,
        double radiusY,
        double cx,
        double cy
    ) implements Oval {}
}

// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape;

public sealed interface Quadrilateral {
    record RoundRect(
        double width,
        double height,
        double arcX,
        double arcY,
        double cx,
        double cy
    ) implements Quadrilateral {}
}

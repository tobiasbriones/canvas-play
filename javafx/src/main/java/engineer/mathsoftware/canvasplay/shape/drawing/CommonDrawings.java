// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape.drawing;

import javafx.scene.paint.Paint;

public final class CommonDrawings {
    @FunctionalInterface
    public interface Fill {
        void fill(Paint color);
    }

    @FunctionalInterface
    public interface Stroke {
        void stroke(Paint color);
    }

    public interface CommonDrawing extends Fill, Stroke {}

    private CommonDrawings() {}
}

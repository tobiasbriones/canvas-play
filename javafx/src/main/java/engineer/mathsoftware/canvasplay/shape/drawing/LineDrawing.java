// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape.drawing;

import engineer.mathsoftware.canvasplay.shape.Line;
import javafx.scene.canvas.GraphicsContext;

import static engineer.mathsoftware.canvasplay.shape.Line.*;
import static engineer.mathsoftware.canvasplay.shape.drawing.CanvasLineDrawing.*;

public interface LineDrawing extends CommonDrawings.Stroke {
    static LineDrawing of(GraphicsContext ctx, Line line) {
        return switch (line) {
            case Segment segment -> new SegmentDrawing(
                ctx,
                segment.sx(),
                segment.sy(),
                segment.ex(),
                segment.ey()
            );
            case HSegment hSegment -> new HSegmentDrawing(
                ctx,
                hSegment.cx(),
                hSegment.cy(),
                hSegment.radius()
            );
            case VSegment vSegment -> new HSegmentDrawing(
                ctx,
                vSegment.cx(),
                vSegment.cy(),
                vSegment.radius()
            );
        };
    }
}

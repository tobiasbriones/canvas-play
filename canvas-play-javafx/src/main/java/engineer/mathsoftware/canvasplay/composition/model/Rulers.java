// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.composition.model;

import java.util.Optional;

public final class Rulers {
    public record Ruler(double width, double height) {}

    public record Measure(
        MeasureOrientation orientation,
        double radius,
        double cx,
        double cy,
        Optional<String> text
    ) {}

    public enum MeasureOrientation {
        HRuler, VRuler
    }

    private Rulers() {}
}

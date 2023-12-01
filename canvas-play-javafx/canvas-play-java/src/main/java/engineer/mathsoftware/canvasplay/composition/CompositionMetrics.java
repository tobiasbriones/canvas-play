// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.composition;

import engineer.mathsoftware.canvasplay.Metrics;

public interface CompositionMetrics extends Metrics {
    default double padding() {
        return size(Rem.of(2.0));
    }

    default double textPadding(Rem textSize) {
        return size(Rem.of(textSize.value() / 4.0));
    }

    default double textHeight(Rem textSize) {
        return size(textSize) + textPadding(textSize) * 2.0;
    }
}

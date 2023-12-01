// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.composition.caption;

import engineer.mathsoftware.canvasplay.composition.CompositionMetrics;

public interface CaptionMetrics extends CompositionMetrics {
    default double captionHeight() {
        // Assume home + subHome + title //
        return textHeight(Caption.TITLE_SIZE) * 2.0 + // Home, SubHome
            textHeight(Caption.ABSTRACT_SIZE); // Title
    }

    default double contentHeight() {
        return height() - captionHeight();
    }
}

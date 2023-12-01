// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.composition.caption;

import java.util.Optional;

import static engineer.mathsoftware.canvasplay.composition.caption.Caption.*;

public interface CaptionComposition extends CaptionMetrics {
    default Title home() {
        return new Title("mathsoftware.engineer");
    }

    default Optional<Title> subHome() {
        return Optional.of(
            new Title(
                "Canvas Play"
            )
        );
    }

    Title title();
}

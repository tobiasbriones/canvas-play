// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.composition;

import java.util.Optional;

import static engineer.mathsoftware.canvasplay.composition.caption.Caption.*;

interface Composition {
    default Title home() {
        return new Title("dev | mathsoftware.engineer");
    }

    default Optional<Title> subHome() {
        return Optional.of(
            new Title(
                "Canvas Playground"
            )
        );
    }

    Title title();
}

// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.composition;

import engineer.mathsoftware.canvasplay.caption.Caption;

import java.util.Optional;

interface Composition {
    default Caption.Title home() {
        return new Caption.Title("dev | mathsoftware.engineer");
    }

    default Optional<Caption.Title> subHome() {
        return Optional.of(
            new Caption.Title(
                "Canvas Playground"
            )
        );
    }

    Caption.Title title();
}

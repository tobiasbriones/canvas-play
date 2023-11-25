// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

/***
 * Defines a production level Canvas useful for building relatively
 * professional productions like images and animations with scaling for
 * custom resolutions, etc.
 */
public interface ProdCanvas {
    double width();

    double height();

    double cx();

    double cy();
}

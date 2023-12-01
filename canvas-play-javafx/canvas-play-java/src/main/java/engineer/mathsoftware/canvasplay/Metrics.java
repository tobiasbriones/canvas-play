// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay;

public interface Metrics {
    double REM_PX = 16.0;
    double BASELINE_WIDTH = 720.0;

    record Rem(double value) {
        public static Rem of(double value) { return new Rem(value); }
    }

    double width();

    double height();

    double cx();

    double cy();

    /**
     * 1rem = 16px, baseline is 720px width.
     *
     * @return the value of a REM in pixels considering 1rem = 16px and 720px
     * width as baseline
     */
    default double rem() {
        return REM_PX * (width() / BASELINE_WIDTH);
    }

    /**
     * Returns the size of the given REM units in pixels.
     *
     * @param size REM units
     *
     * @return the size of the given REM units in pixels
     */
    default double size(Rem size) {
        return rem() * size.value();
    }
}

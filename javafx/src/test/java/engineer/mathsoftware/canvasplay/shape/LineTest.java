// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.shape;

import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.shape.Line.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class LineTest {
    @Test
    void lineAreaIsZero() {
        var segment = new Segment(0.0, 0.0, 10.0, 15.0);
        var hSegment = new HSegment(0.0, 0.0, 10.0);
        var vSegment = new VSegment(0.0, 0.0, 10.0);

        // The area of any Line is zero
        assertThat(segment.area(), is(0.0));
        assertThat(hSegment.area(), is(0.0));
        assertThat(vSegment.area(), is(0.0));
    }
}

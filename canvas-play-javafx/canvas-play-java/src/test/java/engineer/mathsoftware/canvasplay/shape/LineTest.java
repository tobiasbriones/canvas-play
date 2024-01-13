// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.shape.Lines.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class LineTest {
    @Test
    void lineAreaIsZero() {
        var segment = new Segment(0.0, 0.0, 10.0, 15.0);
        var hSegment = HSegment.of(
            10.0,
            0.0,
            0.0
        );
        var vSegment = HSegment.of(
            10.0,
            0.0,
            0.0
        );

        // The area of any Line is zero
        assertThat(segment.area(), is(0.0));
        assertThat(hSegment.area(), is(0.0));
        assertThat(vSegment.area(), is(0.0));
    }

    @Test
    void subSegment() {
        var segmentPosSlope = new Segment(1.0, 2.0, 10.0, 8.0);
        var segmentNegSlope = new Segment(4.0, 2.0, 10.0, -8.0);

        assertThat(
            segmentPosSlope.minus(2.0),
            is(
                new Segment(
                    2.664100588675687,
                    3.109400392450458,
                    8.335899411324313,
                    6.890599607549542
                )
            )
        );

        assertThat(
            segmentNegSlope.minus(3.5),
            is(
                new Segment(
                    5.800735143996342,
                    -1.0012252399939046,
                    8.199264856003657,
                    -4.998774760006095
                )
            )
        );
    }
}

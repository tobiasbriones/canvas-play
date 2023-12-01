// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.shape;

import org.junit.jupiter.api.Test;

import static engineer.mathsoftware.canvasplay.shape.Oval.Circle;
import static engineer.mathsoftware.canvasplay.shape.Oval.Ellipse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class OvalTest {
    @Test
    void area() {
        var circle = new Circle(5.0, 10.0, 10.0);
        var circleArea = Math.PI * StrictMath.pow(5.0, 2.0);

        assertThat(circle.area(), is(circleArea));

        var ellipse = new Ellipse(5.0, 7.0, 10.0, 10.0);
        var ellipseArea = Math.PI * 5.0 * 7.0;

        assertThat(ellipse.area(), is(ellipseArea));
    }
}

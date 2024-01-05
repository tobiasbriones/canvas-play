// Copyright (c) 2024 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.geo;

public final class Angles {
    @FunctionalInterface
    public interface AngleMeasure {
        double angle();
    }

    /**
     * It represents a signed acute angle value, that is, a double in (0, 90) or
     * (-90, 0).
     */
    public record AcuteAngle(double value) {}

    @FunctionalInterface
    public interface ToAcute {
        AcuteAngle toAcute();
    }

    public enum AcuteOrientation implements AngleMeasure, ToAcute {
        Acute15,
        Acute30,
        Acute45,
        Acute60;

        @Override
        public double angle() {
            return switch (this) {
                case Acute15 -> 15.0;
                case Acute30 -> 30.0;
                case Acute45 -> 45.0;
                case Acute60 -> 60.0;
            };
        }

        @Override
        public AcuteAngle toAcute() {
            return new AcuteAngle(angle());
        }
    }

    public enum QuadrantalOrientation implements AngleMeasure {
        Horizontal,
        Vertical;

        @Override
        public double angle() {
            return switch (this) {
                case Horizontal -> 0.0;
                case Vertical -> 90.0;
            };
        }
    }

    private Angles() {}
}

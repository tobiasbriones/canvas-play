// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.anim;

import javafx.animation.AnimationTimer;

public class FadeAnimLoop extends AnimationTimer {
    public enum TimeMode {Absolute, Relative}

    @FunctionalInterface
    public interface Draw {
        void draw(
            int animNum,
            double opacity,
            Cycle.State state,
            int tickCount,
            double cycleTime
        );
    }

    private final Draw draw;
    private final long targetFps;
    private final TimeMode timeMode;
    private final long targetFrameTime;
    private final double fadeDuration;
    private final double fadeInStartTime;
    private final Cycle cycle;
    private long lastUpdate;
    private int count;
    private double opacity;

    public FadeAnimLoop(
        Draw draw,
        int targetFps,
        double cycleDuration,
        TimeMode timeMode
    ) {
        super();
        this.draw = draw;
        this.targetFps = targetFps;
        this.targetFrameTime = 1_000_000_000 / targetFps;
        this.timeMode = timeMode;
        this.fadeDuration = cycleDuration / 4.0;
        this.fadeInStartTime = cycleDuration - fadeDuration;
        this.cycle = new Cycle(cycleDuration, fadeDuration);
        this.lastUpdate = 0;
        this.count = 0;
        this.opacity = 1;
    }

    private double getOpacity() {
        return switch (cycle.state) {
            case FadingIn -> cycle.time / fadeDuration;
            case Steady -> 1.0;
            case FadingOut ->
                1.0 - (cycle.time - fadeInStartTime) / fadeDuration;
        };
    }

    @Override
    public void handle(long now) {
        if (lastUpdate == 0) {
            lastUpdate = now;
            cycle.reset();
            return;
        }

        var deltaTime = switch (timeMode) {
            case Absolute -> now - lastUpdate;
            case Relative -> targetFrameTime;
        };

        updateCanvas(deltaTime / 1_000_000_000.0D);
        draw.draw(cycle.getCount(),
            opacity,
            cycle.getState(),
            count,
            cycle.getTime()
        );

        sleep(deltaTime);

        lastUpdate = now;
    }

    void updateCanvas(double deltaTime) {
        cycle.update(deltaTime);
        count++;
        opacity = getOpacity();
    }

    void sleep(long deltaTime) {
        var minimumRestTime = 20L;
        var remainingFrameTime = targetFrameTime - deltaTime;
        var sleepTime = switch (timeMode) {
            case Absolute -> remainingFrameTime > 0 ?
                remainingFrameTime / 1_000_000L :
                minimumRestTime;
            case Relative -> minimumRestTime;
        };

        // Case 1: It's game loop (absolute time) so it can have time left
        // to sleep for the next tick, or it ran out of time already.
        // The animation has to run as synced as possible (e.g. 60FPS).

        // Case 2: It's simulation (relative time), sleep a minimum value
        // to let the CPU rest. The animation has to run as fast as
        // possible.
        try {
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public static class Cycle {
        public enum State {FadingIn, Steady, FadingOut}

        private final double cycleDuration;
        private final double fadeDuration;
        private State state = State.FadingIn;
        private double time = 0.0;
        private int count = 1;

        Cycle(double cycleDuration, double fadeDuration) {
            if (cycleDuration <= fadeDuration * 2.0) {
                throw new RuntimeException("Invalid cycle duration");
            }
            this.cycleDuration = cycleDuration;
            this.fadeDuration = fadeDuration;
        }

        State getState() {
            return state;
        }

        double getTime() {
            return time;
        }

        int getCount() {
            return count;
        }

        void reset() {
            state = State.FadingIn;
            time = 0.0;
            count = 1;
        }

        void update(double delta) {
            var newTime = time + delta;
            time = newTime;

            if (newTime > cycleDuration) {
                // TODO diff can be greater than cycleDuration
                time = newTime - cycleDuration;
                count++;
            }

            if (time < fadeDuration) {
                state = State.FadingIn;
            }
            else if (time > cycleDuration - fadeDuration) {
                state = State.FadingOut;
            }
            else {
                state = State.Steady;
            }
        }
    }
}

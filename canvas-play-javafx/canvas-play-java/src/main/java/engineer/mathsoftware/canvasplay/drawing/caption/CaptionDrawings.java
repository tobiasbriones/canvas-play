// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.drawing.caption;

import engineer.mathsoftware.canvasplay.ProdCanvas;
import engineer.mathsoftware.canvasplay.composition.CompositionCanvas;
import javafx.geometry.VPos;
import javafx.scene.text.FontWeight;

import java.util.Optional;

import static engineer.mathsoftware.canvasplay.composition.caption.Caption.*;
import static engineer.mathsoftware.canvasplay.drawing.CanvasStates.drawingText;

public final class CaptionDrawings {
    public interface CaptionDrawing {
        void draw();
    }

    public interface CardDrawing {
        int BIG_TITLE_LENGTH_THRESHOLD = 36;

        static CardDrawing of(CompositionCanvas canvas, Card card) {
            var compositionPadding = canvas.padding();

            // Metrics from bottom-up: Home -> SubHome -> Title -> Abstract
            var homeBottom
                = compositionPadding + canvas.textPadding(TITLE_SIZE);

            var bottomAfterHome
                = homeBottom
                + canvas.size(TITLE_SIZE)
                + canvas.textPadding(TITLE_SIZE);

            var subHomeBottom
                = bottomAfterHome
                + canvas.textPadding(TITLE_SIZE);

            var bottomAfterSubHome
                = card.subHome().isPresent()
                ? subHomeBottom
                + canvas.size(TITLE_SIZE)
                + canvas.textPadding(TITLE_SIZE)
                : bottomAfterHome;

            var bigTitle
                = card.title().value().length() > BIG_TITLE_LENGTH_THRESHOLD;

            var titleSize
                = bigTitle ? TITLE_SIZE : ABSTRACT_SIZE;

            var titleBottom
                = bottomAfterSubHome + canvas.textPadding(titleSize);

            var homesNum = card.subHome().isPresent() ? 2 : 1;
            var abstractBottom
                = compositionPadding // bottom padding
                + canvas.textHeight(TITLE_SIZE) * homesNum  // home, home2
                + canvas.textHeight(ABSTRACT_SIZE)  // title
                + compositionPadding  // extra padding to separate titles
                // with abstract
                + canvas.textPadding(ABSTRACT_SIZE); // abstract padding

            return new CanvasCardDrawing(
                new CanvasTitleDrawing(
                    canvas,
                    card.title().value(),
                    titleBottom,
                    titleSize.value()
                ),
                new CanvasHomeDrawing(
                    canvas,
                    card.home().value(),
                    homeBottom
                ),
                card.subHome().map(subHome -> new CanvasSubHomeDrawing(
                    canvas,
                    subHome.value(),
                    subHomeBottom
                )),
                card.abs().map(abs -> new CanvasAbstractDrawing(
                    canvas,
                    abs.value(),
                    abstractBottom
                ))
            );
        }

        void drawTitle();

        void drawHome();

        void drawSubHome();

        void drawAbstract();

        default void draw() {
            drawTitle();
            drawHome();
            drawSubHome();
            drawAbstract();
        }
    }

    private static void drawCaptionLabel(
        ProdCanvas canvas,
        String abs,
        double bottom
    ) {
        canvas.ctx().fillText(
            abs,
            canvas.cx(),
            canvas.height() - bottom
        );
    }

    record CanvasCardDrawing(
        CaptionDrawing titleDrawing,
        CaptionDrawing homeDrawing,
        Optional<CaptionDrawing> subHomeDrawing,
        Optional<CaptionDrawing> abstractDrawing
    ) implements CardDrawing {
        @Override
        public void drawTitle() {
            titleDrawing.draw();
        }

        @Override
        public void drawHome() {
            homeDrawing.draw();
        }

        @Override
        public void drawSubHome() {
            subHomeDrawing.ifPresent(CaptionDrawing::draw);
        }

        @Override
        public void drawAbstract() {
            abstractDrawing.ifPresent(CaptionDrawing::draw);
        }
    }

    record CanvasTitleDrawing(
        ProdCanvas canvas,
        String title,
        double titleBottom,
        double titleSize
    ) implements CaptionDrawing {
        @Override
        public void draw() {
            canvas.setState(drawingText(
                FontWeight.BOLD,
                titleSize,
                VPos.BOTTOM
            ));
            drawCaptionLabel(canvas, title, titleBottom);
        }
    }

    record CanvasHomeDrawing(
        ProdCanvas canvas,
        String home,
        double bottom
    ) implements CaptionDrawing {
        @Override
        public void draw() {
            canvas.setState(drawingText(
                FontWeight.MEDIUM,
                canvas.size(TITLE_SIZE),
                VPos.BOTTOM
            ));
            drawCaptionLabel(canvas, home, bottom);
        }
    }

    record CanvasSubHomeDrawing(
        ProdCanvas canvas,
        String subHome,
        double bottom
    ) implements CaptionDrawing {
        @Override
        public void draw() {
            canvas.setState(drawingText(
                FontWeight.LIGHT,
                canvas.size(ABSTRACT_SIZE),
                VPos.BOTTOM
            ));
        }
    }

    record CanvasAbstractDrawing(
        ProdCanvas canvas,
        String abs,
        double bottom
    ) implements CaptionDrawing {
        @Override
        public void draw() {
            canvas.setState(drawingText(
                FontWeight.LIGHT,
                canvas.size(ABSTRACT_SIZE),
                VPos.BOTTOM
            ));

            drawCaptionLabel(canvas, abs, bottom);
        }
    }

    private CaptionDrawings() {}
}

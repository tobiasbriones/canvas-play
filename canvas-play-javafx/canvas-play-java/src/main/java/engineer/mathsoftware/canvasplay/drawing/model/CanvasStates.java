// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.drawing.model;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.function.Consumer;

public final class CanvasStates {
    public static Consumer<GraphicsContext> drawingText(
        FontWeight weight, double size
    ) {
        return drawingText(weight, size, VPos.CENTER, Color.web("#212121"));
    }

    public static Consumer<GraphicsContext> drawingText(
        FontWeight weight, double size, VPos baseline
    ) {
        return drawingText(weight, size, baseline, Color.web("#212121"));
    }

    public static Consumer<GraphicsContext> drawingText(
        FontWeight weight,
        double size,
        VPos baseline,
        Color color
    ) {
        var font = loadFont(weight, size);

        return ctx -> {
            ctx.setFill(color);
            ctx.setTextAlign(TextAlignment.CENTER);
            ctx.setTextBaseline(baseline);
            ctx.setFontSmoothingType(FontSmoothingType.GRAY);
            ctx.setFont(font);
        };
    }

    private static Font loadFont(FontWeight weight, double size) {
        var variant = switch (weight) {
            case THIN -> "Thin";
            case EXTRA_LIGHT -> "ExtraLight";
            case LIGHT -> "Light";
            case NORMAL -> "Regular";
            case MEDIUM -> "Medium";
            case SEMI_BOLD -> "SemiBold";
            case BOLD -> "Bold";
            case EXTRA_BOLD -> "ExtraBold";
            case BLACK -> "Black";
        };
        var path = "/fonts/Poppins/Poppins-" + variant + ".ttf";

        return Font.loadFont(
            CanvasStates.class.getResourceAsStream(path), size
        );
    }

    private CanvasStates() {}
}

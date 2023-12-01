// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-playground

package engineer.mathsoftware.canvasplay.composition.model;

import engineer.mathsoftware.canvasplay.ProdCanvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class CommonOps {
    private static final Color bgColor = Color.web("#fafafa");
    private final ProdCanvas canvas;
    private final GraphicsContext ctx;

    CommonOps(ProdCanvas canvas) {
        this.canvas = canvas;
        this.ctx = canvas.ctx();
    }
    //
    // void reset() {
    //     clean();
    //
    //     ctx.setGlobalAlpha(1.0);
    //     RulerDraw.of(
    //         new Ruler(canvas.width(), canvas.height())
    //     ).draw(ctx);
    //     ctx.setGlobalAlpha(opacity);
    // }
    //
    // void clean() {
    //     ctx.setGlobalAlpha(1);
    //     ctx.setFill(bgColor);
    //     ctx.fillRect(0.0, 0.0, canvas.width(), canvas.height());
    //     drawTitles();
    //     ctx.setGlobalAlpha(opacity);
    // }
    //
    // void clean(Image bgImage) {
    //     ctx.setGlobalAlpha(1.0);
    //     drawImage(bgImage);
    //     ctx.setGlobalAlpha(opacity);
    // }
    //
    // void drawImage(Image image) {
    //     var x = (canvas.width() - image.getWidth()) / 2.0;
    //     var y = (contentHeight() - image.getHeight()) / 2.0;
    //
    //     ctx.drawImage(image, x, y);
    // }
    //
    // void drawAbstract(Caption.Abstract abs) {
    //     var homesNum = drawing.subHome().isPresent() ? 2 : 1;
    //     var bottom =
    //         padding() + // bottom padding
    //             textHeight(Caption.TITLE_SIZE) * homesNum + // home, home2
    //             textHeight(Caption.ABSTRACT_SIZE) + // title
    //             padding() + // extra padding to separate titles with abstract
    //             textPadding(Caption.ABSTRACT_SIZE); // abstract padding
    //
    //     setDrawingText(
    //         FontWeight.LIGHT,
    //         sizePx(Caption.ABSTRACT_SIZE),
    //         VPos.BOTTOM
    //     );
    //
    //     drawTitleLabel(abs.value(), bottom);
    // }
    //
    // void drawTitleLabel(String title, double bottom) {
    //     ctx.fillText(
    //         title,
    //         width() / 2,
    //         height() - bottom
    //     );
    // }
}

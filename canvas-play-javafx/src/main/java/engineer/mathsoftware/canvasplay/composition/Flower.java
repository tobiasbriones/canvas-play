package engineer.mathsoftware.canvasplay.composition;

import engineer.mathsoftware.canvasplay.ProdCanvas;
import engineer.mathsoftware.canvasplay.composition.caption.Caption;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Flower implements Composition {
    public static final int ANIM_NUM = 9;
    // private final ProdDrawing drawing;
    private final GraphicsContext ctx;
    private final double radius;
    private final Color color;
    private final Color centerColor;
    private final double cx;
    private final double cy;

    public Flower(
        ProdCanvas canvas,
        double radius,
        Color color,
        Color centerColor,
        double cx,
        double cy
    ) {
        // this.drawing = ProdDrawing.of(canvas.ctx());
        this.ctx = canvas.ctx();
        this.radius = radius;
        this.color = color;
        this.centerColor = centerColor;
        this.cx = cx;
        this.cy = cy;
    }

    @Override
    public Caption.Title title() {
        return new Caption.Title("Drawing a Flower");
    }
    //
    //
    // void draw(int animNum, Cycle.State state) {
    //     switch (animNum) {
    //         case 1 -> anim1_Diameter();
    //         case 2 -> anim2_LeftPetal();
    //         case 3 -> anim3_TopPetal();
    //         case 4 -> anim4_RightPetal();
    //         case 5 -> anim5_BottomPetal();
    //         case 6 -> anim6_Center();
    //         case 7 -> anim7_Center();
    //         case 8 -> anim8_Stem();
    //         case 9 -> anim9_Flower(state);
    //     }
    // }
    //
    // void anim1_Diameter() {
    //     reset();
    //
    //     RulerSegmentDraw.of(
    //         new RulerSegment(
    //             RulerSegmentOrientation.HRuler,
    //             radius,
    //             cx,
    //             cy - radius,
    //             Optional.of("diameter")
    //         )
    //     ).draw(ctx);
    //
    //     drawAbstract(new Caption.Abstract("Centering"));
    // }
    //
    // void anim2_LeftPetal() {
    //     reset();
    //     fillCenteredCircle(radius, cx - radius / 2, cy, color);
    //     encloseHRuler(radius, cx - radius / 2, cy, "diameter");
    //     drawAbstract(new Caption.Abstract("Petal: Left"));
    // }
    //
    // void anim3_TopPetal() {
    //     reset();
    //     fillCenteredCircle(radius, cx - radius / 2, cy, color);
    //     fillCenteredCircle(radius, cx, cy - radius / 2, color);
    //     encloseHRuler(radius, cx, cy - radius / 2, "diameter");
    //     drawAbstract(new Caption.Abstract("Petal: Top"));
    // }
    //
    // void anim4_RightPetal() {
    //     reset();
    //     fillCenteredCircle( radius, cx - radius / 2, cy, color);
    //     fillCenteredCircle(radius, cx, cy - radius / 2, color);
    //     fillCenteredCircle(radius, cx + radius / 2, cy, color);
    //     encloseHRuler(radius, cx + radius / 2, cy, "diameter");
    //     drawAbstract(new Caption.Abstract("Petal: Right"));
    // }
    //
    // void anim5_BottomPetal() {
    //     reset();
    //     fillPetals();
    //     encloseHRuler(radius, cx, cy + radius / 2, "diameter");
    //     drawAbstract(new Caption.Abstract("Petal: Bottom"));
    // }
    //
    // void anim6_Center() {
    //     reset();
    //     fillPetals();
    //     encloseHRuler(radius, cx, cy, "diameter");
    //     drawAbstract(new Caption.Abstract("Flower: Center"));
    // }
    //
    // void anim7_Center() {
    //     reset();
    //     fillPetals();
    //     fillCenteredCircle(radius / 2, cx, cy, centerColor);
    //     encloseHRuler(radius / 2, cx, cy, "radius");
    //     drawAbstract(new Caption.Abstract("Flower: Center"));
    // }
    //
    // void anim8_Stem() {
    //     reset();
    //
    //     // Petal
    //     fillPetals();
    //
    //     // draw line to delimit the bottom of center and petals
    //     ctx.strokeLine(0, cy + radius / 2, cx, cy + radius / 2);
    //     ctx.strokeLine(0, cy + 3 * radius / 2, cx, cy + 3 * radius / 2);
    //
    //     fillCenteredCircle(radius / 2, cx, cy, centerColor);
    //
    //     // Flower Stem
    //     var stemWidth = radius / 4;
    //     var stemHeight = radius * 2.5;
    //
    //     ctx.setFill(Color.web("#81c784"));
    //     ctx.fillRoundRect(cx - stemWidth / 2,
    //         cy,
    //         stemWidth,
    //         stemHeight,
    //         16,
    //         16
    //     );
    //     encloseVRuler(
    //         stemHeight / 2,
    //         cx,
    //         cy + stemHeight / 2,
    //         "2.5*radius"
    //     );
    //
    //     drawAbstract(new Caption.Abstract("Flower: Stem"));
    // }
    //
    // void anim9_Flower(Cycle.State state) {
    //     clean();
    //
    //     // Eliminate the effect as this is the last anim
    //     if (state == Cycle.State.FadingOut) {
    //         ctx.setGlobalAlpha(1);
    //     }
    //
    //     drawFlower();
    // }

    // private void drawFlower() {
    //     // Flower Stem
    //     stemDrawing().fill(Color.web("#81c784"));
    //
    //     // Petal
    //     fillPetals();
    //     flowerCenterDrawing().fill(Color.AQUAMARINE);
    // }
    //
    // private void fillPetals() {
    //     drawing.ovals(
    //         new Circle(radius, cx - radius / 2.0, cy),
    //         new Circle(radius, cx, cy - radius / 2.0),
    //         new Circle(radius, cx + radius / 2.0, cy),
    //         new Circle(radius, cx, cy + radius / 2.0)
    //     ).forEach(ovalDrawing -> ovalDrawing.fill(color));
    // }
    //
    // private OvalDrawing flowerCenterDrawing() {
    //     return drawing.oval(new Circle(radius / 2.0, cx, cy));
    // }
    //
    // private QuadrilateralDrawing stemDrawing() {
    //     var stemWidth = radius / 4.0;
    //     return drawing.quadrilateral(new RoundRect(
    //         stemWidth,
    //         16.0,
    //         16.0,
    //         radius * 2.5,
    //         cx - stemWidth / 2.0,
    //         cy
    //     ));
    // }
}

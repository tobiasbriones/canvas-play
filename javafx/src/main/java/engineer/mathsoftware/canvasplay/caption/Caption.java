package engineer.mathsoftware.canvasplay.caption;

public sealed interface Caption {
    double TITLE_SIZE = 1.5;

    // Use this smaller size for embedded captions (i.e. captions are part
    // of the whole animation). If the font size is bigger (1.5) then the
    // caption is separate from the animation.
    // double TITLE_SIZE = 1.25;

    double ABSTRACT_SIZE = TITLE_SIZE * 1.5;

    record Title(String value) implements Caption {}

    record Abstract(String value) implements Caption {}
}
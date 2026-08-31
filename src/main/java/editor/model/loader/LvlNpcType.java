package editor.model.loader;

import java.awt.Color;

public enum LvlNpcType {
    ANITA("Anita", new Color(212, 145, 237, 180)),
    NIKOLAS("Nikolas", new Color(224, 107, 81, 180)),
    SIR_DEJANOVIC("Sir Dejanovic", new Color(103, 171, 235, 180)),
    KRYSANTHE("Krysanthe", new Color(255, 112, 238, 180)),
    RORIC("Roric", new Color(156, 221, 74, 180)),
    MAX("", Color.BLACK);

    private final String label;
    private final Color color;

    LvlNpcType(String label, Color color) {
        this.label = label;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public Color getColor() {
        return color;
    }
}
package editor.model.loader;

import java.awt.Color;

public enum LvlTriggerType {
    // ID, Line Color, Display Name
    FOLLOWER_BOUNDARY(0, new Color(0, 150, 255, 100), "AI BOUNDARY"), 
    KILL_ZONE(1, new Color(255, 0, 0, 100), "KILL ZONE"),
    LOAD_LEFT_LEVEL(2, new Color(0, 255, 0, 100), "LOAD_LEFT_LEVEL"),
    LOAD_RIGHT_LEVEL(2, new Color(0, 255, 0, 100), "LOAD_RIGHT_LEVEL"),
    LOAD_UP_LEVEL(2, new Color(0, 255, 0, 100), "LOAD_UP_LEVEL"),
    LOAD_DOWN_LEVEL(2, new Color(0, 255, 0, 100), "LOAD_DOWN_LEVEL"),
    MAX(6, Color.BLACK, "");

    private final int id;
    private final Color color;
    private final String label;

    LvlTriggerType(int id, Color color, String label) {
        this.id = id;
        this.color = color;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public String getLabel() {
        return label;
    }
}
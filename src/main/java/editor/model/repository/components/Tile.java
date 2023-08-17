package editor.model.repository.components;

import editor.model.repository.Node;

public class Tile extends Node {

    private TileType tileType;
    private int layer;

    private int x, y;
    private int red, green, blue;

    public Tile(String name, Node parent) {
        super(name, parent);
    }

    public Tile(String name, Node parent, TileType tileType, int x, int y, int red, int green, int blue) {
        super(name, parent);
        this.tileType = tileType;
        this.x = x;
        this.y = y;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

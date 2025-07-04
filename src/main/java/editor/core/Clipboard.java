package editor.core;

import editor.model.repository.components.Tile;

import java.util.ArrayList;
import java.util.List;

public class Clipboard {

    private static final Clipboard instance = new Clipboard();
    private final List<Tile> copiedTiles = new ArrayList<>();

    private Clipboard() {}

    public static Clipboard getInstance() {
        return instance;
    }

    public void copy(List<Tile> tiles) {
        copiedTiles.clear();
        for (Tile original : tiles) {
            Tile copy = new Tile(original.getName(), null, original.getTileType(), original.getX(), original.getY(), original.getRed(), original.getGreen(), original.getBlue());
            copy.setLayer(original.getLayer());
            copy.setRotation(original.getRotation());
            copy.setScaleX(original.getScaleX());
            copy.setScaleY(original.getScaleY());
            copiedTiles.add(copy);
        }
    }

    public List<Tile> getCopiedTiles() {
        return copiedTiles;
    }
}

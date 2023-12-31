package editor.gui.view.renderer.renderers;

import editor.gui.view.renderer.RenderStrategy;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.TILE_SIZE;

public class TerrainRenderer implements RenderStrategy<Tile> {

    private final BufferedImage[] tiles;

    public TerrainRenderer(BufferedImage[] tiles) {
        this.tiles = tiles;
    }

    @Override
    public void render(Graphics g, Tile tile, int layer) {
        if (tiles == null || tiles.length == 0) return;
        int value = tile.getRed();
        int layerIndex = tile.getLayer();
        if (value != -1 && value < tiles.length && tile.getTileType() == TileType.SOLID && layerIndex == layer) {
            if (tiles[value] != null)
                g.drawImage(tiles[value], tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
        }
    }

}
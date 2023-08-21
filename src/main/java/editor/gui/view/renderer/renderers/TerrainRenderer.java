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
        int value = tile.getRed();
        int layerIndex = tile.getLayer();
        if (value != -1 && tile.getTileType() == TileType.SOLID && layerIndex == layer) {
            g.drawImage(tiles[value], tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
        }
    }

}
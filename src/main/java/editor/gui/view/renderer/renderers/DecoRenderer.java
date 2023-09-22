package editor.gui.view.renderer.renderers;

import editor.gui.view.renderer.RenderStrategy;
import editor.model.loader.LvlDecoType;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.TILE_SIZE;

public class DecoRenderer implements RenderStrategy<Tile> {

    private final BufferedImage[] decoTiles;

    public DecoRenderer(BufferedImage[] decoTiles) {
        this.decoTiles = decoTiles;
    }

    @Override
    public void render(Graphics g, Tile tile, int layer) {
        if (decoTiles == null || decoTiles.length == 0) return;
        int value = tile.getBlue();
        int layerIndex = tile.getLayer();
        if (value != -1 && tile.getTileType() == TileType.DECO && layerIndex == layer) {
            LvlDecoType deco = LvlDecoType.values()[value];
            int x = tile.getX() * TILE_SIZE + deco.getXOffset();
            int y = tile.getY() * TILE_SIZE + deco.getYOffset();
            if (decoTiles[value] != null)
                g.drawImage(decoTiles[value], x, y, deco.getWid(), deco.getHei(), null);
        }
    }

}

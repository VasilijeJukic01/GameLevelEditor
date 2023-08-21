package editor.gui.view.renderer.renderers;

import editor.gui.view.renderer.RenderStrategy;
import editor.model.loader.LvlObjType;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.TILE_SIZE;

public class ObjectRenderer implements RenderStrategy<Tile> {

    private final BufferedImage[] objectTiles;

    public ObjectRenderer(BufferedImage[] objectTiles) {
        this.objectTiles = objectTiles;
    }

    @Override
    public void render(Graphics g, Tile tile, int layer) {
        int value = tile.getBlue();
        if (value != -1 && tile.getTileType() == TileType.OBJECT) {
            LvlObjType obj = LvlObjType.values()[value];
            int x = tile.getX() * TILE_SIZE + obj.getXOffset();
            int y = tile.getY() * TILE_SIZE + obj.getYOffset();
            g.drawImage(objectTiles[value], x, y, obj.getWid(), obj.getHei(), null);
        }
    }

}

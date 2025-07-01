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
        if (decoTiles == null || decoTiles.length == 0 || tile.getTileType() != TileType.DECO) return;

        int value = tile.getBlue();
        int layerIndex = tile.getLayer();

        if (value != -1 && layerIndex == layer) {
            LvlDecoType deco = LvlDecoType.values()[value];
            BufferedImage model = decoTiles[value];
            if (model == null) return;

            double rotation = tile.getRotation();
            double scaleX = tile.getScaleX();
            double scaleY = tile.getScaleY();

            if (rotation == 0.0 && scaleX == 1.0 && scaleY == 1.0) {
                int x = tile.getX() * TILE_SIZE + deco.getXOffset();
                int y = tile.getY() * TILE_SIZE + deco.getYOffset();
                g.drawImage(model, x, y, deco.getWid(), deco.getHei(), null);
            }
            else {
                Graphics2D g2d = (Graphics2D) g.create();
                int tileTopLeftX = tile.getX() * TILE_SIZE;
                int tileTopLeftY = tile.getY() * TILE_SIZE;
                int centerX = tileTopLeftX + deco.getWid() / 2;
                int centerY = tileTopLeftY + deco.getHei() / 2;

                g2d.translate(centerX, centerY);
                g2d.rotate(Math.toRadians(rotation));
                g2d.scale(scaleX, scaleY);

                g2d.drawImage(model, -deco.getWid() / 2, -deco.getHei() / 2, deco.getWid(), deco.getHei(), null);
                g2d.dispose();
            }
        }
    }

}

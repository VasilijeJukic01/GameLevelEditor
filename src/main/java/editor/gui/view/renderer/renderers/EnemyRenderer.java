package editor.gui.view.renderer.renderers;

import editor.gui.view.renderer.RenderStrategy;
import editor.model.loader.LvlEnemyType;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.TILE_SIZE;

public class EnemyRenderer implements RenderStrategy<Tile> {

    private final BufferedImage[] enemyTiles;

    public EnemyRenderer(BufferedImage[] enemyTiles) {
        this.enemyTiles = enemyTiles;
    }

    @Override
    public void render(Graphics g, Tile tile, int layer) {
        int value = tile.getGreen();
        if (value != -1 && tile.getTileType() == TileType.ENEMY) {
            LvlEnemyType enemy = LvlEnemyType.values()[value];
            int x = tile.getX() * TILE_SIZE + enemy.getXOffset();
            int y = (tile.getY() - 1) * TILE_SIZE + enemy.getYOffset();
            g.drawImage(enemyTiles[value], x, y, enemy.getWid(), enemy.getHei(), null);
        }
    }

}
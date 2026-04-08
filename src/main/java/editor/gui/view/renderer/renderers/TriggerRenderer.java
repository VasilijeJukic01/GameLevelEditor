package editor.gui.view.renderer.renderers;

import editor.gui.view.renderer.RenderStrategy;
import editor.model.loader.LvlTriggerType;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;

import java.awt.*;

import static editor.constants.Constants.TILE_SIZE;

public class TriggerRenderer implements RenderStrategy<Tile> {

    @Override
    public void render(Graphics g, Tile tile, int layer) {
        if (tile.getTileType() != TileType.TRIGGER) return;

        int value = tile.getBlue();
        if (value < 0 || value >= LvlTriggerType.MAX.ordinal()) return;

        LvlTriggerType type = LvlTriggerType.values()[value];
        int x = tile.getX() * TILE_SIZE;
        int y = tile.getY() * TILE_SIZE;

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(type.getColor());
        g2d.fillRect(x + (TILE_SIZE / 4), y, TILE_SIZE / 2, TILE_SIZE);
        g2d.setColor(Color.WHITE);
        int dotSize = TILE_SIZE / 8;
        g2d.fillOval(x + (TILE_SIZE / 2) - (dotSize / 2), y + (TILE_SIZE / 2) - (dotSize / 2), dotSize, dotSize);

        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString(type.getLabel(), x + 2, y + 10);

        g2d.dispose();
    }
}
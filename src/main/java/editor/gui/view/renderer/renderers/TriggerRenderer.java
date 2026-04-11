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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int padding = TILE_SIZE / 10;
        int boxSize = TILE_SIZE - (padding * 2);

        g2d.setColor(new Color(30, 30, 30, 220));
        g2d.fillRoundRect(x + padding, y + padding, boxSize, boxSize, 10, 10);
        g2d.setColor(new Color(255, 255, 255, 200));
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(x + padding, y + padding, boxSize, boxSize, 10, 10);

        String label = type.getLabel();
        g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
        FontMetrics fm = g2d.getFontMetrics();

        int labelX = x + (TILE_SIZE - fm.stringWidth(label)) / 2;
        int labelY = y + padding + fm.getAscent() + 2;

        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.drawString(label, labelX + 1, labelY + 1);
        g2d.setColor(Color.WHITE);
        g2d.drawString(label, labelX, labelY);

        // Center
        int circleSize = boxSize / 2;
        int circleX = x + (TILE_SIZE - circleSize) / 2;
        int circleY = y + (int)(TILE_SIZE * 0.6) - (circleSize / 2);
        g2d.setColor(type.getColor());
        g2d.fillOval(circleX, circleY, circleSize, circleSize);

        g2d.dispose();
    }
}
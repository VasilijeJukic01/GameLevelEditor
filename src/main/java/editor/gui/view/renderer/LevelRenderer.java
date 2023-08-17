package editor.gui.view.renderer;

import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.*;
import static editor.constants.Constants.FOREST_TILE_SIZE;
import static editor.constants.FilePaths.FOREST_SPRITE;

public class LevelRenderer implements Renderer {

    private Level level;
    private BufferedImage[] forestSprite;

    public LevelRenderer(Level level) {
        this.level = level;
        loadSprite();
    }

    private void loadSprite() {
        BufferedImage img = Utils.getInstance().importImage(FOREST_SPRITE, -1, -1);
        forestSprite = new BufferedImage[FOREST_TILES];
        for (int i = 0; i < FOREST_SPRITE_ROW; i++) {
            for (int j = 0; j < FOREST_SPRITE_COL; j++) {
                int index = j * FOREST_SPRITE_COL + i;
                forestSprite[index] = img.getSubimage(i*FOREST_TILE_SIZE, j*FOREST_TILE_SIZE, FOREST_TILE_SIZE, FOREST_TILE_SIZE);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (level.getChildren() == null) return;
        for (Node child : level.getChildren()) {
            Tile c = (Tile) child;
            int value = c.getRed();
            if (value == -1) continue;
            g.drawImage(forestSprite[value], c.getX() * TILE_SIZE, c.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
        }
        renderGrid(g);
    }

    private void renderGrid(Graphics g) {
        if (!level.isShowGrid()) return;
        int cols = level.getWidth();
        int rows = level.getHeight();
        g.setColor(Color.BLUE);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}

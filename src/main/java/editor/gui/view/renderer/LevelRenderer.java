package editor.gui.view.renderer;

import editor.gui.view.tab.TabView;
import editor.model.loader.LevelObject;
import editor.model.loader.LevelObjectManager;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.TileType;
import editor.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.*;
import static editor.constants.Constants.FOREST_TILE_SIZE;
import static editor.constants.FilePaths.FOREST_SPRITE;

public class LevelRenderer implements Renderer {

    private final Level level;
    private BufferedImage[] forestSprite;

    private final LevelObjectManager levelObjectManager;
    private final TabView tabView;

    public LevelRenderer(TabView tabView) {
        this.tabView = tabView;
        this.level = tabView.getLevel();
        this.levelObjectManager = new LevelObjectManager();
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
        String layers = (String) tabView.getSettings().getParameter("Layers");
        int fade = (int) tabView.getSettings().getParameter("Fade");

        for (int i = 0; i < layers.length() - 1; i++) {
            if (layers.charAt(layers.length()-1) == '1' || layers.charAt(i) == '1') {
                if (i == 2 && fade == 1) {
                    renderFade(g);
                }
                renderDeco(g, i);
                renderTerrain(g, i);
            }
        }

        renderGrid(g);
    }

    private void renderTerrain(Graphics g, int layer) {
        for (Node child : level.getChildren()) {
            Tile c = (Tile) child;
            int value = c.getRed();
            int layerIndex = c.getLayer();
            if (value == -1) continue;
            if (c.getTileType() == TileType.SOLID) {
                if (layerIndex == layer)
                    g.drawImage(forestSprite[value], c.getX() * TILE_SIZE, c.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }
    }

    private void renderDeco(Graphics g, int layer) {
        for (Node child : level.getChildren()) {
            Tile c = (Tile) child;
            int value = c.getBlue();
            int layerIndex = c.getLayer();
            if (value == -1) continue;
            if (c.getTileType() == TileType.DECO) {
                LevelObject lvlObject = levelObjectManager.getObjects()[value];
                if (layerIndex == layer) {
                    int x = c.getX() * TILE_SIZE + lvlObject.getXOffset();
                    int y = c.getY() * TILE_SIZE + lvlObject.getYOffset();
                    g.drawImage(levelObjectManager.getModels()[lvlObject.getType().ordinal()], x, y, lvlObject.getW(), lvlObject.getH(), null);
                }
            }

        }
    }

    private void renderFade(Graphics g) {
        g.setColor(DEFAULT_FADE_COLOR);
        g.fillRect(0, 0, level.getWidth()*TILE_SIZE, level.getHeight()*TILE_SIZE);
    }

    private void renderGrid(Graphics g) {
        if (!level.isShowGrid()) return;
        g.setColor(Color.BLUE);

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public BufferedImage[] getForestSprite() {
        return forestSprite;
    }
}

package editor.gui.view.renderer;

import editor.core.Framework;
import editor.gui.view.tab.TabView;
import editor.model.loader.LevelDeco;
import editor.model.loader.LvlObjType;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.*;

public class LevelRenderer implements Renderer {

    private final Level level;
    private final TabView tabView;

    public LevelRenderer(TabView tabView) {
        this.tabView = tabView;
        this.level = tabView.getLevel();
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
        renderObjects(g);
        if (tabView.getSettings().getParameter("Selection") != null) {
            Tile selection = (Tile) tabView.getSettings().getParameter("Selection");
            g.setColor(SELECTION_COLOR);
            g.fillRect(selection.getX() * TILE_SIZE, selection.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
        renderGrid(g);
    }

    private void renderTerrain(Graphics g, int layer) {
        BufferedImage[] forestTiles = Framework.getInstance().getStorage().getForestTilesImg();
        for (Node child : level.getChildren()) {
            Tile c = (Tile) child;
            int value = c.getRed();
            int layerIndex = c.getLayer();
            if (value == -1) continue;
            if (c.getTileType() == TileType.SOLID) {
                if (layerIndex == layer)
                    g.drawImage(forestTiles[value], c.getX() * TILE_SIZE, c.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }
    }

    private void renderObjects(Graphics g) {
        BufferedImage[] objectTiles = Framework.getInstance().getStorage().getObjectsTilesImg();
        for (Node child : level.getChildren()) {
            Tile c = (Tile) child;
            int value = c.getBlue();
            if (value == -1) continue;
            if (c.getTileType() == TileType.OBJECT) {
                LvlObjType obj = LvlObjType.values()[value];
                int x = c.getX() * TILE_SIZE + obj.getXOffset();
                int y = c.getY() * TILE_SIZE + obj.getYOffset();
                g.drawImage(objectTiles[value], x, y, obj.getWid(), obj.getHei(), null);
            }
        }
    }

    private void renderDeco(Graphics g, int layer) {
        LevelDeco[] decorations = Framework.getInstance().getStorage().getForestObjects();
        BufferedImage[] models = Framework.getInstance().getStorage().getForestDecoTilesImg();
        for (Node child : level.getChildren()) {
            Tile c = (Tile) child;
            int value = c.getBlue();
            int layerIndex = c.getLayer();
            if (value == -1) continue;
            if (c.getTileType() == TileType.DECO) {
                LevelDeco deco = decorations[value];
                if (layerIndex == layer) {
                    int x = c.getX() * TILE_SIZE + deco.getXOffset();
                    int y = c.getY() * TILE_SIZE + deco.getYOffset();
                    g.drawImage(models[deco.getType().ordinal()], x, y, deco.getW(), deco.getH(), null);
                }
            }

        }
    }

    private void renderFade(Graphics g) {
        g.setColor(DEFAULT_FADE_COLOR);
        g.fillRect(0, 0, level.getWidth()*TILE_SIZE, level.getHeight()*TILE_SIZE);
    }

    private void renderGrid(Graphics g) {
        boolean isGrid = (boolean) tabView.getSettings().getParameter("Grid");
        if (!isGrid) return;
        g.setColor(Color.BLUE);

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        }
    }

}

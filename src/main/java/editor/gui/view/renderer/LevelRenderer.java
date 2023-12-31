package editor.gui.view.renderer;

import editor.core.Framework;
import editor.gui.view.renderer.renderers.DecoRenderer;
import editor.gui.view.renderer.renderers.EnemyRenderer;
import editor.gui.view.renderer.renderers.ObjectRenderer;
import editor.gui.view.renderer.renderers.TerrainRenderer;
import editor.gui.view.tab.TabView;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.TileType;
import editor.settings.SettingsKey;

import java.awt.*;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.*;
import static editor.constants.ObjectConstants.*;

public class LevelRenderer implements Renderer {

    private final Level level;
    private final TabView tabView;

    private RenderStrategy<Tile> terrainRenderer, objectRenderer, enemyRenderer, decoRenderer;

    public LevelRenderer(TabView tabView) {
        this.tabView = tabView;
        this.level = tabView.getLevel();
        reloadTileset();
    }

    private void reloadTileset() {
        String set = (String) tabView.getSettings().getParameter(SettingsKey.TILE_SET);
        this.terrainRenderer = new TerrainRenderer(Framework.getInstance().getStorage().getImageMap().get(set+"Tiles"));
        this.objectRenderer = new ObjectRenderer(Framework.getInstance().getStorage().getImageMap().get("Objects"));
        this.enemyRenderer = new EnemyRenderer(Framework.getInstance().getStorage().getImageMap().get("Enemies"));
        this.decoRenderer = new DecoRenderer(Framework.getInstance().getStorage().getImageMap().get(set+"Deco"));
    }

    @Override
    public void render(Graphics g) {
        reloadTileset();
        if (level.getChildren() == null) return;
        String layersFlags = (String) tabView.getSettings().getParameter(SettingsKey.LAYERS_INFO);
        int fade = (int) tabView.getSettings().getParameter(SettingsKey.FADE);

        renderBackground(g);
        for (int layer = 0; layer < layersFlags.length() - 1; layer++) {
            if (layersFlags.charAt(layersFlags.length()-1) == '1' || layersFlags.charAt(layer) == '1') {
                if (layer == 2 && fade == 1) renderFade(g);
                renderLayer(g, layer);
            }
        }
        renderSelection(g);
        renderGrid(g);
    }

    private void renderBackground(Graphics g) {
        BufferedImage background = (BufferedImage) tabView.getSettings().getParameter(SettingsKey.BACKGROUND);
        if (background != null) g.drawImage(background, 0, 0, level.getWidth()*TILE_SIZE, level.getHeight()*TILE_SIZE, null);
    }

    private void renderLayer(Graphics g, int layer) {
        for (Node child : level.getChildren()) {
            Tile tile = (Tile) child;
            terrainRenderer.render(g, tile, layer);
            objectRenderer.render(g, tile, layer);
            decoRenderer.render(g, tile, layer);
            enemyRenderer.render(g, tile, layer);
            if (tile.getTileType() == TileType.PLAYER) renderPlayer(g, tile);
        }
    }

    private void renderPlayer(Graphics g, Tile t) {
        BufferedImage playerImg = Framework.getInstance().getStorage().getPlayerImg();
        int x = t.getX() * TILE_SIZE + PLAYER_OFFSET_X;
        int y = t.getY() * TILE_SIZE + PLAYER_OFFSET_Y;
        g.drawImage(playerImg, x, y, PLAYER_WID, PLAYER_HEI, null);
    }

    private void renderFade(Graphics g) {
        Color fadeColor = (Color) tabView.getSettings().getParameter(SettingsKey.FADE_COLOR);
        g.setColor(fadeColor);
        g.fillRect(0, 0, level.getWidth() * TILE_SIZE, level.getHeight() * TILE_SIZE);
    }

    private void renderSelection(Graphics g) {
        if (tabView.getSettings().getParameter(SettingsKey.EDIT_SELECTION) != null) {
            Tile selection = (Tile) tabView.getSettings().getParameter(SettingsKey.EDIT_SELECTION);
            g.setColor(SELECTION_COLOR);
            g.fillRect(selection.getX() * TILE_SIZE, selection.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private void renderGrid(Graphics g) {
        boolean isGrid = (boolean) tabView.getSettings().getParameter(SettingsKey.GRID);
        if (!isGrid) return;
        Color gridColor = (Color) tabView.getSettings().getParameter(SettingsKey.GRID_COLOR);
        g.setColor(gridColor);

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
            }
        }
    }

}

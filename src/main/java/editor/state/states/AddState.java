package editor.state.states;

import editor.core.Framework;
import editor.gui.view.tab.TabView;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;
import editor.state.State;

import java.util.List;

import static editor.constants.Constants.*;

public class AddState implements State<TabView> {

    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        String set = (String) tabView.getSettings().getParameter("Selected Set");
        int index = (int) tabView.getSettings().getParameter("Selected Tile");
        int layer = (int) tabView.getSettings().getParameter("Selected Layer");

        List<Tile> tiles;
        if ("Solid Tiles".equals(set))
            tiles = Framework.getInstance().getStorage().getForestSolidTiles();
        else if ("Decorations".equals(set))
            tiles = Framework.getInstance().getStorage().getForestDecoTiles();
        else return;

        int tileX = x / TILE_SIZE;
        int tileY = y / TILE_SIZE;

        if (set.equals("Solid Tiles")) {
            for (Tile tile : tiles) {
                if (tile.getRed() == index) {
                    if (!isFree(tileX, tileY, tabView.getLevel(), TileType.SOLID)) return;
                    Tile newTile = new Tile("", tabView.getLevel(), TileType.SOLID, tileX, tileY, index, 254, 254);
                    addTile(newTile, layer, tabView);
                }
            }
        }
        else {
            for (Tile tile : tiles) {
                if (tile.getBlue() == index) {
                    if (!isFree(tileX, tileY, tabView.getLevel(), TileType.DECO)) return;
                    Tile newTile = new Tile("", tabView.getLevel(), TileType.DECO, tileX, tileY, 254, layer, index);
                    addTile(newTile, layer, tabView);
                }
            }
        }
    }

    private boolean isFree(int tileX, int tileY, Level level, TileType type) {
        for (Node node : level.getChildren()) {
            Tile tile = (Tile) node;
            if (tile.getX() == tileX && tile.getY() == tileY && tile.getTileType() == type) return false;
        }
        return true;
    }

    private void addTile(Tile newTile, int layer, TabView tabView) {
        newTile.setLayer(layer);
        tabView.getLevel().addChild(newTile);
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {

    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }

}

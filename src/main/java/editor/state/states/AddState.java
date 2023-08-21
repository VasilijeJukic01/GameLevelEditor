package editor.state.states;

import editor.command.Command;
import editor.command.commands.AddNodeCommand;
import editor.core.Framework;
import editor.gui.view.tab.TabView;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;
import editor.settings.SettingsKey;
import editor.state.State;

import java.util.List;

import static editor.constants.Constants.*;

public class AddState implements State<TabView> {

    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        String set = (String) tabView.getSettings().getParameter(SettingsKey.SELECTED_SET);
        int index = (int) tabView.getSettings().getParameter(SettingsKey.SELECTED_TILE);
        int layer = (int) tabView.getSettings().getParameter(SettingsKey.SELECTED_LAYER);

        List<Tile> tiles = getTileSet(set);
        Tile newTile;
        if (tiles == null) return;

        int tileX = x / TILE_SIZE;
        int tileY = y / TILE_SIZE;


        if (set.equals("Solid Tiles")) newTile = getSolidTile(tabView, tiles, index, layer, tileX, tileY);
        else if (set.equals("Objects")) newTile = getObjectTile(tabView, tiles, index, tileX, tileY);
        else if (set.equals("Enemies")) newTile = getEnemyTile(tabView, tiles, index, tileX, tileY);
        else newTile = getDecoTile(tabView, tiles, index, layer, tileX, tileY);

        if (newTile != null) addTile(newTile, layer, tabView);
    }

    private List<Tile> getTileSet(String name) {
        if ("Solid Tiles".equals(name))
            return Framework.getInstance().getStorage().getForestSolidTiles();
        else if ("Decorations".equals(name))
            return Framework.getInstance().getStorage().getForestDecoTiles();
        else if ("Objects".equals(name))
            return Framework.getInstance().getStorage().getObjectTiles();
        else if ("Enemies".equals(name))
            return Framework.getInstance().getStorage().getEnemyTiles();
       return null;
    }

    private boolean isFree(int tileX, int tileY, Level level, List<TileType> types) {
        for (Node node : level.getChildren()) {
            Tile tile = (Tile) node;
            if (tile.getX() == tileX && tile.getY() == tileY && types.contains(tile.getTileType())) return false;
        }
        return true;
    }

    private Tile getSolidTile(TabView tabView, List<Tile> tiles, int index, int layer, int tileX, int tileY) {
        for (Tile tile : tiles) {
            if (tile.getRed() == index) {
                if (!isFree(tileX, tileY, tabView.getLevel(), List.of(TileType.SOLID, TileType.OBJECT, TileType.ENEMY))) return null;
                int green = (layer == 5) ? 255 : 254;
                int blue = (layer == 5) ? 255 : 254;
                return new Tile("", tabView.getLevel(), TileType.SOLID, tileX, tileY, index, green, blue);
            }
        }
        return null;
    }

    private Tile getObjectTile(TabView tabView, List<Tile> tiles, int index, int tileX, int tileY) {
        for (Tile tile : tiles) {
            if (tile.getBlue() == index) {
                if (!isFree(tileX, tileY, tabView.getLevel(), List.of(TileType.SOLID, TileType.OBJECT, TileType.ENEMY))) return null;
                return new Tile("", tabView.getLevel(), TileType.OBJECT, tileX, tileY, 254, 254, index);
            }
        }
        return null;
    }

    private Tile getEnemyTile(TabView tabView, List<Tile> tiles, int index, int tileX, int tileY) {
        for (Tile tile : tiles) {
            if (tile.getGreen() == index) {
                if (!isFree(tileX, tileY, tabView.getLevel(), List.of(TileType.SOLID, TileType.OBJECT, TileType.ENEMY))) return null;
                return new Tile("", tabView.getLevel(), TileType.ENEMY, tileX, tileY, 254, index, 254);
            }
        }
        return null;
    }

    private Tile getDecoTile(TabView tabView, List<Tile> tiles, int index, int layer, int tileX, int tileY) {
        for (Tile tile : tiles) {
            if (tile.getBlue() == index) {
                if (!isFree(tileX, tileY, tabView.getLevel(), List.of(TileType.DECO))) return null;
                return new Tile("", tabView.getLevel(), TileType.DECO, tileX, tileY, 254, layer, index);
            }
        }
        return null;
    }

    private void addTile(Tile newTile, int layer, TabView tabView) {
        newTile.setLayer(layer);
        Command command = new AddNodeCommand(tabView.getLevel(), newTile);
        Framework.getInstance().getGui().getCommandManager().addCommand(command);
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {

    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }

}

package editor.state.states;

import editor.command.Command;
import editor.command.commands.AddNodeCommand;
import editor.core.Clipboard;
import editor.core.Framework;
import editor.gui.view.tab.TabView;
import editor.logger.LogType;
import editor.model.repository.components.Tile;
import editor.state.State;

import java.util.List;

import static editor.constants.Constants.TILE_SIZE;

public class PasteState implements State<TabView> {

    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        List<Tile> copiedTiles = Clipboard.getInstance().getCopiedTiles();
        if (copiedTiles.isEmpty()) {
            Framework.getInstance().log("Clipboard is empty, nothing to paste.", LogType.WARNING);
            return;
        }

        int mouseTileX = x / TILE_SIZE;
        int mouseTileY = y / TILE_SIZE;
        int minX = copiedTiles.stream().mapToInt(Tile::getX).min().orElse(0);
        int minY = copiedTiles.stream().mapToInt(Tile::getY).min().orElse(0);

        int offsetX = mouseTileX - minX;
        int offsetY = mouseTileY - minY;

        for (Tile original : copiedTiles) {
            Tile newTile = new Tile(original.getName(), tabView.getLevel(), original.getTileType(), original.getX() + offsetX, original.getY() + offsetY, original.getRed(), original.getGreen(), original.getBlue());
            newTile.setLayer(original.getLayer());
            newTile.setRotation(original.getRotation());
            newTile.setScaleX(original.getScaleX());
            newTile.setScaleY(original.getScaleY());

            Command command = new AddNodeCommand(tabView.getLevel(), newTile);
            Framework.getInstance().getGui().getCommandManager().addCommand(command);
        }

        Framework.getInstance().log("Pasted " + copiedTiles.size() + " tiles.", LogType.INFORMATION);
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {

    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }
}

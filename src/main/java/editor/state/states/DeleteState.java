package editor.state.states;

import editor.command.Command;
import editor.command.commands.DeleteNodeCommand;
import editor.core.Framework;
import editor.gui.view.tab.TabView;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;
import editor.state.State;

import java.util.Iterator;

import static editor.constants.Constants.TILE_SIZE;

public class DeleteState implements State<TabView> {


    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        Tile target = findTileAtPosition(x, y, tabView);
        if (target != null) {
            Command command = new DeleteNodeCommand(tabView.getLevel(), target);
            Framework.getInstance().getGui().getCommandManager().addCommand(command);
        }
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {

    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }

    private Tile findTileAtPosition(int x, int y, TabView tabView) {
        int tileSize = TILE_SIZE;
        Level level = tabView.getLevel();
        Iterator<Node> iterator = level.getChildren().iterator();

        while (iterator.hasNext()) {
            Node child = iterator.next();
            if (child instanceof Tile) {
                Tile c = (Tile) child;
                int xPos = c.getX();
                int yPos = c.getY();
                if (isBetween(xPos * tileSize, (xPos + 1) * tileSize, x) && isBetween(yPos * tileSize, (yPos + 1) * tileSize, y)) {
                    iterator.remove();
                    return c;
                }
            }
        }
        return null;
    }

    private boolean isBetween(int start, int end, int value) {
        return value >= start && value <= end;
    }

}

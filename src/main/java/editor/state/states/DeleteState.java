package editor.state.states;

import editor.gui.view.tab.TabView;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.state.State;

import static editor.constants.Constants.TILE_SIZE;

public class DeleteState implements State<TabView> {


    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        Tile target = null;
        for (Node child : tabView.getLevel().getChildren()) {
            Tile c = (Tile) child;
            int xPos = c.getX();
            int yPos = c.getY();
            if (isBetween(xPos*TILE_SIZE, (xPos+1)*TILE_SIZE, x) && isBetween(yPos*TILE_SIZE, (yPos+1)*TILE_SIZE, y)) {
                target = c;
                break;
            }
        }
        if (target != null) tabView.getLevel().getChildren().remove(target);
        tabView.repaint();
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {

    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }

    private boolean isBetween(int x1, int x2, int n) {
        return n >= x1 && n <= x2;
    }

}

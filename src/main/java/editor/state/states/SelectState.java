package editor.state.states;

import editor.gui.view.tab.TabView;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.settings.SettingsKey;
import editor.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import static editor.constants.Constants.TILE_SIZE;

public class SelectState implements State<TabView> {

    private Rectangle2D selectionRect;

    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        selectionRect = new Rectangle2D.Double(x, y, 0, 0);
        if (!tabView.getTabKeyListener().isShiftFlag()) {
            ((List<Tile>) tabView.getSettings().getParameter(SettingsKey.EDIT_SELECTION)).clear();
        }
        tabView.repaint();
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {
        if (selectionRect != null) {
            selectionRect.setRect(selectionRect.getX(), selectionRect.getY(), x - selectionRect.getX(), y - selectionRect.getY());
            tabView.repaint();
        }
    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {
        if (selectionRect != null) {
            List<Tile> selectedTiles = (List<Tile>) tabView.getSettings().getParameter(SettingsKey.EDIT_SELECTION);

            Rectangle2D normalizedRect = new Rectangle2D.Double(
                    Math.min(selectionRect.getX(), x),
                    Math.min(selectionRect.getY(), y),
                    Math.abs(selectionRect.getWidth()),
                    Math.abs(selectionRect.getHeight())
            );

            for (Node child : tabView.getLevel().getChildren()) {
                Tile tile = (Tile) child;
                Rectangle tileBounds = new Rectangle(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if (normalizedRect.intersects(tileBounds)) {
                    if (!selectedTiles.contains(tile)) {
                        selectedTiles.add(tile);
                    }
                }
            }
            selectionRect = null;
            tabView.repaint();
        }
    }

    public void renderSelection(Graphics g) {
        if (selectionRect != null) {
            g.setColor(new Color(0, 100, 255, 100));
            g.fillRect((int) selectionRect.getX(), (int) selectionRect.getY(), (int) selectionRect.getWidth(), (int) selectionRect.getHeight());
            g.setColor(Color.BLUE);
            g.drawRect((int) selectionRect.getX(), (int) selectionRect.getY(), (int) selectionRect.getWidth(), (int) selectionRect.getHeight());
        }
    }
}

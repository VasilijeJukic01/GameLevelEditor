package editor.state.states;

import editor.command.Command;
import editor.command.commands.MoveNodeCommand;
import editor.core.Framework;
import editor.gui.view.tab.TabView;
import editor.model.repository.components.Tile;
import editor.settings.SettingsKey;
import editor.state.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static editor.constants.Constants.TILE_SIZE;

public class MoveState implements State<TabView> {

    private Point startPoint;
    private List<Tile> selectionSnapshot;
    private List<Point> originalPositions;
    private boolean isDragging = false;

    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        List<Tile> selectedTiles = (List<Tile>) tabView.getSettings().getParameter(SettingsKey.EDIT_SELECTION);
        int tileX = x / TILE_SIZE;
        int tileY = y / TILE_SIZE;

        boolean clickedOnSelection = false;
        if (selectedTiles != null) {
            for (Tile tile : selectedTiles) {
                if (tile.getX() == tileX && tile.getY() == tileY) {
                    clickedOnSelection = true;
                    break;
                }
            }
        }

        if (clickedOnSelection) {
            isDragging = true;
            startPoint = new Point(tileX, tileY);
            selectionSnapshot = new ArrayList<>(selectedTiles);
            originalPositions = new ArrayList<>();
            for (Tile tile : selectionSnapshot) {
                originalPositions.add(new Point(tile.getX(), tile.getY()));
            }
        }
        else {
            if (selectedTiles != null) selectedTiles.clear();
            isDragging = false;
            selectionSnapshot = null;
            originalPositions = null;
            tabView.repaint();
        }
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {
        if (!isDragging || startPoint == null || selectionSnapshot == null) return;

        int currentTileX = x / TILE_SIZE;
        int currentTileY = y / TILE_SIZE;
        int dx = currentTileX - startPoint.x;
        int dy = currentTileY - startPoint.y;

        for (int i = 0; i < selectionSnapshot.size(); i++) {
            Tile tile = selectionSnapshot.get(i);
            Point originalPos = originalPositions.get(i);
            tile.setX(originalPos.x + dx);
            tile.setY(originalPos.y + dy);
        }
        tabView.repaint();
    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {
        if (!isDragging || startPoint == null || selectionSnapshot == null || originalPositions == null) {
            isDragging = false;
            return;
        }

        int finalTileX = x / TILE_SIZE;
        int finalTileY = y / TILE_SIZE;
        int dx = finalTileX - startPoint.x;
        int dy = finalTileY - startPoint.y;

        for (int i = 0; i < selectionSnapshot.size(); i++) {
            Tile tile = selectionSnapshot.get(i);
            Point originalPos = originalPositions.get(i);
            tile.setX(originalPos.x);
            tile.setY(originalPos.y);
            Command moveCommand = new MoveNodeCommand(tabView.getLevel(), tile, originalPos.x, originalPos.y, originalPos.x + dx, originalPos.y + dy);
            Framework.getInstance().getGui().getCommandManager().addCommand(moveCommand);
        }

        isDragging = false;
        startPoint = null;
        selectionSnapshot = null;
        originalPositions = null;
    }
}
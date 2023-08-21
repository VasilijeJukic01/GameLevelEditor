package editor.state.states;

import editor.gui.view.EditorFrame;
import editor.gui.view.tab.EditDialog;
import editor.gui.view.tab.TabView;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.settings.SettingsKey;
import editor.state.State;

import static editor.constants.Constants.TILE_SIZE;

public class EditState implements State<TabView> {

    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        int tileX = x / TILE_SIZE;
        int tileY = y / TILE_SIZE;
        for (Node child : tabView.getLevel().getChildren()) {
            Tile t = (Tile) child;
            if (t.getX() == tileX && t.getY() == tileY) {
                tabView.getSettings().updateParameter(SettingsKey.EDIT_SELECTION, t);
                EditDialog editDialog = new EditDialog(EditorFrame.getInstance(),true, t);
                editDialog.setVisible(true);
                return;
            }
        }
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {

    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }

}

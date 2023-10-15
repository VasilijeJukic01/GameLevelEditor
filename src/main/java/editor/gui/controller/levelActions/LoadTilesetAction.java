package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.LoadTilesetDialog;
import editor.gui.view.tab.TabView;
import editor.settings.SettingsKey;

import java.awt.event.ActionEvent;

public class LoadTilesetAction extends AbstractEditorAction {

    public LoadTilesetAction() {
        super.putValue(NAME, "Load Tileset");
        super.putValue(SHORT_DESCRIPTION, "Load Tileset");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab == null) return;
        LoadTilesetDialog dialog = new LoadTilesetDialog(EditorFrame.getInstance(), true);
        dialog.setVisible(true);
        tab.reload();
    }

}

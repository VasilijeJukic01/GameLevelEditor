package editor.gui.controller.levelActions;

import editor.core.Clipboard;
import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;
import editor.logger.LogType;
import editor.model.repository.components.Tile;
import editor.settings.SettingsKey;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class CopyAction extends AbstractEditorAction {

    public CopyAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Copy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab == null) return;

        List<Tile> selectedTiles = (List<Tile>) tab.getSettings().getParameter(SettingsKey.EDIT_SELECTION);

        if (selectedTiles != null && !selectedTiles.isEmpty()) {
            Clipboard.getInstance().copy(selectedTiles);
            Framework.getInstance().log("Copied " + selectedTiles.size() + " tiles to clipboard.", LogType.INFORMATION);
        }
        else Framework.getInstance().log("No tiles selected to copy.", LogType.WARNING);
    }
}
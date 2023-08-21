package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.OptionsDialog;
import editor.gui.view.tab.TabView;

import java.awt.event.ActionEvent;

public class OptionsAction extends AbstractEditorAction {

    public OptionsAction() {
        super.putValue(NAME, "Options");
        super.putValue(SHORT_DESCRIPTION, "Options");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        OptionsDialog dialog = new OptionsDialog(EditorFrame.getInstance(), true, tab.getLevel());
        dialog.setVisible(true);
    }

}

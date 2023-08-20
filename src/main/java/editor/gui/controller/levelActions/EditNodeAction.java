package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;

import java.awt.event.ActionEvent;

public class EditNodeAction extends AbstractEditorAction {

    public EditNodeAction() {
        super.putValue(NAME, "Edit");
        super.putValue(SHORT_DESCRIPTION, "Edit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorFrame.getInstance().getProjectView().startEditState();
        super.reset();
    }

}

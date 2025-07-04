package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractEditorAction {

    public SelectAction() {
        super.putValue(NAME, "Select");
        super.putValue(SHORT_DESCRIPTION, "Select multiple tiles");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorFrame.getInstance().getProjectView().startSelectState();
        super.reset();
    }
}

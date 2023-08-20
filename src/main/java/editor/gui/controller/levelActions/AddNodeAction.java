package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;

import java.awt.event.ActionEvent;

public class AddNodeAction extends AbstractEditorAction {

    public AddNodeAction() {
        super.putValue(NAME, "Add");
        super.putValue(SHORT_DESCRIPTION, "Add");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorFrame.getInstance().getProjectView().startAddState();
        super.reset();
    }

}

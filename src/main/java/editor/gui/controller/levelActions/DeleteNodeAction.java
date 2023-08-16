package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractEditorAction {

    public DeleteNodeAction() {
        super.putValue(NAME, "Delete");
        super.putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Delete Node Action
    }

}

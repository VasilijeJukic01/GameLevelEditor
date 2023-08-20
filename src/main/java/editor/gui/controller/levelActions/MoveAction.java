package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;

import java.awt.event.ActionEvent;

public class MoveAction extends AbstractEditorAction {

    public MoveAction() {
        super.putValue(NAME, "Move");
        super.putValue(SHORT_DESCRIPTION, "Move");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorFrame.getInstance().getProjectView().startMoveState();
        super.reset();
    }

}

package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractEditorAction {

    public DeleteNodeAction() {
        super.putValue(NAME, "Delete");
        super.putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab == null) return;
        EditorFrame.getInstance().getProjectView().startDeleteState();
        super.reset();
    }

}

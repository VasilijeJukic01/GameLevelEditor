package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import java.awt.event.ActionEvent;

public class AddNodeAction extends AbstractEditorAction {

    public AddNodeAction() {
        super.putValue(NAME, "Add");
        super.putValue(SHORT_DESCRIPTION, "Add");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab == null) return;
        EditorFrame.getInstance().getProjectView().startAddState();
        super.reset();
    }

}

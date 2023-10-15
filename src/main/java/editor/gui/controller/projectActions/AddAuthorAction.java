package editor.gui.controller.projectActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.AddAuthorView;
import editor.gui.view.EditorFrame;
import editor.model.repository.components.Project;
import editor.model.tree.mvc.TreeItem;

import java.awt.event.ActionEvent;

public class AddAuthorAction extends AbstractEditorAction {

    public AddAuthorAction() {
        super.putValue(NAME, "Add Author");
        super.putValue(SHORT_DESCRIPTION, "Add Author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem treeItem = EditorFrame.getInstance().getEditorTree().getSelectedNode();
        if(treeItem == null) return;
        if (treeItem.getNode() instanceof Project) {
            AddAuthorView addAuthorView = new AddAuthorView(EditorFrame.getInstance(), true, ((Project) treeItem.getNode()).getAuthor());
            addAuthorView.setVisible(true);
        }
    }

}

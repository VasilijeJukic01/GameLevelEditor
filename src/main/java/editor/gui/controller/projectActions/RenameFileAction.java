package editor.gui.controller.projectActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.RenameView;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.tree.mvc.TreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RenameFileAction extends AbstractEditorAction {

    public RenameFileAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Rename");
        super.putValue(SHORT_DESCRIPTION, "Rename");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem treeItem = EditorFrame.getInstance().getEditorTree().getSelectedNode();
        if(treeItem == null) return;
        if (treeItem.getNode() instanceof Project || treeItem.getNode() instanceof Level) {
            RenameView renameView = new RenameView(EditorFrame.getInstance(), true, treeItem.getNode().getName());
            renameView.setVisible(true);
        }
    }

}

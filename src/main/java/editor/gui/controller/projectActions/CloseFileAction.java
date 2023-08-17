package editor.gui.controller.projectActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.model.repository.components.ProjectExplorer;
import editor.model.tree.mvc.TreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CloseFileAction extends AbstractEditorAction {

    public CloseFileAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
        super.putValue(NAME, "Close");
        super.putValue(SHORT_DESCRIPTION, "Close");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            TreeItem treeItem = EditorFrame.getInstance().getEditorTree().getSelectedNode();
            if (treeItem.getNode() instanceof ProjectExplorer) return;
            EditorFrame.getInstance().getEditorTree().removeChild(treeItem);
        }
        catch(Exception ignored) {}
    }
}

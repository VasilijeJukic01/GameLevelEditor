package editor.gui.controller.projectActions;

import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.model.repository.components.Project;
import editor.model.tree.mvc.TreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SaveFileAction extends AbstractEditorAction {

    public SaveFileAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Save");
        super.putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem item = EditorFrame.getInstance().getEditorTree().getSelectedNode();
        if (item == null) return;
        if (!(item.getNode() instanceof Project)) return;

        Project project = (Project) item.getNode();

        if (!project.isChanged()) return;

        if (project.getPath() != null && !project.getPath().isEmpty()) {
            Framework.getInstance().getSerializer().saveProject(project);
            project.setChanged(false);
        }
    }

}

package editor.gui.controller.projectActions;

import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.model.repository.components.Project;
import editor.model.tree.mvc.TreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAsAction extends AbstractEditorAction {

    public SaveAsAction() {
        super.putValue(NAME, "Save As");
        super.putValue(SHORT_DESCRIPTION, "Save As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem item = EditorFrame.getInstance().getEditorTree().getSelectedNode();
        if (item == null) return;
        if (!(item.getNode() instanceof Project)) return;

        Project project = (Project) item.getNode();
        JFileChooser fileChooser = new JFileChooser();
        File projectFile = null;

        if (fileChooser.showSaveDialog(EditorFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            projectFile = fileChooser.getSelectedFile();
            project.setPath(projectFile.getPath());
        }
        if (projectFile == null || projectFile.getPath().equals("")) return;
        Framework.getInstance().getSerializer().saveProject(project);
        project.setChanged(false);
    }

}

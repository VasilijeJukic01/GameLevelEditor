package editor.model.tree.mvc;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class TreeView extends JTree {

    public TreeView(DefaultTreeModel dftTreeModel) {
        super.setModel(dftTreeModel);
        TreeCellRenderer treeCellRenderer = new TreeCellRenderer();
        super.addTreeSelectionListener(new EditorTreeSelectionListener());
        super.setCellEditor(new TreeCellEditor(this, treeCellRenderer));
        super.setCellRenderer(treeCellRenderer);
        super.setEditable(true);
    }
}

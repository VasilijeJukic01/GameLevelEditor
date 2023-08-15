package editor.model.tree.mvc;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class TreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn = null;
    private JTextField tfEdit;

    public TreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object cell, boolean selectionHighlight, boolean isExpanded, boolean isLeaf, int row) {
        clickedOn = cell;
        tfEdit = new JTextField(cell.toString());
        tfEdit.addActionListener(this);
        return tfEdit;
    }

    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent) {
            return ((MouseEvent) e).getClickCount() == 3;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (!(clickedOn instanceof TreeItem)) return;
        ((TreeItem) clickedOn).setName(e.getActionCommand());
    }

    public Object getClickedOn() {
        return clickedOn;
    }

    public void setClickedOn(Object clickedOn) {
        this.clickedOn = clickedOn;
    }

    public JTextField getTfEdit() {
        return tfEdit;
    }

    public void setTfEdit(JTextField tfEdit) {
        this.tfEdit = tfEdit;
    }
}

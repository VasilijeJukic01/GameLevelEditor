package editor.model.tree.mvc;

import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.repository.components.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

import static editor.constants.Constants.EXPLORER_COLOR;
import static editor.constants.Constants.TREE_SELECTION_COLOR;
import static editor.constants.FilePaths.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    public Component getTreeCellRendererComponent(JTree tree, Object cell, boolean selectionHighlight, boolean isExpanded, boolean isLeaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, cell, selectionHighlight, isExpanded, isLeaf, row, hasFocus);

        Node node = ((TreeItem) cell).getNode();
        Icon icon = getIconForNode(node);
        setIcon(icon);

        return this;
    }

    private Icon getIconForNode(Node node) {
        String iconPath = null;

        if (node instanceof ProjectExplorer) iconPath = TREE_EXPLORER_ICON;
        else if (node instanceof Project) iconPath = TREE_PROJECT_ICON;
        else if (node instanceof Level) iconPath = TREE_LEVEL_ICON;

        if (iconPath != null) {
            URL imageURL = getClass().getResource(iconPath);
            if (imageURL != null) {
                return new ImageIcon(imageURL);
            }
        }

        return null;
    }

    @Override
    public Color getBackgroundNonSelectionColor() {
        return null;
    }

    @Override
    public Color getBackgroundSelectionColor() {
        return TREE_SELECTION_COLOR;
    }

    @Override
    public Color getBackground() {
        return EXPLORER_COLOR;
    }

}

package editor.model.tree;

import editor.model.repository.Node;
import editor.model.repository.components.ProjectExplorer;
import editor.model.tree.treeObserver.TreePublisher;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public interface Tree<U extends DefaultMutableTreeNode, V extends JTree> extends TreePublisher {

    V generateTree(ProjectExplorer projectExplorer);

    void addChild(U parent);

    void addChild();

    void removeChild(U child);

    U getSelectedNode();

    void setSelectedNode(Node node);

    void clearSelection();

    void deserialize(Node node);

}

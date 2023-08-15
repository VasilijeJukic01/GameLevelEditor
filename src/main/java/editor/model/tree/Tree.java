package editor.model.tree;

import editor.model.repository.Node;
import editor.model.repository.components.ProjectExplorer;
import editor.model.tree.mvc.TreeItem;
import editor.model.tree.mvc.TreeView;

public interface Tree {

    TreeView generateTree(ProjectExplorer projectExplorer);

    void addChild(TreeItem parent);

    void removeChild(TreeItem child);

    TreeItem getSelectedNode();

    void setSelectedNode(Node node);

    void clearSelection();

}

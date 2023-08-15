package editor.model.tree;

import editor.model.repository.Node;
import editor.model.repository.NodeComposite;
import editor.model.repository.components.Component;
import editor.model.repository.components.Level;
import editor.model.repository.components.ProjectExplorer;
import editor.model.repository.factory.Factory;
import editor.model.repository.factory.NodeFactory;
import editor.model.tree.mvc.TreeItem;
import editor.model.tree.mvc.TreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("unchecked")
public class EditorTree implements Tree {

    private TreeView treeView;
    private DefaultTreeModel treeModel;

    private final NodeFactory factory = new Factory();

    @Override
    public TreeView generateTree(ProjectExplorer projectExplorer) {
        TreeItem root = new TreeItem(projectExplorer);
        this.treeModel = new DefaultTreeModel(root);
        this.treeView = new TreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(TreeItem parent) {
        if(parent == null || parent.getNode() instanceof Level || !(parent.getNode() instanceof NodeComposite)) return;
        Node child = factory.create(parent.getNode());
        parent.add(new TreeItem(child));
        ((NodeComposite<Node>) parent.getNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeChild(TreeItem child) {
        if(child.getNode() instanceof ProjectExplorer || child.getNode() instanceof Component)
            return;

        ((TreeItem) child.getParent()).remove(child);
        NodeComposite<Node> parent = (NodeComposite<Node>) child.getNode().getParent();
        parent.removeChild(child.getNode());

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        clearSelection();
    }

    @Override
    public TreeItem getSelectedNode() {
        return (TreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void setSelectedNode(Node node) {

    }

    @Override
    public void clearSelection() {
        treeView.setSelectionRow(0);
    }

}

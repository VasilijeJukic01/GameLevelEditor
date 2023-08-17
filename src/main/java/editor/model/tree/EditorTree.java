package editor.model.tree;

import editor.model.repository.Node;
import editor.model.repository.Composite;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.ProjectExplorer;
import editor.model.repository.factory.Factory;
import editor.model.repository.factory.NodeFactory;
import editor.model.tree.mvc.TreeItem;
import editor.model.tree.mvc.TreeView;
import editor.model.tree.treeObserver.TreeSubscriber;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class EditorTree implements Tree<TreeItem, TreeView> {

    private TreeView treeView;
    private DefaultTreeModel treeModel;

    private final NodeFactory factory = new Factory();

    private List<TreeSubscriber> subscribers;

    @Override
    public TreeView generateTree(ProjectExplorer projectExplorer) {
        TreeItem root = new TreeItem(projectExplorer);
        this.treeModel = new DefaultTreeModel(root);
        this.treeView = new TreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(TreeItem parent) {
        if(parent == null || parent.getNode() instanceof Level || !(parent.getNode() instanceof Composite)) return;

        Node child = factory.create(parent.getNode());
        parent.add(new TreeItem(child));
        ((Composite<Node>) parent.getNode()).addChild(child);
        refreshView();
        notifyAdd(child);
    }

    @Override
    public void addChild() {
        TreeItem parent = getSelectedNode();
        addChild(parent);
    }

    @Override
    public void removeChild(TreeItem child) {
        if(child.getNode() instanceof ProjectExplorer || child.getNode() instanceof Tile) return;

        ((TreeItem) child.getParent()).remove(child);
        Composite<Node> parent = (Composite<Node>) child.getNode().getParent();
        parent.removeChild(child.getNode());
        clearSelection();
        refreshView();
        notifyRemove(child.getNode());
    }

    private void refreshView() {
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
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

    // Observer
    @Override
    public void addSubscriberTree(TreeSubscriber s) {
        if(s == null) return;
        if(this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(s)) return;
        this.subscribers.add(s);
    }

    @Override
    public void removeSubscriberTree(TreeSubscriber s) {
        if(s == null ||  this.subscribers == null || !this.subscribers.contains(s)) return;
        this.subscribers.remove(s);
    }

    @Override
    public <T> void notifyRemove(T t) {
        if(t == null || this.subscribers == null || this.subscribers.isEmpty()) return;
        this.subscribers.forEach(s -> s.updateRemoved(t));
    }

    @Override
    public <T> void notifyAdd(T t) {
        if (t == null || this.subscribers == null || this.subscribers.isEmpty()) return;
        this.subscribers.forEach(s -> s.updateAdded(t));
    }
}

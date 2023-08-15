package editor.model.tree.mvc;

import editor.model.repository.Node;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeItem extends DefaultMutableTreeNode {

    private Node node;

    public TreeItem(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return node.getName();
    }


    // Getters & Setters
    public void setName(String name) {
        this.node.setName(name);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}

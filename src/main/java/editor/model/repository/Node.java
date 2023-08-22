package editor.model.repository;

public abstract class Node {

    private String name;
    private transient Node parent;

    private transient boolean changed = true;

    private final String type = getClass().getName();

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}

package editor.model.repository;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite<T extends Node> extends Node {

    private final List<T> children = new ArrayList<>();

    public Composite(String name, Node parent) {
        super(name, parent);
    }

    public void setName(String name) {
        super.setName(name);
    }

    public List<T> getChildren() {
        return children;
    }

    public void addChild(T child) {
        if (!children.contains(child)) {
            children.add(child);
            setChanged(true);
        }
    }

    public void removeChild(T child) {
        children.remove(child);
        setChanged(true);
    }

}

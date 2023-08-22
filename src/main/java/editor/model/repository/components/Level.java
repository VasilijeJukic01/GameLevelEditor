package editor.model.repository.components;

import editor.model.repository.Node;
import editor.model.repository.Composite;
import editor.model.repository.nodeObserver.NodePublisher;
import editor.model.repository.nodeObserver.NodeSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Level extends Composite<Node> implements NodePublisher {

    private int width, height;

    private transient List<NodeSubscriber> subscribers;

    public Level(String name, Node parent) {
        super(name, parent);
        this.width = this.height = 30;
    }

    public Level(String name, Node parent, int width, int height) {
        super(name, parent);
        this.width = width;
        this.height = height;
    }

    @Override
    public void setName(String text) {
        Optional.ofNullable(text)
                .filter(str -> !str.isEmpty())
                .ifPresent(super::setName);
        super.getParent().setChanged(true);
        notify(this);
    }

    @Override
    public void addChild(Node child) {
        this.getChildren().add(child);
        super.getParent().setChanged(true);
        notify(this);
    }

    @Override
    public void removeChild(Node child) {
        this.getChildren().remove(child);
        super.getParent().setChanged(true);
        notify(this);
    }

    // Observer
    @Override
    public void addSubscriber(NodeSubscriber s) {
        if(s == null) return;
        if(this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(s)) return;
        this.subscribers.add(s);
    }

    @Override
    public void removeSubscriber(NodeSubscriber s) {
        if(s == null ||  this.subscribers == null || !this.subscribers.contains(s)) return;
        this.subscribers.remove(s);
    }

    @Override
    public <T> void notify(T t) {
        if (t == null || this.subscribers == null || this.subscribers.isEmpty()) return;
        this.subscribers.forEach(s -> s.updateNode(t));
    }

    // Getters & Setters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        notify(this);
    }

}

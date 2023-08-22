package editor.model.repository.components;

import editor.model.repository.Node;
import editor.model.repository.Composite;
import editor.model.repository.nodeObserver.NodePublisher;
import editor.model.repository.nodeObserver.NodeSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Project extends Composite<Node> implements NodePublisher {

    private String author;
    private String path;

    private transient List<NodeSubscriber> subscribers;

    public Project(String name, Node parent, String author, String path) {
        super(name, parent);
        this.author = author;
        this.path = path;
    }

    public Project(String name, Node parent) {
        super(name, parent);
        this.author = "Default";
        this.path = "/";
    }

    @Override
    public void setName(String text) {
        Optional.ofNullable(text)
                .filter(str -> !str.isEmpty())
                .ifPresent(super::setName);
        setChanged(true);
        notify(this);
    }

    // Getters & Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        Optional.ofNullable(author)
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> this.author = str);
        setChanged(true);
        notify(this);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
}

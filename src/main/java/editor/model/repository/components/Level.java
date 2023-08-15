package editor.model.repository.components;

import editor.model.repository.Node;
import editor.model.repository.Composite;
import editor.model.repository.nodeObserver.NodePublisher;
import editor.model.repository.nodeObserver.NodeSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Level extends Composite<Node> implements NodePublisher {

    private transient List<NodeSubscriber> subscribers;

    public Level(String name, Node parent) {
        super(name, parent);
    }

    @Override
    public void setName(String text) {
        Optional.ofNullable(text)
                .filter(str -> !str.isEmpty())
                .ifPresent(super::setName);
        notify(this);
    }

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

package editor.model.repository.nodeObserver;

public interface NodePublisher {

    void addSubscriber(NodeSubscriber s);

    void removeSubscriber(NodeSubscriber s);

    <T> void notify(T t);

}

package editor.model.repository.nodeObserver;

public interface NodeSubscriber {

    <T> void updateNode(T t);

}

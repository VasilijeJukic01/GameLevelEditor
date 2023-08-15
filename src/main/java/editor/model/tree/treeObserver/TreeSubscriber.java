package editor.model.tree.treeObserver;

public interface TreeSubscriber {

    <T> void updateRemoved(T t);

    <T> void updateAdded(T t);

}

package editor.model.tree.treeObserver;

public interface TreePublisher {

    void addSubscriberTree(TreeSubscriber s);

    void removeSubscriberTree(TreeSubscriber s);

    <T> void notifyRemove(T t);

    <T> void notifyAdd(T t);

}

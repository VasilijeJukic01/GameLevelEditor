package editor.model.repository.components;

import editor.model.repository.Node;
import editor.model.repository.NodeComposite;

import java.util.Optional;

public class Level extends NodeComposite<Node> {

    public Level(String name, Node parent) {
        super(name, parent);
    }

    public void setName(String text) {
        Optional.ofNullable(text)
                .filter(str -> !str.isEmpty())
                .ifPresent(super::setName);
    }

}

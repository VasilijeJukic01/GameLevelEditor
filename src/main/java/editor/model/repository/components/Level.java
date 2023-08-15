package editor.model.repository.components;

import editor.model.repository.Node;
import editor.model.repository.NodeComposite;

public class Level extends NodeComposite<Node> {

    public Level(String name, Node parent) {
        super(name, parent);
    }

    public void setName(String text) {
        if(text == null || text.equals("")) return;
        super.setName(text);
    }

}

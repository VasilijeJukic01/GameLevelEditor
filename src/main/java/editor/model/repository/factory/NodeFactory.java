package editor.model.repository.factory;

import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;

public interface NodeFactory {

    Project createProject(String name, Node parent);

    Level createLevel(String name, Node parent);

    Tile createComponent(String name, Node parent);

    Node create(Node parent);

}

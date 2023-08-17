package editor.model.repository.factory;

import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.repository.components.ProjectExplorer;

import static editor.constants.Constants.*;

public class Factory implements NodeFactory {

    @Override
    public Project createProject(String name, Node parent) {
        return new Project(name, parent);
    }

    @Override
    public Level createLevel(String name, Node parent) {
        return new Level(name, parent);
    }

    @Override
    public Tile createComponent(String name, Node parent) {
        return new Tile(name, parent);
    }

    @Override
    public Node create(Node parent) {
        if (parent instanceof ProjectExplorer) return createProject("Project "+(PROJECT_NUM++), parent);
        if (parent instanceof Project) return createLevel("Level "+(LEVEL_NUM++), parent);
        if (parent instanceof Level) return createComponent("Component "+(COMPONENT_NUM++), parent);
        return null;
    }

}

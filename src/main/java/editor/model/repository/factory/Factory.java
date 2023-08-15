package editor.model.repository.factory;

import editor.model.repository.Node;
import editor.model.repository.components.Component;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.repository.components.ProjectExplorer;

public class Factory implements NodeFactory {

    @Override
    public ProjectExplorer createProjectExplorer(String name, Node parent) {
        return new ProjectExplorer(name, parent);
    }

    @Override
    public Project createProject(String name, Node parent) {
        return new Project(name, parent);
    }

    @Override
    public Level createLevel(String name, Node parent) {
        return new Level(name, parent);
    }

    @Override
    public Component createComponent(String name, Node parent) {
        return new Component(name, parent);
    }

    @Override
    public Node create(Node parent) {
        if (parent instanceof ProjectExplorer) return createProject("", parent);
        if (parent instanceof Project) return createLevel("", parent);
        if (parent instanceof Level) return createComponent("", parent);
        return null;
    }

}

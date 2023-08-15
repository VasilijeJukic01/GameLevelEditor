package editor.model.repository.factory;

import editor.model.repository.Node;
import editor.model.repository.components.Component;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.repository.components.ProjectExplorer;

public interface NodeFactory {

    ProjectExplorer createProjectExplorer(String name, Node parent);

    Project createProject(String name, Node parent);

    Level createLevel(String name, Node parent);

    Component createComponent(String name, Node parent);

    Node create(Node parent);

}

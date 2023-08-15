package editor.model.repository;

import editor.core.Repository;
import editor.model.repository.components.ProjectExplorer;

public class LevelRepository implements Repository {

    private final ProjectExplorer projectExplorer;

    public LevelRepository() {
        this.projectExplorer = new ProjectExplorer("Project Explorer", null);
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

}

package editor.core;

import editor.command.CommandManager;
import editor.gui.view.ProjectView;

public interface Gui {

    void start();

    CommandManager getCommandManager();

    ProjectView getProjectView();

}

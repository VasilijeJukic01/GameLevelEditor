package editor.gui;

import editor.command.CommandManager;
import editor.core.Gui;
import editor.gui.view.EditorFrame;
import editor.gui.view.ProjectView;

public class SwingGui implements Gui {

    private final CommandManager commandManager;

    public SwingGui() {
        this.commandManager = new CommandManager();
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public ProjectView getProjectView() {
        return EditorFrame.getInstance().getProjectView();
    }

    @Override
    public void start() {
        EditorFrame.getInstance().setVisible(true);
    }
}

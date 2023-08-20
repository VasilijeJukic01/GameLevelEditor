package editor.gui;

import editor.command.CommandManager;
import editor.core.Gui;
import editor.gui.view.EditorFrame;

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
    public void start() {
        EditorFrame.getInstance().setVisible(true);
    }
}

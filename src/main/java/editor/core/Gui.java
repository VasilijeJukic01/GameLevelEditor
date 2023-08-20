package editor.core;

import editor.command.CommandManager;

public interface Gui {

    void start();

    CommandManager getCommandManager();

}

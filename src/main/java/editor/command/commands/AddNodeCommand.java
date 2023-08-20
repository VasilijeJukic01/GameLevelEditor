package editor.command.commands;

import editor.command.Command;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;

public class AddNodeCommand implements Command {

    private final Level level;
    private final Tile tile;

    public AddNodeCommand(Level level, Tile tile) {
        this.level = level;
        this.tile = tile;
    }

    @Override
    public void execute() {
        if(tile == null ||  level == null) return;
        level.addChild(tile);
    }

    @Override
    public void undo() {
        if(tile == null ||  level == null) return;
        level.removeChild(tile);
    }

}

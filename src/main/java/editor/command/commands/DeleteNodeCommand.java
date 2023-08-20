package editor.command.commands;

import editor.command.Command;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;

public class DeleteNodeCommand implements Command {

    private final Level level;
    private final Tile tile;

    public DeleteNodeCommand(Level level, Tile tile) {
        this.level = level;
        this.tile = tile;
    }

    @Override
    public void execute() {
        if(tile == null ||  level == null) return;
        level.removeChild(tile);
    }

    @Override
    public void undo() {
        if(tile == null ||  level == null) return;
        level.addChild(tile);
    }

}

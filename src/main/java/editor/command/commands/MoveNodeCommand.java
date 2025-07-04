package editor.command.commands;

import editor.command.Command;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;

public class MoveNodeCommand implements Command {

    private final Level level;
    private final Tile tile;
    private final int oldX, oldY;
    private final int newX, newY;

    public MoveNodeCommand(Level level, Tile tile, int oldX, int oldY, int newX, int newY) {
        this.level = level;
        this.tile = tile;
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public void execute() {
        if (tile == null || level == null) return;
        tile.setX(newX);
        tile.setY(newY);
        level.notify(tile);
    }

    @Override
    public void undo() {
        if (tile == null || level == null) return;
        tile.setX(oldX);
        tile.setY(oldY);
        level.notify(tile);
    }
}
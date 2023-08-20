package editor.command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final List<Command> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(Command command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        execute();
    }

    public void execute(){
        if(currentCommand < commands.size())
            commands.get(currentCommand++).execute();
    }

    public void undo(){
        if(currentCommand > 0)
            commands.get(--currentCommand).undo();
    }

}


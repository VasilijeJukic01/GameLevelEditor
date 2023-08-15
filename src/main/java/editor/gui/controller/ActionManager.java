package editor.gui.controller;

import editor.gui.controller.actions.ExitAction;
import editor.gui.controller.actions.NewFileAction;
import editor.gui.controller.actions.OpenFileAction;

public class ActionManager {

    private ExitAction exitAction;
    private NewFileAction newAction;

    private OpenFileAction openAction;

    public ActionManager() {
        init();
    }

    private void init() {
        this.exitAction = new ExitAction();
        this.newAction = new NewFileAction();
        this.openAction = new OpenFileAction();
    }

    public NewFileAction getNewAction() {
        return newAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public OpenFileAction getOpenAction() {
        return openAction;
    }
}

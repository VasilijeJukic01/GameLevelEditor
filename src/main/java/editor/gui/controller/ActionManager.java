package editor.gui.controller;

import editor.gui.controller.levelActions.AddNodeAction;
import editor.gui.controller.levelActions.DeleteNodeAction;
import editor.gui.controller.levelActions.EditNodeAction;
import editor.gui.controller.levelActions.ImportLevelAction;
import editor.gui.controller.projectActions.ExitAction;
import editor.gui.controller.projectActions.NewFileAction;
import editor.gui.controller.projectActions.OpenFileAction;
import editor.gui.controller.levelActions.ShowGridAction;

public class ActionManager {

    // Project Actions
    private ExitAction exitAction;
    private NewFileAction newAction;
    private OpenFileAction openAction;

    // Editor Actions
    private ImportLevelAction importLevelAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private EditNodeAction editNodeAction;
    private ShowGridAction showGridAction;

    public ActionManager() {
        initProjectActions();
        initEditorActions();
    }

    private void initProjectActions() {
        this.exitAction = new ExitAction();
        this.newAction = new NewFileAction();
        this.openAction = new OpenFileAction();
    }

    private void initEditorActions() {
        this.importLevelAction = new ImportLevelAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.editNodeAction = new EditNodeAction();
        this.showGridAction = new ShowGridAction();
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

    public ImportLevelAction getImportLevelAction() {
        return importLevelAction;
    }

    public AddNodeAction getAddNodeAction() {
        return addNodeAction;
    }

    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }

    public EditNodeAction getEditNodeAction() {
        return editNodeAction;
    }

    public ShowGridAction getShowGridAction() {
        return showGridAction;
    }
}

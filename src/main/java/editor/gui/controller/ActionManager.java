package editor.gui.controller;

import editor.gui.controller.levelActions.*;
import editor.gui.controller.projectActions.CloseFileAction;
import editor.gui.controller.projectActions.ExitAction;
import editor.gui.controller.projectActions.NewFileAction;
import editor.gui.controller.projectActions.OpenFileAction;

public class ActionManager {

    // Project Actions
    private ExitAction exitAction;
    private NewFileAction newAction;
    private OpenFileAction openAction;
    private CloseFileAction closeAction;

    // Editor Actions
    private ImportLevelAction importLevelAction;
    private AddNodeAction addNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private EditNodeAction editNodeAction;
    private ShowGridAction showGridAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;

    public ActionManager() {
        initProjectActions();
        initEditorActions();
    }

    private void initProjectActions() {
        this.exitAction = new ExitAction();
        this.newAction = new NewFileAction();
        this.openAction = new OpenFileAction();
        this.closeAction = new CloseFileAction();
    }

    private void initEditorActions() {
        this.importLevelAction = new ImportLevelAction();
        this.addNodeAction = new AddNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.editNodeAction = new EditNodeAction();
        this.showGridAction = new ShowGridAction();
        this.zoomInAction = new ZoomInAction();
        this.zoomOutAction = new ZoomOutAction();
    }

    // Project Actions Getters
    public NewFileAction getNewAction() {
        return newAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public OpenFileAction getOpenAction() {
        return openAction;
    }

    public CloseFileAction getCloseAction() {
        return closeAction;
    }

    // Editor Actions Getters
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

    public ZoomInAction getZoomInAction() {
        return zoomInAction;
    }

    public ZoomOutAction getZoomOutAction() {
        return zoomOutAction;
    }
}

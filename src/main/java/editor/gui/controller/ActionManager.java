package editor.gui.controller;

import editor.gui.controller.levelActions.*;
import editor.gui.controller.projectActions.*;

import java.util.HashMap;
import java.util.Map;

public class ActionManager {

    private final Map<ActionType, AbstractEditorAction> actions = new HashMap<>();

    public ActionManager() {
        initProjectActions();
        initEditorActions();
    }

    private void initProjectActions() {
        actions.put(ActionType.EXIT, new ExitAction());
        actions.put(ActionType.NEW_FILE, new NewFileAction());
        actions.put(ActionType.OPEN_FILE, new OpenFileAction());
        actions.put(ActionType.SAVE_FILE, new SaveFileAction());
        actions.put(ActionType.SAVE_AS_FILE, new SaveAsAction());
        actions.put(ActionType.CLOSE_FILE, new CloseFileAction());
        actions.put(ActionType.RENAME_FILE, new RenameFileAction());
        actions.put(ActionType.ADD_AUTHOR, new AddAuthorAction());
        actions.put(ActionType.HELP, new HelpAction());
    }

    private void initEditorActions() {
        actions.put(ActionType.IMPORT, new ImportLevelAction());
        actions.put(ActionType.EXPORT, new ExportLevelAction());
        actions.put(ActionType.ADD_NODE, new AddNodeAction());
        actions.put(ActionType.DELETE_NODE, new DeleteNodeAction());
        actions.put(ActionType.EDIT_NODE, new EditNodeAction());
        actions.put(ActionType.MOVE, new MoveAction());
        actions.put(ActionType.SHOW_GRID, new ShowGridAction());
        actions.put(ActionType.ZOOM_IN, new ZoomInAction());
        actions.put(ActionType.ZOOM_OUT, new ZoomOutAction());
        actions.put(ActionType.UNDO, new UndoAction());
        actions.put(ActionType.REDO, new RedoAction());
        actions.put(ActionType.SCREENSHOT, new ScreenshotAction());
        actions.put(ActionType.OPTIONS, new OptionsAction());
        actions.put(ActionType.BACKGROUND, new BackgroundAction());
        actions.put(ActionType.LOAD_TILESET, new LoadTilesetAction());
    }

    public AbstractEditorAction getAction(ActionType type) {
        return actions.get(type);
    }
}

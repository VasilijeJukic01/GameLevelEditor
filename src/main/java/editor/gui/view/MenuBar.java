package editor.gui.view;

import editor.gui.controller.ActionManager;
import editor.gui.controller.ActionType;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        ActionManager actionManager = EditorFrame.getInstance().getActionManager();

        JMenu mFile = new JMenu("File");
        mFile.add(actionManager.getAction(ActionType.NEW_FILE));
        mFile.add(actionManager.getAction(ActionType.OPEN_FILE));
        mFile.add(actionManager.getAction(ActionType.CLOSE_FILE));
        mFile.add(actionManager.getAction(ActionType.EXIT));
        this.add(mFile);

        JMenu mEdit = new JMenu("Edit");
        mEdit.add(actionManager.getAction(ActionType.RENAME_FILE));
        mEdit.add(actionManager.getAction(ActionType.SHOW_GRID));
        mEdit.add(actionManager.getAction(ActionType.UNDO));
        mEdit.add(actionManager.getAction(ActionType.REDO));
        this.add(mEdit);

        this.add(new JMenu("Help"));
    }

}

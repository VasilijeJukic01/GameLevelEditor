package editor.gui.view;

import editor.gui.controller.ActionType;

import javax.swing.*;

public class ToolBar extends JToolBar {

    public ToolBar() {
        super(SwingConstants.HORIZONTAL);
        super.setFloatable(false);

        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.NEW_FILE).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.OPEN_FILE).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.SAVE_FILE).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.SAVE_AS_FILE).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.CLOSE_FILE).createButton());
        this.addSeparator();
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.ADD_AUTHOR).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.RENAME_FILE).createButton());
        this.addSeparator();
        this.add(Box.createHorizontalGlue());
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.EXIT).createButton());
    }

}

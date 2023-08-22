package editor.gui.view.tab;

import editor.gui.controller.ActionType;
import editor.gui.view.EditorFrame;

import javax.swing.*;

import static editor.constants.Constants.TAB_TOOLBAR_COLOR;

public class TabToolBar extends JToolBar {

    public TabToolBar() {
        super(SwingConstants.HORIZONTAL);
        super.setFloatable(false);
        this.setBackground(TAB_TOOLBAR_COLOR);

        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.IMPORT).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.EXPORT).createButton());
        this.addSeparator();
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.ADD_NODE).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.DELETE_NODE).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.EDIT_NODE).createButton());
        this.addSeparator();
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.MOVE).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.ZOOM_IN).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.ZOOM_OUT).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.UNDO).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.REDO).createButton());
        this.add(Box.createHorizontalGlue());
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.OPTIONS).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.LOAD_TILESET).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.BACKGROUND).createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getAction(ActionType.SCREENSHOT).createButton());
    }

}

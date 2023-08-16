package editor.gui.view.tab;

import editor.gui.view.EditorFrame;

import javax.swing.*;

import static editor.constants.Constants.TAB_TOOLBAR_COLOR;

public class TabToolBar extends JToolBar {

    public TabToolBar() {
        super(SwingConstants.HORIZONTAL);
        super.setFloatable(false);
        this.setBackground(TAB_TOOLBAR_COLOR);

        this.add(EditorFrame.getInstance().getActionManager().getAddNodeAction().createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getDeleteNodeAction().createButton());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getEditNodeAction().createButton());
    }

}

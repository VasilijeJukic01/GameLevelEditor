package editor.gui.view.tab;

import javax.swing.*;

import static editor.constants.Constants.TAB_TOOLBAR_COLOR;

public class TabToolBar extends JToolBar {

    public TabToolBar() {
        super(SwingConstants.HORIZONTAL);
        super.setFloatable(false);
        this.setBackground(TAB_TOOLBAR_COLOR);
    }

}

package editor.gui.view;

import javax.swing.*;

public class ToolBar extends JToolBar {

    public ToolBar() {
        super(SwingConstants.HORIZONTAL);
        super.setFloatable(false);

        this.add(EditorFrame.getInstance().getActionManager().getOpenAction());
        this.addSeparator();
        this.add(EditorFrame.getInstance().getActionManager().getNewAction());
        this.addSeparator();
        this.add(Box.createHorizontalGlue());
        this.add(EditorFrame.getInstance().getActionManager().getExitAction());
    }

}

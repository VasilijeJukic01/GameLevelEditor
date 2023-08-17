package editor.gui.view;

import editor.gui.controller.ActionManager;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar() {
        ActionManager actionManager = EditorFrame.getInstance().getActionManager();

        JMenu mFile = new JMenu("File");
        mFile.add(actionManager.getOpenAction());
        mFile.add(actionManager.getNewAction());
        mFile.add(actionManager.getExitAction());
        this.add(mFile);

        JMenu mEdit = new JMenu("Edit");
        mEdit.add(actionManager.getShowGridAction());
        this.add(mEdit);

        this.add(new JMenu("Help"));
    }
}

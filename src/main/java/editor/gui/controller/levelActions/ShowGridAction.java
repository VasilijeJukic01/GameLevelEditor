package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ShowGridAction extends AbstractEditorAction {

    public ShowGridAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Show Grid");
        super.putValue(SHORT_DESCRIPTION, "Show Grid");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView currentTab = EditorFrame.getInstance().getCurrentTab();
        currentTab.getLevel().changeGrid();
    }

}

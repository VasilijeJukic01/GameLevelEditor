package editor.gui.controller.actions;

import editor.gui.controller.AbstractEditorAction;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class OpenFileAction extends AbstractEditorAction {

    public OpenFileAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Open");
        super.putValue(SHORT_DESCRIPTION, "Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Open File Action
    }

}

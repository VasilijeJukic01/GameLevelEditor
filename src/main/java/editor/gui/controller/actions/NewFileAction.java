package editor.gui.controller.actions;

import editor.gui.controller.AbstractEditorAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static editor.constants.FilePaths.NEW_FILE_ICON;

public class NewFileAction extends AbstractEditorAction {

    public NewFileAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "New");
        super.putValue(SHORT_DESCRIPTION, "New");
        super.putValue(SMALL_ICON, loadIcon(NEW_FILE_ICON).orElse(null));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: New File Action
    }

}

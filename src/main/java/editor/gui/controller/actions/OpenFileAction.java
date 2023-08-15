package editor.gui.controller.actions;

import editor.gui.controller.AbstractEditorAction;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static editor.constants.FilePaths.OPEN_FILE_ICON;

public class OpenFileAction extends AbstractEditorAction {

    public OpenFileAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Open");
        super.putValue(SHORT_DESCRIPTION, "Open");
        super.putValue(SMALL_ICON, loadIcon(OPEN_FILE_ICON).orElse(null));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Open File Action
    }

}

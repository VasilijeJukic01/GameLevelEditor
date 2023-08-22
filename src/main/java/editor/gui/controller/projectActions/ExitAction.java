package editor.gui.controller.projectActions;

import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.logger.LogType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractEditorAction {

    public ExitAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        super.putValue(NAME, "Exit");
        super.putValue(SHORT_DESCRIPTION, "Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Framework.getInstance().log("Destroying!", LogType.WARNING);
        System.exit(0);
    }

}


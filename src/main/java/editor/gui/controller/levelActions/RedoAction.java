package editor.gui.controller.levelActions;

import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractEditorAction {

    public RedoAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Redo");
        super.putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Framework.getInstance().getGui().getCommandManager().execute();
    }

}

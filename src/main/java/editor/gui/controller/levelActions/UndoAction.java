package editor.gui.controller.levelActions;

import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractEditorAction {

    public UndoAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Undo");
        super.putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Framework.getInstance().getGui().getCommandManager().undo();
    }
}

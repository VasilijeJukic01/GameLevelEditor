package editor.gui.controller.projectActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewFileAction extends AbstractEditorAction {

    public NewFileAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "New");
        super.putValue(SHORT_DESCRIPTION, "New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorFrame.getInstance().getEditorTree().addChild();
    }

}

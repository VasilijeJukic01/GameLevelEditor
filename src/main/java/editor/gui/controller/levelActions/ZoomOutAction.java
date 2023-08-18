package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ZoomOutAction extends AbstractEditorAction {

    public ZoomOutAction() {
        super.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, InputEvent.CTRL_DOWN_MASK));
        super.putValue(NAME, "Zoom Out");
        super.putValue(SHORT_DESCRIPTION, "Zoom Out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab != null) {
            double scale = tab.getScale();
            if (scale / 1.2 < 0.5) scale = 0.5;
            else scale /= 1.2;
            tab.setScale(scale);
        }
    }

}

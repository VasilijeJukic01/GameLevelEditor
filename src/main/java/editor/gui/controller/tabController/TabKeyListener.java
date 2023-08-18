package editor.gui.controller.tabController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TabKeyListener implements KeyListener {

    private boolean ctrlFlag = false;

    public TabKeyListener() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrlFlag = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrlFlag = false;
        }
    }

    public boolean isCtrlFlag() {
        return ctrlFlag;
    }
}

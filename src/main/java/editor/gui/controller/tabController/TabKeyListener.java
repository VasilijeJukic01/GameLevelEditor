package editor.gui.controller.tabController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TabKeyListener implements KeyListener {

    private boolean ctrlFlag = false;
    private boolean shiftFlag = false;

    public TabKeyListener() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) ctrlFlag = true;
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftFlag = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) ctrlFlag = false;
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftFlag = false;
    }

    public boolean isCtrlFlag() {
        return ctrlFlag;
    }

    public boolean isShiftFlag() {
        return shiftFlag;
    }
}

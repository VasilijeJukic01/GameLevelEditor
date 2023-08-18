package editor.gui.controller;

import editor.gui.view.tab.TabView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EditorKeyListener implements KeyListener {

    private boolean ctrlFlag = false;

    public EditorKeyListener() {
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

package editor.gui.controller;

import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import java.awt.event.*;
import java.util.function.BiConsumer;

public class MouseController implements MouseListener, MouseMotionListener {

    private final TabView tabView;

    public MouseController(TabView tabView) {
        this.tabView = tabView;
    }

    private void performAction(MouseEvent e, BiConsumer<Integer, Integer> action) {
        int scaledX = (int) (e.getX() / tabView.getScale() + (tabView.getDx() / tabView.getScale()));
        int scaledY = (int) (e.getY() / tabView.getScale() + (tabView.getDy() / tabView.getScale()));
        action.accept(scaledX, scaledY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            performAction(e, (x, y) -> EditorFrame.getInstance().getProjectView().clickPerform(x, y, tabView));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        performAction(e, (x, y) -> EditorFrame.getInstance().getProjectView().releasePerform(x, y, tabView));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        performAction(e, (x, y) -> EditorFrame.getInstance().getProjectView().dragPerform(x, y, tabView));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

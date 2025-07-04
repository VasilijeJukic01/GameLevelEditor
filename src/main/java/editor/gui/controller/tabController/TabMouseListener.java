package editor.gui.controller.tabController;

import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.BiConsumer;

public class TabMouseListener implements MouseListener, MouseMotionListener {

    private final TabView tabView;
    private Point lastPanPoint;

    public TabMouseListener(TabView tabView) {
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
        if (SwingUtilities.isLeftMouseButton(e)) {
            performAction(e, (x, y) -> EditorFrame.getInstance().getProjectView().clickPerform(x, y, tabView));
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
            lastPanPoint = e.getPoint();
            tabView.getWorkspacePanel().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            performAction(e, (x, y) -> EditorFrame.getInstance().getProjectView().releasePerform(x, y, tabView));
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
            lastPanPoint = null;
            tabView.getWorkspacePanel().setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            performAction(e, (x, y) -> EditorFrame.getInstance().getProjectView().dragPerform(x, y, tabView));
        }
        else if (SwingUtilities.isRightMouseButton(e) && lastPanPoint != null) {
            int deltaX = e.getX() - lastPanPoint.x;
            int deltaY = e.getY() - lastPanPoint.y;
            tabView.setDx(tabView.getDx() - deltaX);
            tabView.setDy(tabView.getDy() - deltaY);
            lastPanPoint = e.getPoint();
        }
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

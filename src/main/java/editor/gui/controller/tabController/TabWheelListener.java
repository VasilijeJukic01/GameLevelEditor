package editor.gui.controller.tabController;

import editor.gui.view.tab.TabView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import static editor.constants.Constants.MAX_ZOOM;
import static editor.constants.Constants.MIN_ZOOM;

public class TabWheelListener extends MouseAdapter implements MouseWheelListener {

    private final JScrollBar vScrollBar;
    private final TabKeyListener tabKeyListener;
    private final TabView tabView;

    public TabWheelListener(JScrollBar vScrollBar, TabKeyListener tabKeyListener, TabView tabView) {
        this.vScrollBar = vScrollBar;
        this.tabKeyListener = tabKeyListener;
        this.tabView = tabView;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = -e.getWheelRotation();
        if (tabKeyListener.isCtrlFlag()) adjustTabViewScale(rotation);
        else adjustScrollBarValue(e, rotation);
    }

    private void adjustTabViewScale(int rotation) {
        double newScale = tabView.getScale() * (1 + 0.1 * (rotation / MAX_ZOOM));
        newScale = Math.max(MIN_ZOOM, Math.min(MAX_ZOOM, newScale));
        tabView.setScale(newScale);
    }

    private void adjustScrollBarValue(MouseWheelEvent e, int rotation) {
        int increment = rotation * vScrollBar.getUnitIncrement() * 15;
        if (e.isShiftDown()) vScrollBar.setValue(vScrollBar.getValue() + increment);
        else vScrollBar.setValue(vScrollBar.getValue() - increment);
    }

}

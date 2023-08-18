package editor.gui.controller.tabController;

import editor.gui.controller.EditorKeyListener;
import editor.gui.view.tab.TabView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TabWheelListener extends MouseAdapter implements MouseWheelListener {

    private final JScrollBar vScrollBar;
    private final EditorKeyListener editorKeyListener;
    private final TabView tabView;

    public TabWheelListener(JScrollBar vScrollBar, EditorKeyListener editorKeyListener, TabView tabView) {
        this.vScrollBar = vScrollBar;
        this.editorKeyListener = editorKeyListener;
        this.tabView = tabView;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation() * (-1);
        if (editorKeyListener.isCtrlFlag()) {
            double newScale = tabView.getScale() * (1 + 0.1 * (rotation / 3.0));
            newScale = Math.max(0.3, Math.min(3.0, newScale));
            tabView.setScale(newScale);
        }
        else {
            if (e.isShiftDown()) {
                vScrollBar.setValue(vScrollBar.getValue() + rotation * vScrollBar.getUnitIncrement() * 15);
            }
            else {
                vScrollBar.setValue(vScrollBar.getValue() - rotation * vScrollBar.getUnitIncrement() * 15);
            }
        }
    }

}

package editor.gui.controller.tabController;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TabMouseListener implements MouseWheelListener {

    private final JScrollBar vScrollBar;

    public TabMouseListener(JScrollBar vScrollBar) {
        this.vScrollBar = vScrollBar;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int rotation = e.getWheelRotation();
        if (e.isShiftDown()) {
            vScrollBar.setValue(vScrollBar.getValue() - rotation * vScrollBar.getUnitIncrement() * 15);
        }
        else {
            vScrollBar.setValue(vScrollBar.getValue() + rotation * vScrollBar.getUnitIncrement() * 15);
        }
    }

}

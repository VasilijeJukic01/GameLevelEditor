package editor.gui.controller;

import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {

    private final TabView tabView;

    public MouseController(TabView tabView) {
        this.tabView = tabView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            EditorFrame.getInstance().getProjectView().clickPerform((int) (e.getX()/ tabView.getScale()+(tabView.getDx()/ tabView.getScale())),
                    (int) (e.getY()/ tabView.getScale()+(tabView.getDy()/ tabView.getScale())), this.tabView);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        EditorFrame.getInstance().getProjectView().releasePerform((int) (e.getX()/ tabView.getScale()+(tabView.getDx()/ tabView.getScale())),
                (int) (e.getY()/ tabView.getScale()+(tabView.getDy()/ tabView.getScale())), this.tabView);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        EditorFrame.getInstance().getProjectView().dragPerform((int) (e.getX()/ tabView.getScale()+(tabView.getDx()/ tabView.getScale())),
                (int) (e.getY()/ tabView.getScale()+(tabView.getDy()/ tabView.getScale())), this.tabView);
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

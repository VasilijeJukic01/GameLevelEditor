package editor.gui.view.tab;

import editor.gui.controller.MouseController;
import editor.model.repository.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.AffineTransform;

import static editor.constants.Constants.VIEW_COLOR;

public class TabView extends JPanel implements AdjustmentListener {

    private Node level;
    private AffineTransform tx;
    private double scale = 1.0, dx = 0.0, dy = 0.0;

    private final JPanel panel;
    private final JScrollBar hScrollBar;
    private final JScrollBar vScrollBar;

    public TabView(Node level) {
        this.level = level;

        this.hScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, 0, 1000);
        this.vScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, 0, 1000);
        this.setLayout(new BorderLayout());
        this.panel = new Workspace();
        panel.setLayout(new BorderLayout());
        panel.setBackground(VIEW_COLOR);
        this.add(hScrollBar, BorderLayout.SOUTH);
        this.add(vScrollBar, BorderLayout.EAST);
        this.add(panel);

        panel.addMouseListener(new MouseController(this));
        panel.addMouseMotionListener(new MouseController(this));

        hScrollBar.addAdjustmentListener(this);
        vScrollBar.addAdjustmentListener(this);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        JScrollBar jScrollBar = (JScrollBar) e.getSource();
        if (jScrollBar.getOrientation() == JScrollBar.HORIZONTAL) dx = e.getValue() - panel.getX();
        else dy = e.getValue() - panel.getY();
        panel.repaint();
    }

    // Getters & Setters
    public Node getLevel() {
        return level;
    }

    public double getScale() {
        return scale;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setScale(double scale) {
        this.scale = scale;
        this.vScrollBar.setMaximum((int) (1000 * scale));
        this.hScrollBar.setMaximum((int) (1000 * scale));
        this.repaint();
    }

    public void setLevel(Node level) {
        this.level = level;
    }

    // Workspace
    private class Workspace extends JPanel{

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            tx = new AffineTransform();
            tx.translate(-dx,-dy);
            tx.scale(scale, scale);
            g2.transform(tx);
        }

    }

}

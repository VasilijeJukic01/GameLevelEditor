package editor.gui.view.tab;

import editor.gui.controller.MouseController;
import editor.model.repository.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.AffineTransform;

import static editor.constants.Constants.*;

public class TabView extends JPanel implements AdjustmentListener {

    private Node level;
    private AffineTransform tx;
    private double scale = 1.0, dx = 0.0, dy = 0.0;

    private JPanel panel;
    private JScrollBar hScrollBar;
    private JScrollBar vScrollBar;

    public TabView(Node level) {
        this.level = level;
        initBars();
        initPanel();
        initLayout();
    }

    // Init
    private void initBars() {
        this.hScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, 0, H_SCROLLBAR_MAX);
        this.vScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, 0, V_SCROLLBAR_MAX);
        hScrollBar.addAdjustmentListener(this);
        vScrollBar.addAdjustmentListener(this);
    }

    private void initPanel() {
        this.panel = new Workspace();
        panel.setLayout(new BorderLayout());
        panel.setBackground(VIEW_COLOR);
        panel.addMouseListener(new MouseController(this));
        panel.addMouseMotionListener(new MouseController(this));
    }

    private void initLayout() {
        this.setLayout(new BorderLayout());
        this.add(hScrollBar, BorderLayout.SOUTH);
        this.add(vScrollBar, BorderLayout.EAST);
        this.add(panel);
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
        this.vScrollBar.setMaximum((int) (H_SCROLLBAR_MAX * scale));
        this.hScrollBar.setMaximum((int) (V_SCROLLBAR_MAX * scale));
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

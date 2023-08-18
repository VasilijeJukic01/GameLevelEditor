package editor.gui.view.tab;

import editor.gui.controller.MouseController;
import editor.gui.controller.tabController.TabMouseListener;
import editor.gui.view.renderer.LevelRenderer;
import editor.gui.view.renderer.Renderer;
import editor.model.repository.components.Level;
import editor.model.repository.nodeObserver.NodeSubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;

import static editor.constants.Constants.*;

public class TabView extends JPanel implements AdjustmentListener, NodeSubscriber {

    private Level level;
    private AffineTransform tx;
    private double scale = 1.0, dx = 0.0, dy = 0.0;

    private JPanel panel;
    private JScrollBar hScrollBar;
    private JScrollBar vScrollBar;

    private final Renderer renderer;

    public TabView(Level level) {
        this.level = level;
        this.level.addSubscriber(this);
        this.renderer = new LevelRenderer(level);
        initBars();
        initPanel();
        initLayout();
        setBars();
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
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setBars();
            }
        });
        TabMouseListener tabMouseListener = new TabMouseListener(vScrollBar);
        panel.addMouseWheelListener(tabMouseListener);
    }

    private void initLayout() {
        this.setLayout(new BorderLayout());
        this.add(hScrollBar, BorderLayout.SOUTH);
        this.add(panel);
        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(vScrollBar, BorderLayout.WEST);
        eastPanel.add(new SidePanel(), BorderLayout.EAST);
        this.add(eastPanel, BorderLayout.EAST);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        JScrollBar jScrollBar = (JScrollBar) e.getSource();
        if (jScrollBar.getOrientation() == JScrollBar.HORIZONTAL) dx = e.getValue() - panel.getX();
        else dy = e.getValue() - panel.getY();
        panel.repaint();
    }

    // Getters & Setters
    public Level getLevel() {
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
        this.vScrollBar.setMaximum((int) (level.getHeight()*TILE_SIZE * scale));
        this.hScrollBar.setMaximum((int) (level.getWidth()*TILE_SIZE * scale));
        this.repaint();
    }

    private void setBars() {
        this.vScrollBar.setMaximum((int) ((level.getHeight()*TILE_SIZE) * scale));
        this.hScrollBar.setMaximum((int) ((level.getWidth()*TILE_SIZE) * scale));
        this.repaint();
    }

    public void setLevel(Level level) {
        ((LevelRenderer)renderer).setLevel(level);
        this.level = level;
    }

    @Override
    public <T> void updateNode(T t) {
        setBars();
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

            renderer.render(g);

        }

    }

}

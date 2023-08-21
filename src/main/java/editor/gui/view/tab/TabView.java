package editor.gui.view.tab;

import editor.gui.controller.tabController.TabKeyListener;
import editor.gui.controller.tabController.TabMouseListener;
import editor.gui.controller.tabController.TabWheelListener;
import editor.gui.view.renderer.LevelRenderer;
import editor.gui.view.renderer.Renderer;
import editor.model.repository.components.Level;
import editor.model.repository.nodeObserver.NodeSubscriber;
import editor.settings.EditorSettings;
import editor.settings.Settings;
import editor.settings.SettingsKey;

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
    private final Settings settings;

    private double scale = 1.0;
    private double dx = 0.0, dy = 0.0;

    private JPanel mainPanel, workspacePanel;
    private JScrollBar hScrollBar, vScrollBar;

    private final Renderer renderer;

    public TabView(Level level) {
        this.setLayout(new BorderLayout());
        this.settings = new EditorSettings(this);
        initSettings();
        this.level = level;
        this.level.addSubscriber(this);
        initBars();
        initCentralPanels();
        initEastPanel();
        this.renderer = new LevelRenderer(this);
        initSouthPanel();
        refreshBars();
    }

    // Init
    private void initSettings() {
        this.settings.addParameter(SettingsKey.GRID, true);
        this.settings.addParameter(SettingsKey.GRID_COLOR, Color.BLUE);
        this.settings.addParameter(SettingsKey.LAYERS_INFO, "0000001");
        this.settings.addParameter(SettingsKey.FADE, 0);
        this.settings.addParameter(SettingsKey.FADE_COLOR, DEFAULT_FADE_COLOR);
        this.settings.addParameter(SettingsKey.SELECTED_SET, "Solid Tiles");
        this.settings.addParameter(SettingsKey.SELECTED_TILE, 0);
        this.settings.addParameter(SettingsKey.SELECTED_LAYER, 3);
        this.settings.addParameter(SettingsKey.EDIT_SELECTION, null);
    }

    private void initBars() {
        this.hScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, 0, H_SCROLLBAR_MAX);
        this.vScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, 0, V_SCROLLBAR_MAX);
        hScrollBar.addAdjustmentListener(this);
        vScrollBar.addAdjustmentListener(this);
    }

    private void initCentralPanels() {
        this.mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(hScrollBar, BorderLayout.SOUTH);
        this.workspacePanel = new Workspace();
        workspacePanel.setLayout(new BorderLayout());
        workspacePanel.setBackground(VIEW_COLOR);
        workspacePanel.setFocusable(true);
        workspacePanel.requestFocus();
        mainPanel.add(workspacePanel);
        initListeners();
    }

    private void initListeners() {
        workspacePanel.addMouseListener(new TabMouseListener(this));
        workspacePanel.addMouseMotionListener(new TabMouseListener(this));
        workspacePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                refreshBars();
            }
        });
        TabKeyListener tabKeyListener = new TabKeyListener();
        TabWheelListener tabWheelListener = new TabWheelListener(vScrollBar, tabKeyListener, this);
        workspacePanel.addMouseWheelListener(tabWheelListener);
        workspacePanel.addKeyListener(tabKeyListener);
    }

    private void initSouthPanel() {
        JScrollPane scrollPane = new JScrollPane(new BottomPanel(this));
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, scrollPane);
        this.add(splitPane);
    }

    private void initEastPanel() {
        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.add(vScrollBar, BorderLayout.WEST);
        SidePanel sidePanel = new SidePanel(this);
        eastPanel.add(sidePanel, BorderLayout.EAST);
        mainPanel.add(eastPanel, BorderLayout.EAST);
    }

    // Value changes
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        JScrollBar jScrollBar = (JScrollBar) e.getSource();
        if (jScrollBar.getOrientation() == JScrollBar.HORIZONTAL) dx = e.getValue() - workspacePanel.getX();
        else dy = e.getValue() - workspacePanel.getY();
        this.repaint();
    }

    private void refreshBars() {
        this.vScrollBar.setMaximum((int) ((level.getHeight() * TILE_SIZE) * scale));
        this.hScrollBar.setMaximum((int) ((level.getWidth() * TILE_SIZE) * scale));
        this.repaint();
    }

    // Getters & Setters
    public Level getLevel() {
        return level;
    }

    public Settings getSettings() {
        return settings;
    }

    public JPanel getWorkspacePanel() {
        return workspacePanel;
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

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public JScrollBar getHScrollBar() {
        return hScrollBar;
    }

    public JScrollBar getVScrollBar() {
        return vScrollBar;
    }

    public void setScale(double scale) {
        this.scale = scale;
        this.vScrollBar.setMaximum((int) (level.getHeight() * TILE_SIZE * scale));
        this.hScrollBar.setMaximum((int) (level.getWidth() * TILE_SIZE * scale));
        this.repaint();
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    // Observer
    @Override
    public <T> void updateNode(T t) {
        refreshBars();
    }

    // Workspace Panel
    private class Workspace extends JPanel {

        protected void paintComponent(Graphics g) {
            this.requestFocus(true);
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform tx = new AffineTransform();
            tx.translate(-dx,-dy);
            tx.scale(scale, scale);
            g2.transform(tx);

            renderer.render(g);
        }

    }

}

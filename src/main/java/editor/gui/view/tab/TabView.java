package editor.gui.view.tab;

import editor.gui.controller.tabController.TabKeyListener;
import editor.gui.controller.tabController.TabMouseListener;
import editor.gui.controller.tabController.TabWheelListener;
import editor.gui.view.renderer.LevelRenderer;
import editor.gui.view.renderer.Renderer;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;
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
import java.util.ArrayList;

import static editor.constants.Constants.*;

public class TabView extends JPanel implements AdjustmentListener, NodeSubscriber {

    private Level level;
    private final Settings settings;

    private double scale = 1.0;
    private double dx = 0.0, dy = 0.0;

    private JPanel mainPanel, workspacePanel;
    private BottomPanel bottomPanel;
    private JScrollBar hScrollBar, vScrollBar;

    private final Renderer renderer;
    private TabKeyListener tabKeyListener;

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
        this.settings.addParameter(SettingsKey.EDIT_SELECTION, new ArrayList<Tile>());
        this.settings.addParameter(SettingsKey.BACKGROUND, null);
        this.settings.addParameter(SettingsKey.TILE_SET, "Forest");
        this.settings.addParameter(SettingsKey.EXPORT_TYPE, "Both");
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
        TabMouseListener tabMouseListener = new TabMouseListener(this);
        workspacePanel.addMouseListener(tabMouseListener);
        workspacePanel.addMouseMotionListener(tabMouseListener);
        workspacePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                refreshBars();
            }
        });

        this.tabKeyListener = new TabKeyListener();
        TabWheelListener tabWheelListener = new TabWheelListener(vScrollBar, tabKeyListener, this);
        workspacePanel.addMouseWheelListener(tabWheelListener);
        workspacePanel.addKeyListener(tabKeyListener);
    }

    private void initSouthPanel() {
        this.bottomPanel = new BottomPanel(this);
        JScrollPane scrollPane = new JScrollPane(bottomPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBlockIncrement(64);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, scrollPane);
        splitPane.setResizeWeight(0.4);
        splitPane.setDividerLocation(0.4);
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
        if (jScrollBar.getOrientation() == JScrollBar.HORIZONTAL) dx = e.getValue();
        else dy = e.getValue();
        this.repaint();
    }

    private void refreshBars() {
        this.vScrollBar.setMaximum((int) ((level.getHeight() * TILE_SIZE) * scale));
        this.hScrollBar.setMaximum((int) ((level.getWidth() * TILE_SIZE) * scale));
        this.repaint();
    }

    public void reload() {
        this.bottomPanel.reload(true);
        this.level.notify(this);
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
        int max_dx = hScrollBar.getMaximum() - hScrollBar.getVisibleAmount();
        this.dx = Math.max(0, Math.min(dx, max_dx));
        hScrollBar.setValue((int) this.dx);
        repaint();
    }

    public void setDy(double dy) {
        int max_dy = vScrollBar.getMaximum() - vScrollBar.getVisibleAmount();
        this.dy = Math.max(0, Math.min(dy, max_dy));
        vScrollBar.setValue((int) this.dy);
        repaint();
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

    public TabKeyListener getTabKeyListener() {
        return tabKeyListener;
    }

    // Observer
    @Override
    public <T> void updateNode(T t) {
        refreshBars();
        repaint();
    }

    // Workspace Panel
    private class Workspace extends JPanel {

        public Workspace() {
            this.setDoubleBuffered(true);
        }

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

package editor.gui.view;

import editor.gui.view.tab.TabToolBar;
import editor.gui.view.tab.TabView;
import editor.gui.view.tab.TabbedPane;
import editor.state.StateManager;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel {

    private TabToolBar tabToolBar;
    private ProjectViewTop projectViewTop;
    private TabbedPane tabbedPane;

    private StateManager stateManager;

    public ProjectView() {
        init();
        initTopPanel();
    }

    private void init() {
        this.tabToolBar = new TabToolBar();
        this.projectViewTop = new ProjectViewTop();
        this.tabbedPane = new TabbedPane();
        this.setLayout(new BorderLayout());
        initTopPanel();
        this.add(tabbedPane);
        this.stateManager = new StateManager();
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tabToolBar, BorderLayout.NORTH);
        topPanel.add(projectViewTop);
        this.add(topPanel, BorderLayout.NORTH);
    }

    // Mediator
    public void clickPerform(int x, int y, TabView tabView) {
        this.stateManager.getCurrentState().clickPerform(x, y, tabView);
    }

    public void releasePerform(int x, int y, TabView tabView) {
        this.stateManager.getCurrentState().releasePerform(x, y, tabView);
    }

    public void dragPerform(int x, int y, TabView tabView) {
        this.stateManager.getCurrentState().dragPerform(x, y, tabView);
    }

    public void startAddState() {
        this.stateManager.setAddState();
    }

    public void startDeleteState() {
        this.stateManager.setDeleteState();
    }

    public void startEditState() {
        this.stateManager.setEditState();
    }

    public void startMoveState() {
        this.stateManager.setMoveState();
    }

    // Getters
    public ProjectViewTop getProjectViewTop() {
        return projectViewTop;
    }

    public TabbedPane getTabbedPane() {
        return tabbedPane;
    }

}

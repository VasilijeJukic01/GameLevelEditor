package editor.gui.view;

import editor.gui.view.tab.TabToolBar;
import editor.gui.view.tab.TabView;
import editor.gui.view.tab.TabbedPane;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel {

    private TabToolBar tabToolBar;
    private ProjectViewTop projectViewTop;
    private TabbedPane tabbedPane;

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
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(tabToolBar, BorderLayout.NORTH);
        topPanel.add(projectViewTop);
        this.add(topPanel, BorderLayout.NORTH);
    }

    public void clickPerform(int x, int y, TabView tabView) {

    }

    public void releasePerform(int x, int y, TabView tabView) {

    }

    public void dragPerform(int x, int y, TabView tabView) {

    }

    // Getters
    public ProjectViewTop getProjectViewTop() {
        return projectViewTop;
    }

    public TabbedPane getTabbedPane() {
        return tabbedPane;
    }

}

package editor.gui.view;

import editor.gui.view.tab.TabToolBar;
import editor.gui.view.tab.TabView;
import editor.gui.view.tab.TabbedPane;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel {

    private TabToolBar mapToolBar;
    private ProjectViewTop projectViewTop;
    private TabbedPane tabbedPane;

    public ProjectView() {
        init();
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(mapToolBar, BorderLayout.NORTH);
        topPanel.add(projectViewTop);
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(tabbedPane);
    }

    public void init() {
        this.mapToolBar = new TabToolBar();
        this.projectViewTop = new ProjectViewTop();
        this.tabbedPane = new TabbedPane();
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

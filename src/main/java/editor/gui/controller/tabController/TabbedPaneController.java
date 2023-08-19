package editor.gui.controller.tabController;

import editor.gui.view.ProjectView;
import editor.gui.view.ProjectViewTop;
import editor.gui.view.tab.TabView;
import editor.gui.view.tab.TabbedPane;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.tree.Tree;
import editor.model.tree.mvc.TreeItem;
import editor.model.tree.mvc.TreeView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabbedPaneController {

    private final ProjectView projectView;

    public TabbedPaneController(ProjectView projectView) {
        this.projectView = projectView;
    }

    public void generateTabs(JTree projectExplorer, Tree<TreeItem, TreeView> editorTree) {
        projectExplorer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleProjectExplorerMousePress(e, editorTree);
            }
        });
    }

    private void handleProjectExplorerMousePress(MouseEvent e, Tree<TreeItem, TreeView> editorTree) {
        if (e.getClickCount() == 2) {
            TreeItem treeItem = editorTree.getSelectedNode();
            if (treeItem.getNode() instanceof Project) {
                refreshProjectAndTabs((Project) treeItem.getNode());
            }
        }
    }

    private void refreshProjectAndTabs(Project selectedProject) {
        TabbedPane tabbedPane = projectView.getTabbedPane();
        ProjectViewTop projectViewTop = projectView.getProjectViewTop();

        tabbedPane.refresh();
        projectViewTop.setProject(selectedProject);
        tabbedPane.setProject(selectedProject);

        selectedProject.getChildren().forEach(this::addTabToTabbedPane);
    }

    private void addTabToTabbedPane(Node child) {
        TabbedPane tabbedPane = projectView.getTabbedPane();
        TabView mapTab = new TabView((Level) child);
        tabbedPane.addTab(child.getName(), mapTab);
    }

}

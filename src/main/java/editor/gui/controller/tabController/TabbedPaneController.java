package editor.gui.controller.tabController;

import editor.gui.view.ProjectView;
import editor.gui.view.tab.TabView;
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
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    TreeItem treeItem = editorTree.getSelectedNode();
                    if (treeItem.getNode() instanceof Project) {
                        projectView.getTabbedPane().refresh();
                        projectView.getProjectViewTop().setProject((Project)treeItem.getNode());
                        projectView.getTabbedPane().setProject((Project)treeItem.getNode());
                        for (Node child : ((Project) treeItem.getNode()).getChildren()) {
                            TabView mapTab = new TabView((Level)child);
                            projectView.getTabbedPane().addTab(child.getName(), mapTab);
                        }
                    }
                }
            }
        });
    }

}

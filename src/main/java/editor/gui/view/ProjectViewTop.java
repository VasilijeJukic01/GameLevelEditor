package editor.gui.view;

import editor.model.repository.components.Project;
import editor.model.repository.nodeObserver.NodeSubscriber;
import editor.model.tree.Tree;
import editor.model.tree.mvc.TreeItem;
import editor.model.tree.mvc.TreeView;
import editor.model.tree.treeObserver.TreeSubscriber;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class ProjectViewTop extends JPanel implements TreeSubscriber, NodeSubscriber {

    private final JLabel lbAuthor = new JLabel();
    private final JLabel lbAuthorValue = new JLabel();
    private final JLabel lbSpacing = new JLabel();
    private final JLabel lbProject = new JLabel();
    private final JLabel lbProjectValue = new JLabel();

    private Project project;
    private final Tree<TreeItem, TreeView> editorTree;

    public ProjectViewTop() {

        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        this.add(panel);

        panel.add(lbAuthor);
        panel.add(lbAuthorValue);
        panel.add(lbSpacing);
        panel.add(lbProject);
        panel.add(lbProjectValue);

        editorTree = EditorFrame.getInstance().getEditorTree();
        editorTree.addSubscriberTree(this);
    }

    public void setProject(Project project) {
        this.project = project;
        project.addSubscriber(this);
        updateNode(project);
    }

    @Override
    public <T> void updateRemoved(T t) {
        if (t instanceof Project && t == this.project) {
            lbAuthor.setText("");
            lbProject.setText("");
            lbProjectValue.setText("");
            lbAuthorValue.setText("");
        }
    }

    @Override
    public <T> void updateAdded(T t) {

    }

    @Override
    public <T> void updateNode(T t) {
        Project p = (Project)t;
        lbAuthor.setText("Author: ");
        lbProject.setText("Project: ");
        lbAuthorValue.setText(p.getAuthor());
        lbProjectValue.setText(p.getName());
        this.revalidate();
        this.repaint();
    }
}


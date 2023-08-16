package editor.gui.view.tab;

import editor.gui.controller.TabCloseButton;
import editor.gui.view.EditorFrame;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.repository.nodeObserver.NodeSubscriber;
import editor.model.tree.treeObserver.TreeSubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TabbedPane extends JTabbedPane implements TreeSubscriber, NodeSubscriber {

    private Project project;

    public TabbedPane() {
        super();
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new TabCloseButton(component, title));
    }

    public void setProject(Project project) {
        this.project = project;
        List<Node> levels = project.getChildren();
        levels.forEach(level -> ((Level) level).addSubscriber(this));
        EditorFrame.getInstance().getEditorTree().addSubscriberTree(this);
    }

    public void refresh() {
        this.removeAll();
    }

    // Observer
    @Override
    public <T> void updateRemoved(T t) {
        if(t instanceof Project && t == this.project) {
            this.removeAll();
            return;
        }
        if (!(t instanceof Level)) return;
        for (Component component : this.getComponents()) {
            if(component instanceof TabView && t == ((TabView) component).getLevel()) {
                this.remove(component);
            }
        }
    }

    @Override
    public <T> void updateAdded(T t) {
        if(t instanceof Level && ((Level) t).getParent().equals(this.project)){
            this.addTab(((Level)t).getName(), new TabView((Level)t));
        }
    }

    @Override
    public <T> void updateNode(T t) {
        Level l = (Level)t;
        List<Component> components = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        Component save = this.getSelectedComponent();

        for (Component component : this.getComponents()) {
            if(component instanceof TabView) {
                components.add(component);
                if(l == ((TabView) component).getLevel())
                    ((TabView) component).setLevel(l);

                titles.add(((TabView) component).getLevel().getName());
            }
        }
        refresh();
        IntStream.range(0, components.size())
                .forEach(i -> this.addTab(titles.get(i), components.get(i)));
        this.setSelectedComponent(save);
    }
}

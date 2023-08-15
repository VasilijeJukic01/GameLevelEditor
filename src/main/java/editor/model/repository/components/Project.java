package editor.model.repository.components;

import editor.model.repository.Node;
import editor.model.repository.NodeComposite;

import java.util.Optional;

public class Project extends NodeComposite<Node> {

    private String author;
    private String path;

    public Project(String name, Node parent, String author, String path) {
        super(name, parent);
        this.author = author;
        this.path = path;
    }

    public Project(String name, Node parent) {
        super(name, parent);
        this.author = "Default";
        this.path = "";
    }

    public void setName(String text) {
        Optional.ofNullable(text)
                .filter(str -> !str.isEmpty())
                .ifPresent(super::setName);
    }

    // Getters & Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        Optional.ofNullable(author)
                .filter(str -> !str.isEmpty())
                .ifPresent(str -> this.author = str);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

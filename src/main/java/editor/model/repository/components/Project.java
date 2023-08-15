package editor.model.repository.components;

import editor.model.repository.Node;
import editor.model.repository.NodeComposite;

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
        if(text == null || text.equals("")) return;
        super.setName(text);
    }

    // Getters & Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author == null || author.equals("")) return;
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

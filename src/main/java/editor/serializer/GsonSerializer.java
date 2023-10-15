package editor.serializer;

import com.google.gson.*;
import editor.gui.view.EditorFrame;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Project;
import editor.model.repository.components.Tile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class GsonSerializer implements Serializer<Project> {

    private Gson gson = new Gson();

    @Override
    public void loadProject(File file) {
        try {
            FileReader fileReader = new FileReader(file);

            RuntimeTypeAdapterFactory<Node> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Node.class, "type")
                            .registerSubtype(Project.class, Project.class.getName()).registerSubtype(Level.class, Level.class.getName())
                            .registerSubtype(Node.class, Node.class.getName()).registerSubtype(Tile.class, Tile.class.getName());

            gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

            Project project = gson.fromJson(fileReader, Project.class);
            EditorFrame.getInstance().getEditorTree().deserialize(project);

        }
        catch (Exception ignored) {}
    }

    @Override
    public void saveProject(Project node) {
        if (node.getPath() == null || node.getPath().isEmpty()) return;
        try (FileWriter writer = new FileWriter(node.getPath())) {
            gson.toJson(node, writer);
        }
        catch (Exception ignored) {}
    }

}

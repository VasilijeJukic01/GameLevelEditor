package editor.model.metadata;

import java.util.ArrayList;
import java.util.List;

public class LevelMetadata {

    private String backgroundId;
    private Boolean ambientParticlesEnabled;
    private String tileset;
    private List<Object> lights = new ArrayList<>();
    private List<ObjectMetadata> decorations = new ArrayList<>();

    public List<ObjectMetadata> getDecorations() {
        return decorations;
    }

    public void setDecorations(List<ObjectMetadata> decorations) {
        this.decorations = decorations;
    }
}

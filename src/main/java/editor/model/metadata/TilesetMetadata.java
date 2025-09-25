package editor.model.metadata;

public class TilesetMetadata {

    private String name;
    private String spritePath;
    private int rows;
    private int columns;
    private int tileSizeInSprite;
    private int tileCount;

    public String getName() {
        return name;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getTileSizeInSprite() {
        return tileSizeInSprite;
    }

    public int getTileCount() {
        return tileCount;
    }
}
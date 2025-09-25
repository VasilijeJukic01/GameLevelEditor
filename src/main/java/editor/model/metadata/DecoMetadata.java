package editor.model.metadata;

public class DecoMetadata {

    private String id;
    private int wid, hei;
    private int xOffset, yOffset;
    private String filename;
    private boolean flipped;

    public String getId() {
        return id;
    }

    public int getWid() {
        return wid;
    }

    public int getHei() {
        return hei;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public String getFilename() {
        return filename;
    }

    public boolean isFlipped() {
        return flipped;
    }
}
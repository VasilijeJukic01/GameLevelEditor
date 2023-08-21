package editor.model.loader;

public class LevelDeco {

    private final LvlDecoType type;

    private int xOffset, yOffset;
    private final int w, h;

    public LevelDeco(LvlDecoType type, int w, int h) {
        this.type = type;
        this.w = w;
        this.h = h;
    }

    public void setXOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public void setYOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public LvlDecoType getType() {
        return type;
    }
}

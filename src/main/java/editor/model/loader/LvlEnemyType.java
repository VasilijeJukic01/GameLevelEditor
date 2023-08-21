package editor.model.loader;

import static editor.constants.Constants.TILE_SIZE;

public enum LvlEnemyType {
    SKELETON("Skeleton",            TILE_SIZE, TILE_SIZE + TILE_SIZE/2, 0, 40),
    GHOUL("Ghoul",                  TILE_SIZE, TILE_SIZE + TILE_SIZE/2, 0, 40),
    SPEAR_WOMAN("SpearWoman",   TILE_SIZE + (int)(TILE_SIZE/1.7), 2*TILE_SIZE, -30, 10),
    MAX("",                     0, 0, 0,0);

    private final String id;
    private final int wid, hei;
    private final int xOffset, yOffset;

    LvlEnemyType(String id, int wid, int hei, int xOffset, int yOffset) {
        this.id = id;
        this.wid = wid;
        this.hei = hei;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public String getId() {
        return id;
    }

    public int getWid() {
        return wid;
    }

    public int getHei() {
        return hei;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

}

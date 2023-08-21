package editor.model.loader;

import static editor.constants.Constants.TILE_SIZE;
import static editor.constants.ObjectConstants.TILE_SIZE_3;
import static editor.constants.ObjectConstants.TILE_SIZE_4;

public enum LvlObjType {
    BLUE_POTION("BluePotion",      24, 40, 20, 20),
    RED_POTION("RedPotion",        24, 40, 20, 20),
    CRATE("Crate",                 TILE_SIZE, TILE_SIZE, 5, 8),
    BARREL("Barrel",               TILE_SIZE, TILE_SIZE, 5, 8),
    SPIKES("Spikes",               TILE_SIZE, TILE_SIZE, 0, 8),
    ARROW_TRAP_LEFT("ArrowTrap",   TILE_SIZE, TILE_SIZE, 0, 8),
    ARROW_TRAP_RIGHT("~ArrowTrap",  TILE_SIZE, TILE_SIZE, 0, 8),
    COIN("Coin",                   TILE_SIZE/2, TILE_SIZE/2, 15, 20),
    SHOP("Shop",                   TILE_SIZE_4, TILE_SIZE_3, 0, 8),
    BLOCKER("Blocker",             TILE_SIZE, TILE_SIZE_3+16, 0, -8),
    BLACKSMITH("Blacksmith",       TILE_SIZE, TILE_SIZE+TILE_SIZE/2, 0, 40),
    DOG("Dog",                     TILE_SIZE, TILE_SIZE, 0, 8),
    MAX("", 0,0,0,0);

    private final String id;
    private final int wid, hei;
    private final int xOffset, yOffset;

    LvlObjType(String id, int wid, int hei, int xOffset, int yOffset) {
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

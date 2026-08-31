package editor.model.loader;

import static editor.constants.Constants.TILE_SIZE;
import static editor.constants.ObjectConstants.*;

public enum LvlObjType {
    BLUE_POTION("BluePotion",       24, 40, 20, 20),                            // 0
    RED_POTION("RedPotion",         24, 40, 20, 20),                            // 1
    CRATE("Crate",                  TILE_SIZE, TILE_SIZE, 5, 8),                // 2
    BARREL("Barrel",                TILE_SIZE, TILE_SIZE, 5, 8),                // 3
    SPIKES_UP("Spikes",             TILE_SIZE, TILE_SIZE, 0, 8),                // 4
    ARROW_TRAP_LEFT("ArrowTrap",    TILE_SIZE, TILE_SIZE, 0, 8),                // 5
    ARROW_TRAP_RIGHT("~ArrowTrap",  TILE_SIZE, TILE_SIZE, 0, 8),                // 6
    COIN("Coin",                    TILE_SIZE/2, TILE_SIZE/2, 15, 20),          // 7
    SHOP("Shop",                    TILE_SIZE_4, TILE_SIZE_4, 0, 8),            // 8
    BLOCKER("Blocker",              TILE_SIZE, TILE_SIZE_3+16, 0, -8),          // 9
    BLACKSMITH("Blacksmith",        TILE_SIZE, TILE_SIZE+TILE_SIZE/2, 0, 40),   // 10
    DOG("Dog",                      TILE_SIZE, TILE_SIZE, 0, 8),                // 11
    TOTEM("Totem",                  TILE_SIZE, TILE_SIZE, 0, 12),               // 12
    SMASH_TRAP("SmashTrap",         TILE_SIZE, TILE_SIZE_2, 0, -15),            // 13
    CANDLE("Candle",                TILE_SIZE, TILE_SIZE_2, 0, 8),              // 14
    LOOT("Loot",                    TILE_SIZE, TILE_SIZE, 0, 8),                // 15
    TABLE("Table",                  TILE_SIZE, TILE_SIZE, 0, 8),                // 16
    BOARD("Board",                  TILE_SIZE_2, TILE_SIZE_2, 0, 8),            // 17
    NPC("NPC",                      TILE_SIZE, (int)(TILE_SIZE*1.3), 0, 8),     // 18
    LAVA("Lava",                    TILE_SIZE, TILE_SIZE, 0, 8),                // 19
    BRICK("Brick",                  TILE_SIZE, TILE_SIZE, 0, 0),                // 20
    JUMP_PAD("JumpPad",             TILE_SIZE, TILE_SIZE, 0, 0),                // 21
    SPIKES_DOWN("SpikesDown",       TILE_SIZE, TILE_SIZE, 0, 0),                // 22
    SPIKES_LEFT("SpikesLeft",       TILE_SIZE, TILE_SIZE, 0, 0),                // 23
    SPIKES_RIGHT("SpikesRight",     TILE_SIZE, TILE_SIZE, 0, 0),                // 24
    HERB_PLACEHOLDER("Placeholder", 1, 1, 0, 0),                                // 25 Ignore
    TRAP_PLACEHOLDER("Placeholder", 1, 1, 0, 0),                                // 26 Ignore
    DOOR("Door",                    TILE_SIZE, (int)(TILE_SIZE*1.5), 0, 0),     // 27
    MAX("",                         0,0,0,0);

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

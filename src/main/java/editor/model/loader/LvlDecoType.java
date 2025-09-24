package editor.model.loader;

import static editor.constants.Constants.TILE_SIZE;
import static editor.constants.ObjectConstants.*;

public enum LvlDecoType {
    BIG_STONE1("BIG_STONE1",        TILE_SIZE_4, TILE_SIZE_2, 0, 8),
    BIG_STONE2("BIG_STONE2",        TILE_SIZE_4, TILE_SIZE_2, 0, 8),
    BIG_STONE3("BIG_STONE3",        TILE_SIZE_4, TILE_SIZE_2, 0, 8),
    THORNS1("THORNS1",              TILE_SIZE_6, TILE_SIZE_2, -5, -30),
    THORNS2("THORNS2",              TILE_SIZE_4, TILE_SIZE_2, 0, 8),
    THORNS3("THORNS3",              TILE_SIZE_4, TILE_SIZE_3, 0, 8),
    PLANT1("PLANT1",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT2("PLANT2",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT3("PLANT3",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT4("PLANT4",                TILE_SIZE, TILE_SIZE, 2, 25),
    PLANT5("PLANT5",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT6("PLANT6",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT7("PLANT7",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT8("PLANT8",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT9("PLANT9",                TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT10("PLANT10",              TILE_SIZE, TILE_SIZE, 2, 15),
    PLANT11("PLANT11",              TILE_SIZE, TILE_SIZE, 2, 15),
    VINES1("VINES1",                TILE_SIZE, TILE_SIZE_3, 0, -10),
    VINES1_BIG("VINES1_BIG",        TILE_SIZE_4, TILE_SIZE_8, 0, -20),
    VINES2("VINES2",                TILE_SIZE_1HALF, TILE_SIZE_3, 0, -10),
    VINES3("VINES3",                TILE_SIZE, TILE_SIZE_3, 0, -10),
    VINES4("VINES4",                TILE_SIZE, TILE_SIZE_3, -5, -25),
    MOSS1("MOSS1",                  TILE_SIZE, TILE_SIZE_3, 0, -15),
    MOSS2("MOSS2",                  TILE_SIZE, TILE_SIZE_3, 0, -15),
    LEAF1("LEAF1",                  BIG_TILE_SIZE, TILE_SIZE, 0, -20),
    BUSH1("BUSH1",                  TILE_SIZE_3, TILE_SIZE_2, 0, 10),
    THORNS1_BIG("THORNS1_BIG",      TILE_SIZE_8, TILE_SIZE_3, 0, -20),
    VINES2_BIG("VINES2_BIG",        TILE_SIZE_3, TILE_SIZE_5, 0, -90),
    MOSS1_BIG("MOSS1_BIG",          TILE_SIZE_3, TILE_SIZE_8, 0, -15),
    VINES5("VINES5",                TILE_SIZE_2, TILE_SIZE_4, -40, 25),
    STONE_MOSS1("STONE_MOSS1",      TILE_SIZE_2, TILE_SIZE, 0, 10),
    MOSS3("MOSS3",                  TILE_SIZE_6, TILE_SIZE_2, 0, 10),
    MOSS4("MOSS4",                  TILE_SIZE_9, TILE_SIZE_2, 0, 10),
    STONE_MOSS2("STONE_MOSS2",      TILE_SIZE_2, TILE_SIZE, 0, 15),
    BLACK("BLACK",                  TILE_SIZE, TILE_SIZE, 0, 0),
    LEFT_END("LEFT_END",            TILE_SIZE, TILE_SIZE, 0, 0),
    RIGHT_END("RIGHT_END",          TILE_SIZE, TILE_SIZE, 0, 0),
    LEAF1_REVERSE("LEAF1_REVERSE",  BIG_TILE_SIZE, TILE_SIZE, -10, -20),
    UPPER_END("UPPER_END",          TILE_SIZE, TILE_SIZE, 0, 0),
    BOTTOM_END("BOTTOM_END",        TILE_SIZE, TILE_SIZE, 0, 0),
    BIG_STONE4("BIG_STONE4",        TILE_SIZE_4, TILE_SIZE_2, 0, 12),
    BIG_STONE5("BIG_STONE5",        TILE_SIZE_4, TILE_SIZE_2, 0, 12),
    BUSH2("BUSH1",                  TILE_SIZE_3, TILE_SIZE_2, 0, 8),
    BUSH3("BUSH2",                  TILE_SIZE_3, TILE_SIZE_2, 0, 8),
    BUSH4("BUSH3",                  TILE_SIZE_2, TILE_SIZE_2, 0, 8),
    FLOWERS1("FLOWERS1",                  TILE_SIZE_3, TILE_SIZE_3, 0, -25),
    FLOWERS2("FLOWERS2",                  TILE_SIZE_3, TILE_SIZE_3, 0, -25),
    LEAF2("LEAF2",                  BIG_TILE_SIZE, TILE_SIZE, 0, 8),
    LOG1("LOG1",        TILE_SIZE_2, TILE_SIZE_2, 0, -16),
    LOG2("LOG2",        TILE_SIZE_2, TILE_SIZE_2, 0, -16),
    LOG3("LOG3",        TILE_SIZE_2, TILE_SIZE_2, 0, -9),
    MOSS5("MOSS5",                  TILE_SIZE, TILE_SIZE, 0, 10),
    MOSS6("MOSS6",                  TILE_SIZE_2, TILE_SIZE_8, 0, 10),
    PILLAR1("PILLAR1",                  TILE_SIZE_2, TILE_SIZE_4, -8, 14),
    ROCK1("ROCK1",        TILE_SIZE_3, TILE_SIZE_2, 0, 7),
    SHROOM1("SHROOM1",                TILE_SIZE, TILE_SIZE, 2, 15),
    SHROOM2("SHROOM2",                TILE_SIZE, TILE_SIZE, 2, 15),
    SHROOM3("SHROOM3",                TILE_SIZE, TILE_SIZE, 2, 15),
    SHROOM4("SHROOM4",                TILE_SIZE_2, TILE_SIZE_2, 2, 15),
    TREE1("TREE1",                  TILE_SIZE_2, TILE_SIZE_5, -7, 14),
    TREE2("TREE2",                  TILE_SIZE_2, TILE_SIZE_5, -7, 14),
    TREE3("TREE3",                  TILE_SIZE_2, TILE_SIZE_4, -7, 14),
    PLANT12("PLANT12",                TILE_SIZE, TILE_SIZE, 2, 8),
    PLANT13("PLANT13",                TILE_SIZE, TILE_SIZE, 2, 8),
    CRYSTAL1("CRYSTAL1",                TILE_SIZE, TILE_SIZE, 2, 8),
    CRYSTAL2("CRYSTAL2",                TILE_SIZE, TILE_SIZE, 2, 8);

    private final String id;
    private final int wid, hei;
    private final int xOffset, yOffset;

    LvlDecoType(String id, int wid, int hei, int xOffset, int yOffset) {
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

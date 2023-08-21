package editor.core;

import editor.model.loader.LevelDeco;
import editor.model.loader.LvlDecoType;
import editor.model.loader.LvlObjType;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;
import editor.utils.Utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static editor.constants.Constants.*;
import static editor.constants.Constants.FOREST_TILE_SIZE;
import static editor.constants.FilePaths.FOREST_SPRITE;

public final class Storage {

    private BufferedImage[] forestTilesImg;
    private BufferedImage[] forestDecoTilesImg;
    private BufferedImage[] objectsTilesImg;

    private final List<Tile> forestSolidTiles;
    private final List<Tile> forestDecoTiles;
    private final List<Tile> objectTiles;

    private final List<LvlDecoType> objData;
    private LevelDeco[] forestObjects;

    public Storage() {
        this.forestSolidTiles = new ArrayList<>();
        this.forestDecoTiles = new ArrayList<>();
        this.objectTiles = new ArrayList<>();
        this.objData = Arrays.asList(LvlDecoType.values());
        init();
    }

    private void init() {
        loadForestTiles();
        loadForestDecoTiles();
        loadObjects();
        initDeco();
    }

    private void loadForestTiles() {
        BufferedImage img = Utils.getInstance().importImage(FOREST_SPRITE, -1, -1);
        forestTilesImg = new BufferedImage[FOREST_TILES];
        for (int i = 0; i < FOREST_SPRITE_ROW; i++) {
            for (int j = 0; j < FOREST_SPRITE_COL; j++) {
                int index = j * FOREST_SPRITE_COL + i;
                forestTilesImg[index] = img.getSubimage(i*FOREST_TILE_SIZE, j*FOREST_TILE_SIZE, FOREST_TILE_SIZE, FOREST_TILE_SIZE);
                Tile t = new Tile("", null, TileType.SOLID, 0, 0, index, 254, 254);
                forestSolidTiles.add(t);
            }
        }
    }

    private void loadForestDecoTiles() {
        this.forestDecoTilesImg = new BufferedImage[objData.size()];
        for (int i = 0; i < forestDecoTilesImg.length; i++) {
            LvlDecoType decoType = objData.get(i);
            String id = decoType.getId();
            int bigFlag = id.contains("_BIG") ? 4 : 0;
            int reverseFlag = id.contains("_REVERSE") ? 8 : 0;
            String name = id.substring(0, id.length() - bigFlag - reverseFlag);
            forestDecoTilesImg[i] = Utils.getInstance().importImage("/images/data/levelDecos/" +name+".png", decoType.getWid(), decoType.getHei());
            if (reverseFlag != 0) forestDecoTilesImg[i] = Utils.getInstance().flipImage(forestDecoTilesImg[i]);
            Tile t = new Tile("", null, TileType.DECO, 0, 0, 254, 254, i);
            forestDecoTiles.add(t);
        }
    }

    private void loadObjects() {
        this.objectsTilesImg = new BufferedImage[LvlDecoType.values().length];
        for (int i = 0; i < 12; i++) {
            LvlObjType objType = LvlObjType.values()[i];
            objectsTilesImg[i] = Utils.getInstance().importImage("/images/data/levelObjects/"+objType.getId().replace("~","")+".png", -1, -1);
            if (objType.getId().contains("~")) objectsTilesImg[i] = Utils.getInstance().flipImage(objectsTilesImg[i]);
            Tile t = new Tile("", null, TileType.OBJECT, 0, 0, 254, 254, i);
            objectTiles.add(t);
        }
    }

    private void initDeco() {
        this.forestObjects = new LevelDeco[objData.size()];
        for (int i = 0; i < forestObjects.length; i++) {
            LvlDecoType lvlObj = objData.get(i);
            forestObjects[i] = new LevelDeco(lvlObj, forestDecoTilesImg[i].getWidth(), forestDecoTilesImg[i].getHeight());
            forestObjects[i].setYOffset(lvlObj.getYOffset());
            forestObjects[i].setXOffset(lvlObj.getXOffset());
        }
    }

    // Getters
    public BufferedImage[] getForestTilesImg() {
        return forestTilesImg;
    }

    public BufferedImage[] getForestDecoTilesImg() {
        return forestDecoTilesImg;
    }

    public BufferedImage[] getObjectsTilesImg() {
        return objectsTilesImg;
    }

    public List<Tile> getForestSolidTiles() {
        return forestSolidTiles;
    }

    public List<Tile> getForestDecoTiles() {
        return forestDecoTiles;
    }

    public List<Tile> getObjectTiles() {
        return objectTiles;
    }

    public LevelDeco[] getForestObjects() {
        return forestObjects;
    }
}

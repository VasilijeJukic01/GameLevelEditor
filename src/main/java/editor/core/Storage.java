package editor.core;

import editor.model.loader.LvlDecoType;
import editor.model.loader.LvlEnemyType;
import editor.model.loader.LvlObjType;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;
import editor.utils.Utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static editor.constants.Constants.*;
import static editor.constants.Constants.FOREST_TILE_SIZE;
import static editor.constants.FilePaths.FOREST_SPRITE;

public final class Storage {

    private final Map<String, BufferedImage[]> imageMap;
    private final Map<String, List<Tile>> tileMap;

    private BufferedImage playerImg;
    private Tile playerTile;

    public Storage() {
        imageMap = new HashMap<>();
        tileMap = new HashMap<>();
        init();
    }

    private void init() {
        BufferedImage img = Utils.getInstance().importImage(FOREST_SPRITE, -1, -1);
        loadTiles(img, FOREST_SPRITE_ROW, FOREST_SPRITE_COL, FOREST_TILE_SIZE, FOREST_TILES, "Forest");
        loadForestDecoTiles();
        loadObjects();
        loadEnemies();
        loadPlayer();
    }

    public void loadTiles(BufferedImage img, int rows, int columns, int size, int maxTiles, String id) {
        BufferedImage[] tilesImg = new BufferedImage[maxTiles];
        List<Tile> solidTiles = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int index = i * columns + j;
                tilesImg[index] = img.getSubimage(j*size, i*size, size, size);
                Tile t = new Tile("", null, TileType.SOLID, 0, 0, index, 254, 254);
                solidTiles.add(t);
            }
        }
        imageMap.put(id+"Tiles", tilesImg);
        tileMap.put(id+"Tiles", solidTiles);
    }


    private void loadForestDecoTiles() {
        BufferedImage[] forestDecoTilesImg = new BufferedImage[LvlDecoType.values().length];
        List<Tile> forestDecoTiles = new ArrayList<>();
        for (int i = 0; i < forestDecoTilesImg.length; i++) {
            LvlDecoType decoType = LvlDecoType.values()[i];
            String id = decoType.getId();
            int bigFlag = id.contains("_BIG") ? 4 : 0;
            int reverseFlag = id.contains("_REVERSE") ? 8 : 0;
            String name = id.substring(0, id.length() - bigFlag - reverseFlag);
            forestDecoTilesImg[i] = Utils.getInstance().importImage("/images/data/levelDecos/"+name+".png", decoType.getWid(), decoType.getHei());
            if (reverseFlag != 0) forestDecoTilesImg[i] = Utils.getInstance().flipImage(forestDecoTilesImg[i]);
            Tile t = new Tile("", null, TileType.DECO, 0, 0, 254, 254, i);
            forestDecoTiles.add(t);
        }
        imageMap.put("ForestDeco", forestDecoTilesImg);
        tileMap.put("ForestDeco", forestDecoTiles);
    }

    private void loadObjects() {
        BufferedImage[] objectsTilesImg = new BufferedImage[LvlDecoType.values().length];
        List<Tile> objectTiles = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            LvlObjType objType = LvlObjType.values()[i];
            objectsTilesImg[i] = Utils.getInstance().importImage("/images/data/levelObjects/"+objType.getId().replace("~","")+".png", -1, -1);
            if (objType.getId().contains("~")) objectsTilesImg[i] = Utils.getInstance().flipImage(objectsTilesImg[i]);
            Tile t = new Tile("", null, TileType.OBJECT, 0, 0, 254, 254, i);
            objectTiles.add(t);
        }
        imageMap.put("Objects", objectsTilesImg);
        tileMap.put("Objects", objectTiles);
    }

    private void loadEnemies() {
        BufferedImage[] enemiesTilesImg = new BufferedImage[LvlEnemyType.values().length];
        List<Tile> enemyTiles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LvlEnemyType enemyType = LvlEnemyType.values()[i];
            enemiesTilesImg[i] = Utils.getInstance().importImage("/images/data/levelEnemies/"+enemyType.getId()+".png", -1, -1);
            Tile t = new Tile("", null, TileType.ENEMY, 0, 0, 254, i, 254);
            enemyTiles.add(t);
        }
        imageMap.put("Enemies", enemiesTilesImg);
        tileMap.put("Enemies", enemyTiles);
    }

    private void loadPlayer() {
        this.playerImg = Utils.getInstance().importImage("/images/data/Player.png", -1, -1);
        this.playerTile = new Tile("", null, TileType.PLAYER, 0, 0, 100, 100, 100);
    }

    // Getters
    public Map<String, BufferedImage[]> getImageMap() {
        return imageMap;
    }

    public Map<String, List<Tile>> getTileMap() {
        return tileMap;
    }

    public BufferedImage getPlayerImg() {
        return playerImg;
    }

    public Tile getPlayerTile() {
        return playerTile;
    }
}

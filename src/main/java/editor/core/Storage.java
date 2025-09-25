package editor.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import editor.logger.LogType;
import editor.model.loader.LvlEnemyType;
import editor.model.loader.LvlObjType;
import editor.model.metadata.DecoMetadata;
import editor.model.metadata.TilesetMetadata;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;
import editor.utils.Utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

public final class Storage {

    private final Map<String, BufferedImage[]> imageMap;
    private final Map<String, List<Tile>> tileMap;
    private final Map<String, List<DecoMetadata>> decoMetadata;

    private BufferedImage playerImg;
    private Tile playerTile;

    public Storage() {
        this.imageMap = new HashMap<>();
        this.tileMap = new HashMap<>();
        this.decoMetadata = new HashMap<>();
        init();
    }

    private void init() {
        loadSolidTilesets();
        loadDecoTiles("Forest");
        loadDecoTiles("Interior");
        loadObjects();
        loadEnemies();
        loadPlayer();
        Framework.getInstance().log("Storage initialized.", LogType.INFORMATION);
    }

    private void loadSolidTilesets() {
        Gson gson = new Gson();
        String jsonPath = "/tilesets.json";

        try (InputStream is = getClass().getResourceAsStream(jsonPath);
             Reader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<ArrayList<TilesetMetadata>>(){}.getType();
            List<TilesetMetadata> tilesets = gson.fromJson(reader, listType);

            for (TilesetMetadata metadata : tilesets) {
                BufferedImage spriteSheet = Utils.getInstance().importImage(metadata.getSpritePath(), -1, -1);
                if (spriteSheet != null) {
                    loadTiles(spriteSheet, metadata.getRows(), metadata.getColumns(), metadata.getTileSizeInSprite(), metadata.getTileCount(), metadata.getName());
                    Framework.getInstance().log("Loaded solid tiles for tileset: " + metadata.getName(), LogType.INFORMATION);
                }
                else Framework.getInstance().log("Failed to load sprite sheet for tileset: " + metadata.getName(), LogType.ERROR);
            }
        } catch (Exception e) {
            Framework.getInstance().log("Failed to load tilesets.json. Error: " + e.getMessage(), LogType.ERROR);
        }
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


    private void loadDecoTiles(String tilesetName) {
        Gson gson = new Gson();
        String jsonPath = "/decos/" + tilesetName.toLowerCase() + "_decos.json";

        try (InputStream is = getClass().getResourceAsStream(jsonPath);
             Reader reader = new InputStreamReader(is)) {

            Type listType = new TypeToken<ArrayList<DecoMetadata>>(){}.getType();
            List<DecoMetadata> metadataList = gson.fromJson(reader, listType);
            decoMetadata.put(tilesetName, metadataList);

            BufferedImage[] decoImages = new BufferedImage[metadataList.size()];
            List<Tile> decoTiles = new ArrayList<>();

            for (int i = 0; i < metadataList.size(); i++) {
                DecoMetadata meta = metadataList.get(i);
                String baseFilename = (meta.getFilename() != null && !meta.getFilename().isEmpty()) ? meta.getFilename() : meta.getId();
                String imagePath = "/images/data/levelDecos/" + tilesetName.toLowerCase() + "/" + baseFilename + ".png";
                BufferedImage loadedImage = Utils.getInstance().importImage(imagePath, meta.getWid(), meta.getHei());
                if (loadedImage != null && meta.isFlipped()) {
                    decoImages[i] = Utils.getInstance().flipImage(loadedImage);
                }
                else decoImages[i] = loadedImage;

                Tile t = new Tile("", null, TileType.DECO, 0, 0, 254, 254, i);
                decoTiles.add(t);
            }

            imageMap.put(tilesetName + "Deco", decoImages);
            tileMap.put(tilesetName + "Deco", decoTiles);
            Framework.getInstance().log("Loaded " + metadataList.size() + " decorations for tileset: " + tilesetName, LogType.INFORMATION);
        } catch (Exception e) {
            Framework.getInstance().log("Failed to load decorations for tileset: " + tilesetName + ". Error: " + e.getMessage(), LogType.ERROR);
        }
    }

    private void loadObjects() {
        BufferedImage[] objectsTilesImg = new BufferedImage[LvlObjType.values().length-1];
        List<Tile> objectTiles = new ArrayList<>();
        for (int i = 0; i < objectsTilesImg.length; i++) {
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
        for (int i = 0; i < enemiesTilesImg.length-1; i++) {
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

    public String[] getTilesetNames() {
        List<String> namesList = new ArrayList<>();
        int index = 0;
        for (String name : tileMap.keySet()) {
            if (name.endsWith("Tiles")) {
                namesList.add(name.substring(0, name.indexOf("Tiles")));
            }
        }
        return namesList.toArray(new String[index]);
    }

    public Map<String, List<DecoMetadata>> getDecoMetadataMap() {
        return decoMetadata;
    }

}

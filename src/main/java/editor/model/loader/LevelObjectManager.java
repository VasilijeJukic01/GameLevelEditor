package editor.model.loader;

import editor.utils.Utils;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class LevelObjectManager {

    private LevelObject[] objects;
    private BufferedImage[] models;

    private final List<LvlObjType> objData;

    public LevelObjectManager() {
        this.objData = Arrays.asList(LvlObjType.values());
        loadImages();
        init();
    }

    private void loadImages() {
        this.models = new BufferedImage[objData.size()];
        for (int i = 0; i < models.length; i++) {
            LvlObjType objType = objData.get(i);
            String id = objType.getId();
            int bigFlag = id.contains("_BIG") ? 4 : 0;
            int reverseFlag = id.contains("_REVERSE") ? 8 : 0;
            String name = id.substring(0, id.length() - bigFlag - reverseFlag);
            models[i] = Utils.getInstance().importImage("/images/data/levelObjects/"+name+".png", objType.getWid(), objType.getHei());
            if (reverseFlag != 0) models[i] = Utils.getInstance().flipImage(models[i]);
        }
    }

    private void init() {
        this.objects = new LevelObject[objData.size()];
        for (int i = 0; i < objects.length; i++) {
            LvlObjType lvlObj = objData.get(i);
            objects[i] = new LevelObject(lvlObj, models[i].getWidth(), models[i].getHeight());
            objects[i].setYOffset(lvlObj.getYOffset());
            objects[i].setXOffset(lvlObj.getXOffset());
        }
    }

    public LevelObject[] getObjects() {
        return objects;
    }

    public BufferedImage[] getModels() {
        return models;
    }
}

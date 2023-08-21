package editor.model.loader;

import editor.core.Loader;
import editor.gui.view.EditorFrame;
import editor.model.repository.Composite;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static editor.constants.Constants.DECO_NUM;


public class LevelLoader implements Loader {

    public LevelLoader() {

    }

    @Override
    public void load(File file) {
        try {
            BufferedImage levelImg = ImageIO.read(file);
            String name = file.getName().substring(0, file.getName().indexOf("."));
            Level level = EditorFrame.getInstance().getCurrentTab().getLevel();
            level.setSize(levelImg.getWidth()/2, levelImg.getHeight());
            level.setName(name);

            level.getChildren().clear();

            getLevelData(levelImg, level);
            getObjectData(levelImg, level);
            getEnemyData(levelImg, level);
            getDecoData(levelImg, level);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLevelData(BufferedImage levelImg, Composite<Node> level) { // Red
        for (int i = 0; i < levelImg.getWidth()/2; i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getRed();
                if (value >= 49) continue;
                Tile tile = new Tile("", level, TileType.SOLID, i, j, value, 254, 254);
                if (color.getBlue() == 255 && color.getGreen() == 255) {
                    tile.setLayer(5);
                    tile.setGreen(255);
                    tile.setBlue(255);
                }
                else tile.setLayer(3);
                level.addChild(tile);
            }
        }
    }

    public void getObjectData(BufferedImage levelImg, Composite<Node> level) { // Blue
        for (int i = 0; i < levelImg.getWidth()/2; i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getBlue();
                if (value >= LvlObjType.MAX.ordinal()) continue;
                Tile tile = new Tile("", level, TileType.OBJECT, i, j, 254, 254, value);
                tile.setLayer(5);
                level.addChild(tile);
            }
        }
    }

    public void getEnemyData(BufferedImage levelImg, Composite<Node> level) { // Green
        for (int i = 0; i < levelImg.getWidth()/2; i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getGreen();
                if (value >= LvlEnemyType.MAX.ordinal()) continue;
                Tile tile = new Tile("", level, TileType.ENEMY, i, j, 254, value, 254);
                tile.setLayer(5);
                level.addChild(tile);
            }
        }
    }

    private void getDecoData(BufferedImage levelImg, Composite<Node> level) { // Blue && Green
        for (int i = levelImg.getWidth()/2; i < levelImg.getWidth(); i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int decoValue = color.getBlue();
                int layerValue = color.getGreen();
                if (decoValue >= DECO_NUM) continue;
                if (layerValue > 5) layerValue = -1;
                Tile tile = new Tile("", level, TileType.DECO, i - levelImg.getWidth()/2, j, 254, layerValue, decoValue);
                tile.setLayer(layerValue);
                level.addChild(tile);
            }
        }
    }

}

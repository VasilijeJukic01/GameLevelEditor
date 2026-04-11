package editor.model.loader;

import editor.core.Framework;
import editor.core.Loader;
import editor.gui.view.EditorFrame;
import editor.logger.LogType;
import editor.model.metadata.LevelMetadata;
import editor.model.metadata.ObjectMetadata;
import editor.model.repository.Composite;
import editor.model.repository.Node;
import editor.model.repository.components.Tile;
import editor.model.repository.components.Level;
import editor.model.repository.components.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static editor.constants.Constants.DECO_NUM;


public class LevelLoader implements Loader {

    public LevelLoader() {

    }

    @Override
    public void load(File file) {
        load(file, null);
    }

    public void load(File file, LevelMetadata metadata) {
        try {
            BufferedImage levelImg = ImageIO.read(file);
            String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
            Level level = EditorFrame.getInstance().getCurrentTab().getLevel();

            int panelWidth = levelImg.getWidth() / 3;

            level.setSize(panelWidth, levelImg.getHeight());
            level.setName(name);

            level.getChildren().clear();

            getLevelData(levelImg, level, panelWidth);
            getObjectData(levelImg, level, panelWidth);
            getEnemyData(levelImg, level, panelWidth);
            getDecoData(levelImg, level, panelWidth);
            getPlayer(levelImg, level, panelWidth);
            getTriggerData(levelImg, level, panelWidth);

            if (metadata != null) {
                applyMetadata(level, metadata);
                Framework.getInstance().log("Successfully applied metadata for " + name, LogType.INFORMATION);
            }
            Framework.getInstance().log("Level imported: "+name, LogType.INFORMATION);
        }
        catch (Exception e) {
            Framework.getInstance().log("Failed to import level: " + e.getMessage(), LogType.ERROR);
        }
    }

    private void applyMetadata(Level level, LevelMetadata metadata) {
        for (ObjectMetadata meta : metadata.getDecorations()) {
            for (Node node : level.getChildren()) {
                if (node instanceof Tile) {
                    Tile tile = (Tile) node;
                    if (tile.getTileType() == TileType.DECO && tile.getX() == meta.getX() && tile.getY() == meta.getY() && tile.getLayer() == meta.getLayer()) {
                        tile.setRotation(meta.getRotation());
                        tile.setScaleX(meta.getScaleX());
                        tile.setScaleY(meta.getScaleY());
                        break;
                    }
                }
            }
        }
    }

    private void getLevelData(BufferedImage levelImg, Composite<Node> level, int panelWidth) { // Red
        for (int i = 0; i < panelWidth; i++) {
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

    public void getObjectData(BufferedImage levelImg, Composite<Node> level, int panelWidth) { // Blue
        for (int i = 0; i < panelWidth; i++) {
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

    public void getEnemyData(BufferedImage levelImg, Composite<Node> level, int panelWidth) { // Green
        for (int i = 0; i < panelWidth; i++) {
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

    private void getDecoData(BufferedImage levelImg, Composite<Node> level, int panelWidth) { // Blue && Green
        for (int i = panelWidth; i < levelImg.getWidth(); i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int decoValue = color.getBlue();
                int layerValue = color.getGreen();
                if (decoValue >= DECO_NUM) continue;
                if (layerValue > 5) layerValue = -1;
                Tile tile = new Tile("", level, TileType.DECO, i - panelWidth, j, 254, layerValue, decoValue);
                tile.setLayer(layerValue);
                level.addChild(tile);
            }
        }
    }

    private void getPlayer(BufferedImage levelImg, Composite<Node> level, int panelWidth) {
        for (int i = 0; i < panelWidth; i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int R = color.getRed();
                int G = color.getGreen();
                int B = color.getBlue();
                if (R == 100 && G == 100 && B == 100) {
                    Tile tile = new Tile("", level, TileType.PLAYER, i, j, 100, 100, 100);
                    tile.setLayer(5);
                    level.addChild(tile);
                    return;
                }
            }
        }
    }

    private void getTriggerData(BufferedImage levelImg, Composite<Node> level, int panelWidth) {
        for (int i = panelWidth * 2; i < levelImg.getWidth(); i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int triggerValue = color.getBlue();

                if (triggerValue < LvlTriggerType.MAX.ordinal() && color.getRed() == 254 && color.getGreen() == 254) {
                    Tile tile = new Tile("", level, TileType.TRIGGER, i - (panelWidth * 2), j, 254, 254, triggerValue);
                    tile.setLayer(5);
                    level.addChild(tile);
                }
            }
        }
    }

}

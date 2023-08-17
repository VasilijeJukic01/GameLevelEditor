package editor.model.level;

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


public class LevelLoader implements Loader {

    public LevelLoader() {

    }

    @Override
    public void load(File file) {
        try {
            BufferedImage levelImg = ImageIO.read(file);
            Level level = EditorFrame.getInstance().getCurrentTab().getLevel();
            level.setSize(levelImg.getWidth(), levelImg.getHeight());
            getLevelData(levelImg, level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLevelData(BufferedImage levelImg, Composite<Node> level) { // Red
        for (int i = 0; i < levelImg.getWidth(); i++) {
            for (int j = 0; j < levelImg.getHeight(); j++) {
                Color color = new Color(levelImg.getRGB(i, j));
                int value = color.getRed();
                if (value >= 49) value = -1;
                Tile tile = new Tile("", level, TileType.SOLID, i, j, value, 255, 255);
                level.addChild(tile);
            }
        }
    }
}

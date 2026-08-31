package editor.gui.controller.levelActions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.ExportDialog;
import editor.gui.view.tab.TabView;
import editor.logger.LogType;
import editor.model.metadata.LevelMetadata;
import editor.model.metadata.ObjectMetadata;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;
import editor.settings.SettingsKey;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ExportLevelAction extends AbstractEditorAction {

    public ExportLevelAction() {
        super.putValue(NAME, "Export PI");
        super.putValue(SHORT_DESCRIPTION, "Export Pixel Image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab == null) return;

        ExportDialog dialog = new ExportDialog(EditorFrame.getInstance(), true, tab);
        dialog.setVisible(true);

        String exportType = (String) tab.getSettings().getParameter(SettingsKey.EXPORT_TYPE);
        if (exportType == null) return;

        Level level = tab.getLevel();
        int levelWidth = level.getWidth();
        int levelHeight = level.getHeight();

        BufferedImage pixelImgLeft = createPixelImage(tab, levelWidth, levelHeight, TileType.SOLID);
        BufferedImage pixelImgRight = createPixelImage(tab, levelWidth, levelHeight, TileType.DECO);
        BufferedImage pixelImgLogic = createPixelImage(tab, levelWidth, levelHeight, TileType.TRIGGER);

        if (exportType.equals("Solid tiles only")) {
            exportCombinedImage(pixelImgLeft);
            return;
        }
        else if (exportType.equals("Decoration tiles only")) {
            exportCombinedImage(pixelImgRight);
            return;
        }

        BufferedImage combinedImage = combineImages(pixelImgLeft, pixelImgRight, pixelImgLogic);
        exportCombinedImage(combinedImage);

        int result = JOptionPane.showConfirmDialog(EditorFrame.getInstance(), "Do you want to save rotation/scale metadata for this level?", "Save Metadata", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) saveMetadataAsJson(tab.getLevel());
    }

    private void saveMetadataAsJson(Level level) {
        LevelMetadata levelMetadata = new LevelMetadata();
        File file = new File(level.getName() + ".json");

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                levelMetadata = new Gson().fromJson(reader, LevelMetadata.class);
                levelMetadata.getDecorations().clear();
            } catch (Exception ex) {
                Framework.getInstance().log("Failed to read existing metadata: " + ex.getMessage(), LogType.WARNING);
            }
        }

        for (Node child : level.getChildren()) {
            Tile tile = (Tile) child;
            if (tile.getTileType() == TileType.DECO && (tile.getRotation() != 0.0 || tile.getScaleX() != 1.0 || tile.getScaleY() != 1.0)) {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setX(tile.getX());
                metadata.setY(tile.getY());
                metadata.setLayer(tile.getLayer());
                metadata.setRotation(tile.getRotation());
                metadata.setScaleX(tile.getScaleX());
                metadata.setScaleY(tile.getScaleY());
                levelMetadata.getDecorations().add(metadata);
            }
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Metadata JSON File");
        fileChooser.setSelectedFile(file);

        if (fileChooser.showSaveDialog(EditorFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter(selectedFile)) {
                gson.toJson(levelMetadata, writer);
                Framework.getInstance().log("Metadata saved to " + selectedFile.getName(), LogType.NOTIFICATION);
            } catch (Exception ex) {
                Framework.getInstance().log("Failed to save metadata JSON: " + ex.getMessage(), LogType.ERROR);
            }
        }
    }

    private BufferedImage createPixelImage(TabView tab, int width, int height, TileType tileType) {
        BufferedImage pixelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = pixelImage.createGraphics();

        g2d.setColor(new Color(254, 254, 254));
        g2d.fillRect(0, 0, width, height);

        for (Node child : tab.getLevel().getChildren()) {
            Tile t = (Tile) child;
            boolean notDeco = (t.getTileType() == TileType.ENEMY || t.getTileType() == TileType.OBJECT || t.getTileType() == TileType.PLAYER) && tileType == TileType.SOLID;
            boolean isLogic = (t.getTileType() == TileType.NPC) && tileType == TileType.TRIGGER;
            if (t.getTileType() != tileType && !notDeco && !isLogic) continue;

            int pixelX = t.getX();
            int pixelY = t.getY();
            int pixelColor = (t.getRed() << 16) | (t.getGreen() << 8) | t.getBlue();
            g2d.setColor(new Color(pixelColor));
            g2d.fillRect(pixelX, pixelY, 1, 1);
        }
        g2d.dispose();
        return pixelImage;
    }

    private BufferedImage combineImages(BufferedImage terrain, BufferedImage deco, BufferedImage logic) {
        int w = terrain.getWidth();
        int h = terrain.getHeight();

        BufferedImage combined = new BufferedImage(w * 3, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combined.createGraphics();

        g2d.drawImage(terrain, 0, 0, null);
        g2d.drawImage(deco, w, 0, null);
        g2d.drawImage(logic, w * 2, 0, null);

        g2d.dispose();
        return combined;
    }

    private void exportCombinedImage(BufferedImage image) {
        try {
            File outputFile = new File("output.png");
            ImageIO.write(image, "png", outputFile);
            Framework.getInstance().log("Level exported to pixel image successfully!", LogType.NOTIFICATION);
        }
        catch (Exception ignored) {}
    }

}

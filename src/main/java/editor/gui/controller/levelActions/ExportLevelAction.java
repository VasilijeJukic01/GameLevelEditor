package editor.gui.controller.levelActions;

import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.ExportDialog;
import editor.gui.view.tab.TabView;
import editor.logger.LogType;
import editor.model.repository.Node;
import editor.model.repository.components.Level;
import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;
import editor.settings.SettingsKey;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

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

        if (exportType.equals("Solid tiles only")) {
            fillEmptySpace(pixelImgLeft);
            exportCombinedImage(pixelImgLeft);
            return;
        }
        else if (exportType.equals("Decoration tiles only")) {
            fillEmptySpace(pixelImgRight);
            exportCombinedImage(pixelImgRight);
            return;
        }

        BufferedImage combinedImage = combineImages(pixelImgLeft, pixelImgRight);
        if (combinedImage != null) {
            fillEmptySpace(combinedImage);
            exportCombinedImage(combinedImage);
        }
    }

    private BufferedImage createPixelImage(TabView tab, int width, int height, TileType tileType) {
        BufferedImage pixelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = pixelImage.createGraphics();

        for (Node child : tab.getLevel().getChildren()) {
            Tile t = (Tile) child;
            boolean notDeco = (t.getTileType() == TileType.ENEMY || t.getTileType() == TileType.OBJECT || t.getTileType() == TileType.PLAYER) && tileType == TileType.SOLID;
            if (t.getTileType() != tileType && !notDeco) continue;

            int pixelX = t.getX();
            int pixelY = t.getY();
            int pixelColor = (t.getRed() << 16) | (t.getGreen() << 8) | t.getBlue();
            g2d.setColor(new Color(pixelColor));
            g2d.fillRect(pixelX, pixelY, 1, 1);
        }
        g2d.dispose();
        return pixelImage;
    }

    private BufferedImage combineImages(BufferedImage leftImage, BufferedImage rightImage) {
        if (leftImage.getWidth() != rightImage.getWidth() || leftImage.getHeight() != rightImage.getHeight())
            return null;

        int newWidth = 2 * leftImage.getWidth();
        int newHeight = leftImage.getHeight();
        BufferedImage combinedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = combinedImage.createGraphics();
        g2d.drawImage(leftImage, 0, 0, null);
        g2d.drawImage(rightImage, leftImage.getWidth(), 0, null);
        g2d.dispose();

        return combinedImage;
    }

    private void fillEmptySpace(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                if (red == 0 && green == 0 && blue == 0) {
                    Color newColor = new Color(254, 254, 254);
                    int newRGB = newColor.getRGB();
                    image.setRGB(x, y, newRGB);
                }
            }
        }
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

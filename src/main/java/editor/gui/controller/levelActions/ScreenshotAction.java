package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static editor.constants.Constants.TILE_SIZE;

public class ScreenshotAction extends AbstractEditorAction {

    public ScreenshotAction() {
        super.putValue(NAME, "Screenshot");
        super.putValue(SHORT_DESCRIPTION, "Screenshot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab != null) {
            JFileChooser fileChooser = new JFileChooser();
            File file;

            double prevScale = tab.getScale();
            tab.setScale(1.0);

            JPanel panel = tab.getWorkspacePanel();

            Dimension originalSize = panel.getSize();
            boolean originalVisibility = panel.isVisible();

            int desiredWidth = tab.getLevel().getWidth() * TILE_SIZE;
            int desiredHeight = tab.getLevel().getHeight() * TILE_SIZE;
            panel.setSize(desiredWidth, desiredHeight);
            panel.setVisible(true);

            BufferedImage image = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_ARGB);
            panel.paint(image.getGraphics());

            panel.setSize(originalSize);
            panel.setVisible(originalVisibility);

            if (fileChooser.showSaveDialog(EditorFrame.getInstance()) == JFileChooser.APPROVE_OPTION)
                file = fileChooser.getSelectedFile();
            else return;

            try {
                ImageIO.write(image, "PNG", new File(file.getPath() + ".png"));
                tab.setScale(prevScale);
            }
            catch (Exception ignored) {}
        }
    }

}

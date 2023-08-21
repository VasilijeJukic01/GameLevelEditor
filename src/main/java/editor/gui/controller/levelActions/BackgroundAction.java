package editor.gui.controller.levelActions;

import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.gui.view.tab.TabView;
import editor.settings.SettingsKey;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundAction extends AbstractEditorAction {

    public BackgroundAction() {
        super.putValue(NAME, "Background");
        super.putValue(SHORT_DESCRIPTION, "Background");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TabView tab = EditorFrame.getInstance().getCurrentTab();
        if (tab == null) return;

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(EditorFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if (!isValidExtension(file)) return;
                BufferedImage background = ImageIO.read(file);
                tab.getSettings().updateParameter(SettingsKey.BACKGROUND, background);

            } catch (Exception ignored) {}
        }
    }

    private boolean isValidExtension(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
        return (extension.equalsIgnoreCase("png"));
    }

}

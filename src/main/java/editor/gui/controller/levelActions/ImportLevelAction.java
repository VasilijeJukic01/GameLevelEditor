package editor.gui.controller.levelActions;

import com.google.gson.Gson;
import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.logger.LogType;
import editor.model.loader.LevelLoader;
import editor.model.metadata.LevelMetadata;
import editor.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;

public class ImportLevelAction extends AbstractEditorAction {

    public ImportLevelAction() {
        super.putValue(NAME, "Import PI");
        super.putValue(SHORT_DESCRIPTION, "Import Pixel Image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(EditorFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File imageFile = fileChooser.getSelectedFile();
                if (!Utils.getInstance().isValidExtension(imageFile)) return;

                String jsonPath = imageFile.getAbsolutePath().replaceAll("(?i)\\.png$", ".json");
                File metadataFile = new File(jsonPath);
                LevelMetadata metadata = null;

                if (metadataFile.exists()) {
                    Framework.getInstance().log("Found metadata file: " + metadataFile.getName(), LogType.INFORMATION);
                    try (FileReader reader = new FileReader(metadataFile)) {
                        metadata = new Gson().fromJson(reader, LevelMetadata.class);
                    } catch (Exception jsonEx) {
                        Framework.getInstance().log("Failed to parse metadata file: " + jsonEx.getMessage(), LogType.ERROR);
                    }
                }
                else Framework.getInstance().log("No metadata file found for " + imageFile.getName(), LogType.WARNING);

                ((LevelLoader) Framework.getInstance().getLoader()).load(imageFile, metadata);
            } catch (Exception ex) {
                Framework.getInstance().log("An error occurred during import: " + ex.getMessage(), LogType.ERROR);
            }
        }
    }

}

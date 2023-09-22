package editor.gui.controller.levelActions;

import editor.core.Framework;
import editor.gui.controller.AbstractEditorAction;
import editor.gui.view.EditorFrame;
import editor.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

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
                File file = fileChooser.getSelectedFile();
                if (!Utils.getInstance().isValidExtension(file)) return;
                Framework.getInstance().getLoader().load(file);

            } catch (Exception ignored) {}
        }
    }

}

package editor.gui.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class HelpAction extends AbstractEditorAction {

    public HelpAction() {
        super.putValue(NAME, "Help");
        super.putValue(SHORT_DESCRIPTION, "Help");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String readmePath = "src/main/resources/HELP.txt";

        try {
            File readmeFile = new File(readmePath);
            if (readmeFile.exists()) Desktop.getDesktop().edit(readmeFile);
        }
        catch (Exception ignored) {}

    }
}







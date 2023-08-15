package editor;

import editor.core.Framework;
import editor.core.Gui;
import editor.gui.SwingGui;

public class EditorMain {

    public static void main(String[] args) {
        Gui gui = new SwingGui();
        Framework framework = Framework.getInstance();
        framework.init(gui);
        framework.run();
    }

}

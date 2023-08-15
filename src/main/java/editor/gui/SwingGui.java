package editor.gui;

import editor.core.Gui;
import editor.gui.view.EditorFrame;

public class SwingGui implements Gui {

    public SwingGui() {}

    @Override
    public void start() {
        EditorFrame.getInstance().setVisible(true);
    }
}

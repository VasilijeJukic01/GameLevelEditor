package editor.gui.view;

import editor.gui.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

import static editor.constants.Constants.*;
import static editor.constants.FilePaths.EDITOR_ICON;

public class EditorFrame extends JFrame {

    private static EditorFrame instance;

    private JScrollPane scrollPaneTree;
    private JSplitPane splitPane;

    private ActionManager actionManager;

    private EditorFrame() {}

    public static EditorFrame getInstance() {
        if (instance == null) {
            instance = new EditorFrame();
            instance.init();
        }
        return instance;
    }

    private void init() {
        this.actionManager = new ActionManager();
        initWindow();
        initBars();
    }

    private void initWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage(getClass().getResource(EDITOR_ICON));
        this.setSize(SCREEN_WID, SCREEN_HEI);
        this.setIconImage(icon);
        this.setTitle("Level Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void initBars() {
        JMenuBar menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
        JToolBar toolBar = new ToolBar();
        toolBar.setBackground(TOOLBAR_COLOR);
        this.add(toolBar, BorderLayout.NORTH);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
}

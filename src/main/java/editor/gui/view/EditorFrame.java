package editor.gui.view;

import editor.core.Framework;
import editor.gui.controller.ActionManager;
import editor.model.tree.EditorTree;
import editor.model.tree.Tree;

import javax.swing.*;
import java.awt.*;

import static editor.constants.Constants.*;
import static editor.constants.FilePaths.EDITOR_ICON;

public class EditorFrame extends JFrame {

    private static volatile EditorFrame instance;

    private Tree editorTree;

    private JScrollPane scrollPaneTree;
    private JSplitPane splitPane;

    private ActionManager actionManager;

    private EditorFrame() {}

    public static EditorFrame getInstance() {
        if(instance == null) {
            synchronized (EditorFrame.class) {
                if (instance == null) {
                    instance = new EditorFrame();
                    instance.init();
                }
            }
        }
        return instance;
    }

    private void init() {
        this.actionManager = new ActionManager();
        this.editorTree = new EditorTree();
        initWindow();
        initBars();

        JTree projectExplorer = editorTree.generateTree(Framework.getInstance().getRepository().getProjectExplorer());
        ProjectView projectView = new ProjectView();

        scrollPaneTree = new JScrollPane(projectExplorer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        projectExplorer.setBackground(EXPLORER_COLOR);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPaneTree, projectView);
        this.add(splitPane);

        scrollPaneTree.setMinimumSize(new Dimension(200,150));

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

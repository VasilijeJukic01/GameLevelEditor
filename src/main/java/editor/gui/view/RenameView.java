package editor.gui.view;

import editor.model.tree.EditorTree;
import editor.model.tree.Tree;
import editor.model.tree.mvc.TreeItem;
import editor.model.tree.mvc.TreeView;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class RenameView extends JDialog {

    private final JLabel lbName = new JLabel("Name:");
    private final JTextField tfName = new JTextField();
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    public RenameView(Frame owner, boolean modal, String name) {
        super(owner, modal);
        initUI();
        tfName.setText(name);
        btnDone.addActionListener(e -> handleDoneButtonClick());
        btnClose.addActionListener(e -> dispose());
    }

    private void initUI() {
        setTitle("Rename");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 5);
        contentPane.add(lbName, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        contentPane.add(tfName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPane.add(btnDone, gbc);

        gbc.gridy = 2;
        contentPane.add(btnClose, gbc);

        setContentPane(contentPane);
    }

    private void handleDoneButtonClick() {
        String newName = tfName.getText();
        if (!newName.isEmpty()) {
            EditorFrame editorFrame = EditorFrame.getInstance();
            Tree<TreeItem, TreeView> editorTree = editorFrame.getEditorTree();
            TreeItem selectedNode = editorTree.getSelectedNode();
            selectedNode.getNode().setName(newName);
            ((EditorTree)editorTree).refreshView();
            dispose();
        }
    }
}

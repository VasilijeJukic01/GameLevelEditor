package editor.gui.view;

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

        setTitle("Rename");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
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

        tfName.setText(name);

        btnDone.addActionListener(e -> {
            if (!tfName.getText().isEmpty()) {
                EditorFrame.getInstance().getEditorTree().getSelectedNode().getNode().setName(tfName.getText());
                dispose();
            }
        });

        btnClose.addActionListener(e -> dispose());

    }
}

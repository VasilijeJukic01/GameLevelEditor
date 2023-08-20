package editor.gui.view.tab;

import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;

import javax.swing.*;
import java.awt.*;

public class EditDialog extends JDialog {

    private final JLabel lbName = new JLabel("Layer:");
    private JComboBox<Integer> cbLayer;
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    private final Tile selection;

    public EditDialog(Frame owner, boolean modal, Tile selection) {
        super(owner, modal);
        this.selection = selection;
        initComboBox();
        initUI();
        btnDone.addActionListener(e -> handleDoneButtonClick());
        btnClose.addActionListener(e -> dispose());
    }

    private void initComboBox() {
        if (selection.getTileType() == TileType.DECO)
            this.cbLayer = new JComboBox<>(new Integer[] {0, 1, 2, 4});
        else this.cbLayer = new JComboBox<>(new Integer[] {3, 5});
    }

    private void initUI() {
        setTitle("Edit Tile");
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
        contentPane.add(cbLayer, gbc);

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
        Integer layer = (Integer) cbLayer.getSelectedItem();
        if (layer != null) {
            selection.setLayer(layer);
            if (selection.getTileType() == TileType.DECO) selection.setGreen(layer);
        }
    }

}

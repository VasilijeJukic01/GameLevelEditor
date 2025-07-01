package editor.gui.view.tab;

import editor.model.repository.components.Tile;
import editor.model.repository.components.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EditDialog extends JDialog {

    private final JLabel lbName = new JLabel("Layer:");
    private JComboBox<Integer> cbLayer;
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    private RotationAndScalePanel rotationAndScalePanel;
    private final JLabel lbRotationValue = new JLabel();
    private final JLabel lbScaleValue = new JLabel();

    private final Tile selection;

    public EditDialog(Frame owner, boolean modal, Tile selection) {
        super(owner, modal);
        this.selection = selection;
        initComboBox();

        if (selection.getTileType() == TileType.DECO) {
            this.rotationAndScalePanel = new RotationAndScalePanel(selection.getRotation(), selection.getScaleX());
            this.rotationAndScalePanel.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    applyChanges();
                    updateLabels();
                }
            });
        }
        initUI();
        updateLabels();
        btnDone.addActionListener(e -> {
            applyChanges();
            dispose();
        });
        btnClose.addActionListener(e -> dispose());
    }

    private void initComboBox() {
        if (selection.getTileType() == TileType.DECO) this.cbLayer = new JComboBox<>(new Integer[]{0, 1, 2, 4});
        else this.cbLayer = new JComboBox<>(new Integer[]{3, 5});
        cbLayer.setSelectedItem(selection.getLayer());
    }

    private void updateLabels() {
        if (rotationAndScalePanel != null) {
            lbRotationValue.setText(String.format("%.1f°", rotationAndScalePanel.getRotationAngle()));
            lbScaleValue.setText(String.format("%.2fx", rotationAndScalePanel.getScale()));
        }
    }

    private void initUI() {
        setTitle("Edit Tile");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        int currentRow = 0;

        gbc.gridx = 0;
        gbc.gridy = currentRow;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lbName, gbc);

        gbc.gridx = 1;
        contentPane.add(cbLayer, gbc);
        currentRow++;

        if (selection.getTileType() == TileType.DECO) {
            gbc.gridx = 0;
            gbc.gridy = currentRow;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            contentPane.add(rotationAndScalePanel, gbc);
            currentRow++;

            JPanel valuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            valuePanel.add(new JLabel("Rotation:"));
            valuePanel.add(lbRotationValue);
            valuePanel.add(new JLabel("Scale:"));
            valuePanel.add(lbScaleValue);
            gbc.gridy = currentRow;
            contentPane.add(valuePanel, gbc);
            currentRow++;
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnDone);
        buttonPanel.add(btnClose);
        gbc.gridy = currentRow;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPane.add(buttonPanel, gbc);

        setContentPane(contentPane);
        setPreferredSize(new Dimension(380, 350));
        pack();
        revalidate();
        repaint();
    }

    private void applyChanges() {
        Integer layer = (Integer) cbLayer.getSelectedItem();
        if (layer != null) {
            selection.setLayer(layer);
            if (selection.getTileType() == TileType.DECO) selection.setGreen(layer);
        }

        if (selection.getTileType() == TileType.DECO && rotationAndScalePanel != null) {
            selection.setRotation(rotationAndScalePanel.getRotationAngle());
            double scale = rotationAndScalePanel.getScale();
            selection.setScaleX(scale);
            selection.setScaleY(scale);
        }
    }

}

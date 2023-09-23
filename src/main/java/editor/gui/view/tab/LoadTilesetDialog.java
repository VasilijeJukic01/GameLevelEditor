package editor.gui.view.tab;

import editor.core.Framework;
import editor.gui.view.EditorFrame;
import editor.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LoadTilesetDialog extends JDialog {

    private final JLabel lbName = new JLabel("Upload tileset:");
    private final JButton btnSelect = new JButton("Select");
    private final JLabel lbTileSize = new JLabel("Tile size:");
    private final JTextField tfTileSize = new JTextField();
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");


    public LoadTilesetDialog(Frame owner, boolean modal) {
        super(owner, modal);
        initUI();
        btnSelect.addActionListener(e -> handleSelectButtonClick());
        btnDone.addActionListener(e -> dispose());
        btnClose.addActionListener(e -> dispose());
    }

    private void initUI() {
        setTitle("Load Tileset");
        setSize(350, 250);
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
        contentPane.add(btnSelect, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lbTileSize, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        contentPane.add(tfTileSize, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPane.add(btnDone, gbc);

        gbc.gridy = 4;
        contentPane.add(btnClose, gbc);

        setContentPane(contentPane);
    }

    private void handleSelectButtonClick() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(EditorFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if (!Utils.getInstance().isValidExtension(file)) return;

                int tileSize = Integer.parseInt(tfTileSize.getText());

                BufferedImage sprite = ImageIO.read(file);
                int rows = sprite.getHeight() / tileSize;
                int cols = sprite.getWidth() / tileSize;


                Framework.getInstance().getStorage().loadTiles(sprite, rows, cols, tileSize, rows*cols, "Custom");
                this.setVisible(false);

            }
            catch (Exception ignored) {}
        }
    }

}

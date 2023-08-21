package editor.gui.view.tab;

import editor.model.repository.components.Level;

import javax.swing.*;
import java.awt.*;

public class OptionsDialog extends JDialog {

    private final JLabel lbWid = new JLabel("Level width:");
    private final JLabel lbHei = new JLabel("Level height:");
    private final JTextField tfWid = new JTextField();
    private final JTextField tfHei = new JTextField();
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    private final Level level;

    public OptionsDialog(Frame owner, boolean modal, Level level) {
        super(owner, modal);
        this.level = level;
        setupTextFields();
        initUI();
        btnDone.addActionListener(e -> handleDoneButtonClick());
        btnClose.addActionListener(e -> dispose());
    }

    private void setupTextFields() {
        this.tfWid.setText(""+level.getWidth());
        this.tfHei.setText(""+level.getHeight());
    }

    private void initUI() {
        setTitle("Level options");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 5);
        contentPane.add(lbWid, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        contentPane.add(tfWid, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lbHei, gbc);

        gbc.gridx = 1;
        contentPane.add(tfHei, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPane.add(btnDone, gbc);

        gbc.gridy = 3;
        contentPane.add(btnClose, gbc);

        setContentPane(contentPane);
    }

    private void handleDoneButtonClick() {
        try {
            int wid = Integer.parseInt(tfWid.getText());
            int hei = Integer.parseInt(tfHei.getText());
            if (wid < 100 && hei < 100) level.setSize(wid, hei);
        }
        catch (Exception ignored) {}
    }

}

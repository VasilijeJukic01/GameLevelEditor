package editor.gui.view.tab;

import editor.model.repository.components.Level;
import editor.settings.SettingsKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OptionsDialog extends JDialog {

    private final JLabel lbWid = new JLabel("Level width:");
    private final JLabel lbHei = new JLabel("Level height:");
    private final JLabel lbGridColor = new JLabel("Grid Color:");
    private final JLabel lbFadeColor = new JLabel("Fade Color:");

    private final JTextField tfWid = new JTextField();
    private final JTextField tfHei = new JTextField();

    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");
    private final JButton btnGridColor = new JButton("Select");
    private final JButton btnFadeColor = new JButton("Select");

    private Color selectedGridColor;
    private Color selectedFadeColor;

    private final TabView tab;
    private final Level level;

    public OptionsDialog(Frame owner, boolean modal, TabView tab) {
        super(owner, modal);
        this.tab = tab;
        this.level = tab.getLevel();
        setupComponents();
        initUI();
        btnDone.addActionListener(this::handleDoneButtonClick);
        btnClose.addActionListener(e -> dispose());
        btnGridColor.addActionListener(e -> openColorPicker(selectedGridColor, "Select Grid Color", color -> selectedGridColor = color));
        btnFadeColor.addActionListener(e -> openColorPicker(selectedFadeColor, "Select Fade Color", color -> selectedFadeColor = color));
    }

    private void setupComponents() {
        this.tfWid.setText("" + level.getWidth());
        this.tfHei.setText("" + level.getHeight());
        this.selectedGridColor = (Color) tab.getSettings().getParameter(SettingsKey.GRID_COLOR);
        this.selectedFadeColor = (Color) tab.getSettings().getParameter(SettingsKey.FADE_COLOR);
    }

    private void initUI() {
        setTitle("Level options");
        setSize(300, 300);
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
        contentPane.add(lbGridColor, gbc);

        gbc.gridx = 1;
        contentPane.add(btnGridColor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(lbFadeColor, gbc);

        gbc.gridx = 1;
        contentPane.add(btnFadeColor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        contentPane.add(btnDone, gbc);

        gbc.gridy = 5;
        contentPane.add(btnClose, gbc);

        setContentPane(contentPane);
    }

    private void handleDoneButtonClick(ActionEvent e) {
        try {
            int wid = Integer.parseInt(tfWid.getText());
            int hei = Integer.parseInt(tfHei.getText());
            if (wid < 100 && hei < 100) {
                level.setSize(wid, hei);
                tab.getSettings().updateParameter(SettingsKey.GRID_COLOR, selectedGridColor);
                tab.getSettings().updateParameter(SettingsKey.FADE_COLOR, selectedFadeColor);
            }
        }
        catch (Exception ignored) {}
    }

    private void openColorPicker(Color initialColor, String title, ColorConsumer colorConsumer) {
        Color selectedColor = JColorChooser.showDialog(this, title, initialColor);
        if (selectedColor != null) colorConsumer.accept(selectedColor);
    }

    @FunctionalInterface
    private interface ColorConsumer {
        void accept(Color color);
    }

}

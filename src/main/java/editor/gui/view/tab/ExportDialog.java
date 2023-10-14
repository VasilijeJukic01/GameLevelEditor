package editor.gui.view.tab;

import editor.settings.SettingsKey;

import javax.swing.*;
import java.awt.*;

public class ExportDialog extends JDialog {

    private final TabView tabView;

    private final JLabel lbName = new JLabel("Export options:");
    private final JComboBox<String> cbExportType = new JComboBox<>();
    private final JButton btnDone = new JButton("Done");
    private final JButton btnClose = new JButton("Close");

    public ExportDialog(Frame owner, boolean modal, TabView tabView) {
        super(owner, modal);
        this.tabView = tabView;
        initComboBox();
        initUI();
        btnDone.addActionListener(e -> handleDoneButtonClick());
        btnClose.addActionListener(e -> dispose());
    }

    private void initComboBox() {
        cbExportType.addItem("Both");
        cbExportType.addItem("Solid tiles only");
        cbExportType.addItem("Decoration tiles only");
    }

    private void initUI() {
        setTitle("Export options");
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
        contentPane.add(cbExportType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(btnDone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(btnClose, gbc);

        setContentPane(contentPane);
    }

    private void handleDoneButtonClick() {
        String exportType = (String) cbExportType.getSelectedItem();
        if (exportType == null) return;
        tabView.getSettings().updateParameter(SettingsKey.EXPORT_TYPE, exportType);
        dispose();
    }

}

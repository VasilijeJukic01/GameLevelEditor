package editor.gui.view.tab;

import editor.settings.Settings;
import editor.settings.SettingsKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class SidePanel extends JPanel {

    private final TabView tabView;

    private JCheckBox[] layerCheckBoxes;
    private final String[] layerNames = {"Layer 0 [Deco]", "Layer 1 [Deco]", "Layer 2 [Deco]", "Layer 3 [Solid]", "Layer 4 [Deco]", "Layer 5 [Solid]", "All Layers"};

    public SidePanel(TabView tabView) {
        this.tabView = tabView;
        init();
    }

    private void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));
        this.layerCheckBoxes = new JCheckBox[layerNames.length];
        initLayers(panel);
        initFade(panel);
        this.add(panel);
    }

    private void initLayers(JPanel panel) {
        JLabel[] labels = new JLabel[layerNames.length];
        panel.add(new JLabel("Show Layers: "));
        panel.add(new JLabel());
        for (int i = 0; i < layerNames.length; i++) {
            labels[i] = new JLabel(layerNames[i]);
            layerCheckBoxes[i] = new JCheckBox();
            panel.add(labels[i]);
            panel.add(layerCheckBoxes[i]);
            if (i != layerNames.length-1) layerCheckBoxes[i].addItemListener(this::resetLastCheckBox);
        }
        layerCheckBoxes[layerNames.length-1].setSelected(true);
        layerCheckBoxes[layerNames.length-1].addItemListener(this::resetCheckBoxes);
    }

    private void initFade(JPanel panel) {
        JCheckBox fadeCheckBox = new JCheckBox();
        panel.add(new JSeparator());
        panel.add(new JSeparator());
        panel.add(new JLabel("Enable Fade: "));
        panel.add(new JLabel());
        panel.add(new JLabel("Fade"));
        panel.add(fadeCheckBox);
        fadeCheckBox.addItemListener(this::changeFade);
    }

    // Reset
    private void resetCheckBoxes(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            for (int i = 0; i < layerCheckBoxes.length - 1; i++) {
                layerCheckBoxes[i].setSelected(false);
            }
            layerCheckBoxes[layerCheckBoxes.length - 1].setSelected(true);
            tabView.getSettings().updateParameter(SettingsKey.LAYERS_INFO, "0000001");
        }
        else {
            tabView.getSettings().updateParameter(SettingsKey.LAYERS_INFO, "0000000");
        }
    }

    private void resetLastCheckBox(ItemEvent e) {
        layerCheckBoxes[layerCheckBoxes.length-1].setSelected(false);
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < layerCheckBoxes.length - 1; i++) {
            if (layerCheckBoxes[i].isSelected()) binaryString.append("1");
            else binaryString.append("0");
        }
        binaryString.append("0");
        tabView.getSettings().updateParameter(SettingsKey.LAYERS_INFO, binaryString.toString());
    }

    private void changeFade(ItemEvent e) {
        Settings settings = tabView.getSettings();
        int fade = (int) settings.getParameter(SettingsKey.FADE);
        settings.updateParameter(SettingsKey.FADE, fade ^ 1);
    }

}

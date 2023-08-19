package editor.gui.view.tab;

import editor.model.repository.components.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class SidePanel extends JPanel {

    private Level level;

    private JCheckBox[] layerCheckBoxes;
    private final String[] layerNames = {"Layer 0 [Deco]", "Layer 1 [Deco]", "Layer 2 [Deco]", "Layer 3 [Solid]", "Layer 4 [Deco]", "Layer 5 [Solid]", "All Layers"};
    private JCheckBox fadeCheckBox;

    public SidePanel(Level level) {
        this.level = level;
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
        this.fadeCheckBox = new JCheckBox();
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
        }
        level.notify(level);
    }

    private void resetLastCheckBox(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED)
            layerCheckBoxes[layerNames.length-1].setSelected(false);
        level.notify(level);
    }

    private void changeFade(ItemEvent e) {
        level.notify(level);
    }

    // Getters & Setters
    public JCheckBox[] getLayerCheckBoxes() {
        return layerCheckBoxes;
    }

    public JCheckBox getFadeCheckBox() {
        return fadeCheckBox;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}

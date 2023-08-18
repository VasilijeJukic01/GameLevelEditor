package editor.gui.view.tab;

import editor.model.repository.components.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class SidePanel extends JPanel {

    private Level level;

    private JCheckBox[] checkBoxes;
    private final String[] layerNames = {"Layer 0 [Deco]", "Layer 1 [Deco]", "Layer 2 [Deco]", "Layer 3 [Solid]", "Layer 4 [Deco]", "Layer 5 [Solid]", "All Layers"};

    public SidePanel(Level level) {
        this.level = level;
        init();
    }

    private void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel[] labels = new JLabel[layerNames.length];
        this.checkBoxes = new JCheckBox[layerNames.length];

        panel.add(new JLabel("Show Layers: "));
        panel.add(new JLabel());
        for (int i = 0; i < layerNames.length; i++) {
            labels[i] = new JLabel(layerNames[i]);
            checkBoxes[i] = new JCheckBox();
            panel.add(labels[i]);
            panel.add(checkBoxes[i]);
            if (i != layerNames.length-1) checkBoxes[i].addItemListener(this::resetLastCheckBox);
        }
        checkBoxes[layerNames.length-1].setSelected(true);
        checkBoxes[layerNames.length-1].addItemListener(this::resetCheckBoxes);

        this.add(panel);
    }

    // Reset
    private void resetCheckBoxes(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            for (int i = 0; i < checkBoxes.length - 1; i++) {
                checkBoxes[i].setSelected(false);
            }
        }
        level.notify(level);
    }

    private void resetLastCheckBox(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED)
            checkBoxes[layerNames.length-1].setSelected(false);
        level.notify(level);
    }

    // Getters & Setters
    public JCheckBox[] getCheckBoxes() {
        return checkBoxes;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}

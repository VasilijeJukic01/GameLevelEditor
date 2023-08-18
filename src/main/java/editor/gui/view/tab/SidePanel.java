package editor.gui.view.tab;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {

    private JCheckBox[] checkBoxes;

    public SidePanel() {
        init();
    }

    private void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        String[] layerNames = {"Layer 1", "Layer 2", "Layer 3", "Layer 4", "Layer 5", "Layer 6", "All Layers"};
        JLabel[] labels = new JLabel[layerNames.length];
        checkBoxes = new JCheckBox[layerNames.length];

        panel.add(new JLabel("Show Layers: "));
        panel.add(new JLabel());
        for (int i = 0; i < layerNames.length; i++) {
            labels[i] = new JLabel(layerNames[i]);
            checkBoxes[i] = new JCheckBox();
            panel.add(labels[i]);
            panel.add(checkBoxes[i]);
        }
        checkBoxes[layerNames.length-1].setSelected(true);

        this.add(panel);
    }

    public JCheckBox[] getCheckBoxes() {
        return checkBoxes;
    }
}

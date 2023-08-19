package editor.gui.view.tab;

import editor.gui.view.renderer.LevelRenderer;
import editor.gui.view.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.*;

public class BottomPanel extends JPanel {

    private final BufferedImage[] forestSprite;
    private int selectedIndex = -1;
    private int lastSelectedIndex = -1;

    public BottomPanel(Renderer levelRenderer) {
        this.forestSprite = ((LevelRenderer)levelRenderer).getForestSprite();
        init();
    }

    private void init() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(10, 0, 10, 0);

        for (int itemIndex = 0; itemIndex < FOREST_TILES; itemIndex++) {
            int row = itemIndex / EDITOR_PICKER_COL;
            int col = itemIndex % EDITOR_PICKER_COL;

            if (row >= EDITOR_PICKER_ROW) break;

            constraints.gridx = col;
            constraints.gridy = row;
            ImagePanel imagePanel = new ImagePanel(forestSprite[itemIndex], itemIndex);
            add(imagePanel, constraints);
        }
    }

    // Image panel
    private class ImagePanel extends JPanel {

        private final BufferedImage image;
        private final int index;

        public ImagePanel(BufferedImage image, int index) {
            this.image = image;
            this.index = index;
            setupPanel();
            setupMouseListener();
        }

        private void setupPanel() {
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        }

        private void setupMouseListener() {
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lastSelectedIndex = selectedIndex;
                    selectedIndex = ImagePanel.this.index;
                    repaint();

                    if (lastSelectedIndex != -1) {
                        Component[] components = getParent().getComponents();
                        if (lastSelectedIndex < components.length) {
                            components[lastSelectedIndex].repaint();
                        }
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, TILE_SIZE, TILE_SIZE, null);

            if (selectedIndex == index) {
                g.setColor(Color.RED);
                g.drawRect(0, 0, 63, 63);
            }
        }

    }

}

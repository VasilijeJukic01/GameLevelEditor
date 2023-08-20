package editor.gui.view.tab;

import editor.core.Framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static editor.constants.Constants.*;

public class BottomPanel extends JPanel {

    private final BufferedImage[] tiles;
    private final BufferedImage[] decorations;
    private JPanel panel;
    private int selectedIndex = -1;
    private int lastSelectedIndex = -1;

    public BottomPanel() {
        this.tiles = Framework.getInstance().getStorage().getForestTilesImg();
        this.decorations = Framework.getInstance().getStorage().getForestDecoTilesImg();
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout());

        panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);

        initTopPanel();

        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(10, 0, 10, 0);

        for (int itemIndex = 0; itemIndex < FOREST_TILES; itemIndex++) {
            if (fillGridLayout(constraints, itemIndex, tiles)) break;
        }
        this.add(panel, BorderLayout.CENTER);
    }

    private boolean fillGridLayout(GridBagConstraints constraints, int itemIndex, BufferedImage[] tiles) {
        int row = itemIndex / EDITOR_PICKER_COL;
        int col = itemIndex % EDITOR_PICKER_COL;

        if (row >= EDITOR_PICKER_ROW) return true;

        constraints.gridx = col;
        constraints.gridy = row;
        ImagePanel imagePanel = new ImagePanel(tiles[itemIndex], itemIndex);
        panel.add(imagePanel, constraints);
        return false;
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Select:");
        String[] comboBoxItems = {"Solid Tiles", "Objects", "Decorations"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxItems);
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();
                updateImagePanel(selectedItem);
            }
        });
        topPanel.add(label);
        topPanel.add(comboBox);
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void updateImagePanel(String selectedItem) {
        panel.removeAll();

        BufferedImage[] selectedImages = null;

        if (selectedItem.equals("Solid Tiles")) selectedImages = tiles;
        else if (selectedItem.equals("Decorations")) selectedImages = decorations;

        if (selectedImages != null) {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 1.0;
            constraints.weighty = 1.0;
            constraints.insets = new Insets(10, 0, 10, 0);

            for (int itemIndex = 0; itemIndex < selectedImages.length; itemIndex++) {
                if (fillGridLayout(constraints, itemIndex, selectedImages)) break;
            }
        }
        panel.revalidate();
        panel.repaint();
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

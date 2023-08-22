package editor.gui.view.tab;

import editor.core.Framework;
import editor.settings.SettingsKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static editor.constants.Constants.*;

public class BottomPanel extends JPanel {

    private final TabView tabView;

    private BufferedImage[] tiles;
    private BufferedImage[] decorations;
    private BufferedImage[] objects;
    private BufferedImage[] enemies;

    private final BufferedImage[] player;

    private JPanel panel;
    private int selectedIndex = -1;
    private int lastSelectedIndex = -1;

    private JComboBox<Integer> cbLayers;
    private JComboBox<String> cbTypes, cbTileset;

    public BottomPanel(TabView tabView) {
        this.tabView = tabView;
        loadTileset();
        this.player = new BufferedImage[]{Framework.getInstance().getStorage().getPlayerImg()};
        init();
    }

    private void loadTileset() {
        String set = (String) tabView.getSettings().getParameter(SettingsKey.TILE_SET);
        this.tiles = Framework.getInstance().getStorage().getImageMap().get(set+"Tiles");
        this.decorations = Framework.getInstance().getStorage().getImageMap().get(set+"Deco");
        this.objects = Framework.getInstance().getStorage().getImageMap().get("Objects");
        this.enemies = Framework.getInstance().getStorage().getImageMap().get("Enemies");
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

        for (int itemIndex = 0; itemIndex < 48; itemIndex++) {
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
        JLabel lbSelect = new JLabel("Select:");
        String[] cbTypeNames = {"Solid Tiles", "Decorations", "Objects", "Enemies", "Player"};
        this.cbTypes = new JComboBox<>(cbTypeNames);
        cbTypes.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();
                updateImagePanel(selectedItem);
            }
        });

        JLabel lbLayer = new JLabel("Layer:");
        cbLayers = new JComboBox<>(new Integer[]{3, 5});
        cbLayers.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Integer selectedItem = (Integer) e.getItem();
                saveLayer(selectedItem);
            }
        });


        JLabel lbSet = new JLabel("Tileset:");
        cbTileset = new JComboBox<>(new String[]{"Forest", "Custom"});
        cbTileset.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();
                getTileset(selectedItem);
            }
        });

        topPanel.add(lbSet);
        topPanel.add(cbTileset);
        topPanel.add(lbSelect);
        topPanel.add(cbTypes);
        topPanel.add(lbLayer);
        topPanel.add(cbLayers);
        this.add(topPanel, BorderLayout.NORTH);
    }

    private void saveLayer(Integer selectedItem) {
        tabView.getSettings().updateParameter(SettingsKey.SELECTED_LAYER, selectedItem);
    }

    private void getTileset(String selectedItem) {
        tabView.getSettings().updateParameter(SettingsKey.TILE_SET, selectedItem);
        reload();
    }

    private void updateImagePanel(String selectedItem) {
        panel.removeAll();

        BufferedImage[] selectedImages = null;
        String selectedSet = "";
        Integer[] layerOptions = null;

        if (selectedItem.equals("Solid Tiles")) {
            selectedSet = "Solid Tiles";
            layerOptions = new Integer[]{3, 5};
            selectedImages = tiles;
        }
        else if (selectedItem.equals("Decorations")) {
            selectedSet = "Decorations";
            layerOptions = new Integer[]{0, 1, 2, 4};
            selectedImages = decorations;
        }
        else if (selectedItem.equals("Objects")) {
            selectedSet = "Objects";
            layerOptions = new Integer[]{5};
            selectedImages = objects;
        }
        else if (selectedItem.equals("Enemies")) {
            selectedSet = "Enemies";
            layerOptions = new Integer[]{5};
            selectedImages = enemies;
        }
        else if (selectedItem.equals("Player")) {
            selectedSet = "Player";
            layerOptions = new Integer[]{5};
            selectedImages = player;
        }

        if (selectedImages != null) {
            updateSettings(selectedSet, layerOptions);
            displaySelectedImages(selectedImages);
        }
        panel.revalidate();
        panel.repaint();
    }

    private void updateSettings(String selectedSet, Integer[] layerOptions) {
        tabView.getSettings().updateParameter(SettingsKey.SELECTED_SET, selectedSet);
        cbLayers.setModel(new DefaultComboBoxModel<>(layerOptions));
        tabView.getSettings().updateParameter(SettingsKey.SELECTED_LAYER, layerOptions[0]);
    }

    private void displaySelectedImages(BufferedImage[] selectedImages) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(10, 0, 10, 0);

        for (int itemIndex = 0; itemIndex < selectedImages.length; itemIndex++) {
            if (fillGridLayout(constraints, itemIndex, selectedImages)) break;
        }
    }

    public void reload() {
        loadTileset();
        cbTileset.setSelectedItem(tabView.getSettings().getParameter(SettingsKey.TILE_SET));
        updateImagePanel((String) Objects.requireNonNull(cbTypes.getSelectedItem()));
    }

    // [Class] Image panel
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
                    tabView.getSettings().updateParameter(SettingsKey.SELECTED_TILE, selectedIndex);
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
                g.drawRect(0, 0, TILE_SIZE - 1, TILE_SIZE - 1);
            }
        }

    }

}

package editor.gui.view.tab;

import editor.gui.view.renderer.LevelRenderer;
import editor.gui.view.renderer.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class BottomPanel extends JPanel {

    private BufferedImage[] forestSprite;
    private int selectedSpriteIndex = -1;
    private int lastSelectedIndex = -1;

    public BottomPanel(Renderer levelRenderer) {
        this.forestSprite = ((LevelRenderer)levelRenderer).getForestSprite();
        init();
    }

    private void init() {
        int rows = 10, cols = 10;
        int hGap = 0, vGap = 10;
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(vGap, hGap, vGap, hGap); // Postavite razmake između komponenti

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (((row * cols) + col) >= 49) break;
                gbc.gridx = col;
                gbc.gridy = row;

                ImagePanel imagePanel = new ImagePanel(forestSprite[(row * cols) + col], (row * cols) + col);
                add(imagePanel, gbc);
            }
            if (((row * cols)) >= 49) break;
        }
    }

    private class ImagePanel extends JPanel {
        private BufferedImage image;
        private int index;

        public ImagePanel(BufferedImage image, int index) {
            BorderLayout borderLayout = new BorderLayout();
            borderLayout.setVgap(0);
            this.setLayout(borderLayout);
            this.image = image;
            this.index = index;
            setPreferredSize(new Dimension(64, 64));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    lastSelectedIndex = selectedSpriteIndex;
                    selectedSpriteIndex = ImagePanel.this.index;
                    repaint();

                    if (lastSelectedIndex != -1) {
                        // Ako postoji prethodno izabrani indeks, ažuriraj ga i osveži
                        Component[] components = getParent().getComponents();
                        if (lastSelectedIndex < components.length) {
                            Component lastSelectedComponent = components[lastSelectedIndex];
                            lastSelectedComponent.repaint();
                        }
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 64, 64, null);

            if (selectedSpriteIndex == index) {
                g.setColor(Color.RED);
                g.drawRect(0, 0, 63, 63);
            }
        }
    }



}

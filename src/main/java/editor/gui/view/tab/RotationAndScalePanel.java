package editor.gui.view.tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class RotationAndScalePanel extends JPanel {

    private double rotationAngle;
    private double scale;

    private final int PANEL_SIZE = 120;
    private final int KNOB_RADIUS = 50;
    private final int SCALE_BAR_HEIGHT = 100;
    private final int SCALE_BAR_WIDTH = 15;
    private final int SCALE_HANDLE_HEIGHT = 10;

    private final Point knobCenter = new Point(PANEL_SIZE / 2, PANEL_SIZE / 2);
    private final Rectangle scaleBar = new Rectangle(PANEL_SIZE + 20, (PANEL_SIZE - SCALE_BAR_HEIGHT) / 2, SCALE_BAR_WIDTH, SCALE_BAR_HEIGHT);

    private boolean isDraggingRotation = false;
    private boolean isDraggingScale = false;

    public RotationAndScalePanel(double initialRotation, double initialScale) {
        this.rotationAngle = initialRotation;
        this.scale = initialScale;

        setPreferredSize(new Dimension(PANEL_SIZE + 60, PANEL_SIZE));

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (new Ellipse2D.Double(knobCenter.x - KNOB_RADIUS, knobCenter.y - KNOB_RADIUS, KNOB_RADIUS * 2, KNOB_RADIUS * 2).contains(e.getPoint()))
                    isDraggingRotation = true;
                else if (getScaleHandleRect().contains(e.getPoint()) || scaleBar.contains(e.getPoint())) isDraggingScale = true;
                updateValues(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDraggingRotation = false;
                isDraggingScale = false;
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateValues(e);
            }
        });
    }

    private void updateValues(MouseEvent e) {
        if (isDraggingRotation) {
            int dx = e.getX() - knobCenter.x;
            int dy = e.getY() - knobCenter.y;
            rotationAngle = (Math.toDegrees(Math.atan2(dy, dx)) + 360) % 360;
        }
        else if (isDraggingScale) {
            int y = Math.max(scaleBar.y, Math.min(e.getY(), scaleBar.y + scaleBar.height));
            double newScale = 2.0 - ((double)(y - scaleBar.y) / SCALE_BAR_HEIGHT) * 1.9;
            scale = Math.round(newScale * 100.0) / 100.0;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(new Ellipse2D.Double(knobCenter.x - KNOB_RADIUS, knobCenter.y - KNOB_RADIUS, KNOB_RADIUS * 2, KNOB_RADIUS * 2));
        g2d.setColor(Color.DARK_GRAY);
        g2d.draw(new Ellipse2D.Double(knobCenter.x - KNOB_RADIUS, knobCenter.y - KNOB_RADIUS, KNOB_RADIUS * 2, KNOB_RADIUS * 2));

        double rads = Math.toRadians(rotationAngle);
        int handleX = (int) (knobCenter.x + (KNOB_RADIUS - 5) * Math.cos(rads));
        int handleY = (int) (knobCenter.y + (KNOB_RADIUS - 5) * Math.sin(rads));
        g2d.setColor(new Color(220, 50, 50));
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Line2D.Double(knobCenter.x, knobCenter.y, handleX, handleY));

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(scaleBar);
        g2d.setColor(Color.DARK_GRAY);
        g2d.draw(scaleBar);

        g2d.setColor(new Color(50, 150, 220));
        g2d.fill(getScaleHandleRect());
        g2d.setColor(Color.BLACK);
        g2d.draw(getScaleHandleRect());

        g2d.dispose();
    }

    private Rectangle getScaleHandleRect() {
        int scaleHandleY = scaleBar.y + (int) (SCALE_BAR_HEIGHT * (1.0 - ((this.scale - 0.1) / 1.9)));
        return new Rectangle(scaleBar.x, scaleHandleY - SCALE_HANDLE_HEIGHT / 2, SCALE_BAR_WIDTH, SCALE_HANDLE_HEIGHT);
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public double getScale() {
        return scale;
    }
}

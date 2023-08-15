package editor.gui.view.style;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedButton extends BasicButtonUI {

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        b.setBackground(new Color(6, 154, 142));
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setFont(b.getFont().deriveFont(Font.BOLD));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        int borderRadius = 0;

        if (model.isPressed()) g.setColor(new Color(44, 62, 80));
        else if (model.isRollover()) g.setColor(new Color(30, 120, 100));
        else g.setColor(button.getBackground());

        g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), borderRadius, borderRadius);
        super.paint(g, c);
    }
}

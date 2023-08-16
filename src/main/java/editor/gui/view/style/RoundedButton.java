package editor.gui.view.style;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

import static editor.constants.Constants.*;

public class RoundedButton extends BasicButtonUI {

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        b.setBackground(BUTTON_COLOR);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setFont(b.getFont().deriveFont(Font.BOLD));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        int borderRadius = 0;

        if (model.isPressed()) g.setColor(BUTTON_PRESS_COLOR);
        else if (model.isRollover()) g.setColor(BUTTON_HOVER_COLOR);
        else g.setColor(button.getBackground());

        g.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), borderRadius, borderRadius);
        super.paint(g, c);
    }
}

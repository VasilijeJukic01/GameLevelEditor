package editor.gui.controller;

import editor.gui.view.style.RoundedButton;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;

public abstract class AbstractEditorAction extends AbstractAction {

    public Optional<Icon> loadIcon(String file) {
        return Optional.ofNullable(getClass().getResource(file))
                .map(AbstractEditorAction::createImageIcon);
    }

    private static Icon createImageIcon(URL url) {
        return new ImageIcon(url);
    }

    public JButton createButton() {
        JButton button = new JButton(this);
        button.setUI(new RoundedButton());
        return button;
    }

}

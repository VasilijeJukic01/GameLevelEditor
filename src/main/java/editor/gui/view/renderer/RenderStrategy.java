package editor.gui.view.renderer;

import editor.model.repository.Node;

import java.awt.*;

public interface RenderStrategy<T extends Node> {

    void render(Graphics g, T item, int layer);

}

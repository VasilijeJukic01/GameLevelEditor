package editor.state.states;

import editor.gui.view.tab.TabView;
import editor.state.State;

import java.awt.*;

public class MoveState implements State<TabView> {

    private Point startPoint;

    @Override
    public void clickPerform(int x, int y, TabView tabView) {
        this.startPoint = new Point(x, y);
    }

    @Override
    public void dragPerform(int x, int y, TabView tabView) {
        Point endPoint = new Point(x, y);

        double dx = (endPoint.getX() - startPoint.getX()) * tabView.getScale();
        double dy = (endPoint.getY() - startPoint.getY()) * tabView.getScale();

        tabView.move(-dx, -dy);
        startPoint = endPoint;
    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }

}

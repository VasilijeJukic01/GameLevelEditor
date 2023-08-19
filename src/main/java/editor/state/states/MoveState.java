package editor.state.states;

import editor.gui.view.tab.TabView;
import editor.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

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

        move(-dx, -dy, tabView);
        startPoint = endPoint;
    }

    @Override
    public void releasePerform(int x, int y, TabView tabView) {

    }

    public void move(double dx, double dy, TabView tabView) {
        JScrollBar hScrollBar = tabView.getHScrollBar();
        JScrollBar vScrollBar = tabView.getVScrollBar();
        double currentDx = tabView.getDx();
        double currentDy = tabView.getDy();

        if (isWithinBounds(currentDx + dx, hScrollBar)) tabView.setDx(currentDx + dx);
        if (isWithinBounds(currentDy + dy, vScrollBar)) tabView.setDy(currentDy + dy);

        updateBars(tabView, hScrollBar, vScrollBar);
    }

    private boolean isWithinBounds(double value, JScrollBar scrollBar) {
        int max = scrollBar.getMaximum();
        int visibleAmount = scrollBar.getVisibleAmount();
        return value >= 0 && value <= max - visibleAmount;
    }

    private void updateBars(TabView tabView, JScrollBar hScrollBar, JScrollBar vScrollBar) {
        updateScrollBar(tabView::getDx, hScrollBar::setValue);
        updateScrollBar(tabView::getDy, vScrollBar::setValue);
    }

    private void updateScrollBar(Supplier<Double> getValue, IntConsumer setValue) {
        setValue.accept(getValue.get().intValue());
    }

}

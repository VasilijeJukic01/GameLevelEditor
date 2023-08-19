package editor.state;

import javax.swing.*;

public interface State<T extends JPanel> {

    void clickPerform(int x, int y, T t);

    void dragPerform(int x, int y, T t);

    void releasePerform(int x, int y, T t);

}

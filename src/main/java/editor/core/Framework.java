package editor.core;

public class Framework {

    private static volatile Framework instance;

    protected Gui gui;

    private Framework() {}

    public static Framework getInstance(){
        if(instance == null) {
            synchronized (Framework.class) {
                if (instance == null) {
                    instance = new Framework();
                }
            }
        }
        return instance;
    }

    public void init(Gui gui) {
        this.gui = gui;
    }

    public void run(){
        this.gui.start();
    }

}

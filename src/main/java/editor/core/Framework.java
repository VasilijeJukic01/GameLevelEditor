package editor.core;

import editor.model.repository.LevelRepository;

public class Framework {

    private static volatile Framework instance;

    protected Gui gui;
    protected Repository repository;

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
        this.repository = new LevelRepository();
    }

    public void run(){
        this.gui.start();
    }

    // Getters & Setters
    public Repository getRepository() {
        return repository;
    }
}

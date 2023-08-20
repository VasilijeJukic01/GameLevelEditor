package editor.core;

import editor.model.loader.LevelLoader;
import editor.model.repository.LevelRepository;

public class Framework {

    private static volatile Framework instance;

    protected Gui gui;
    protected Repository repository;
    protected Loader levelLoader;
    protected Storage storage;

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
        this.levelLoader = new LevelLoader();
        this.storage = new Storage();
    }

    public void run(){
        this.gui.start();
    }

    // Getters & Setters
    public Repository getRepository() {
        return repository;
    }

    public Loader getLoader() {
        return levelLoader;
    }

    public Storage getStorage() {
        return storage;
    }
}

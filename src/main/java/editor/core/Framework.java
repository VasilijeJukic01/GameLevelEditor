package editor.core;

import editor.logger.CombinatorLogger;
import editor.logger.LogType;
import editor.logger.Logger;
import editor.model.loader.LevelLoader;
import editor.model.repository.LevelRepository;
import editor.model.repository.components.Project;
import editor.serializer.Serializer;
import editor.serializer.GsonSerializer;

public class Framework {

    private static volatile Framework instance;

    protected Gui gui;
    protected Repository repository;
    protected Loader levelLoader;
    protected Storage storage;
    protected Serializer<Project> serializer;
    protected Logger logger;

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
        this.logger = CombinatorLogger.createLogger();
        this.gui = gui;
        this.repository = new LevelRepository();
        this.levelLoader = new LevelLoader();
        this.storage = new Storage();
        this.serializer = new GsonSerializer();
    }

    public void run(){
        this.gui.start();
    }

    public void log(String message, LogType type) {
        this.logger.log(message, type);
    }

    // Getters & Setters
    public Gui getGui() {
        return gui;
    }

    public Repository getRepository() {
        return repository;
    }

    public Loader getLoader() {
        return levelLoader;
    }

    public Storage getStorage() {
        return storage;
    }

    public Serializer<Project> getSerializer() {
        return serializer;
    }

}

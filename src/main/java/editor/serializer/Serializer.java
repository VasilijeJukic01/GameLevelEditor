package editor.serializer;

import java.io.File;

public interface Serializer<T> {

    void loadProject(File file);

    void saveProject(T t);

}

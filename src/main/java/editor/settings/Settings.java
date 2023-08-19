package editor.settings;

public interface Settings {

    Object getParameter(String parameter);

    void addParameter(String parameter, Object value);

    void updateParameter(String parameter, Object newValue);

}

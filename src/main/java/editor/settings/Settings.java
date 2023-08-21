package editor.settings;

public interface Settings {

    Object getParameter(SettingsKey key);

    void addParameter(SettingsKey key, Object value);

    void updateParameter(SettingsKey key, Object newValue);

}

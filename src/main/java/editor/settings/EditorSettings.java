package editor.settings;

import editor.gui.view.tab.TabView;

import java.util.HashMap;
import java.util.Map;

public class EditorSettings implements Settings {

    private final TabView tabView;

    public EditorSettings(TabView tabView) {
        this.tabView = tabView;
    }

    private final Map<SettingsKey, Object> parameters = new HashMap<>();

    @Override
    public Object getParameter(SettingsKey key) {
        return this.parameters.get(key);
    }

    @Override
    public void addParameter(SettingsKey key, Object value) {
        this.parameters.put(key, value);
    }

    @Override
    public void updateParameter(SettingsKey key, Object newValue) {
        if (parameters.containsKey(key)) parameters.put(key, newValue);
        else {
            throw new IllegalArgumentException("Parameter '" + key + "' does not exist.");
        }
        tabView.getLevel().notify(this);
    }

}

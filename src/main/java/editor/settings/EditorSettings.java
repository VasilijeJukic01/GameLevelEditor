package editor.settings;

import editor.gui.view.tab.TabView;

import java.util.HashMap;
import java.util.Map;

public class EditorSettings implements Settings {

    private final TabView tabView;

    public EditorSettings(TabView tabView) {
        this.tabView = tabView;
    }

    private final Map<String, Object> parameters = new HashMap<>();

    @Override
    public Object getParameter(String parameter) {
        return this.parameters.get(parameter);
    }

    @Override
    public void addParameter(String parameter, Object value) {
        this.parameters.put(parameter, value);
    }

    @Override
    public void updateParameter(String parameter, Object newValue) {
        if (parameters.containsKey(parameter)) parameters.put(parameter, newValue);
        else {
            throw new IllegalArgumentException("Parameter '" + parameter + "' does not exist.");
        }
        tabView.getLevel().notify(this);
    }

}

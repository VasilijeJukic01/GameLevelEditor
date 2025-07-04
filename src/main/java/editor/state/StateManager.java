package editor.state;

import editor.gui.view.tab.TabView;
import editor.state.states.*;

public class StateManager {

    private State<TabView> currentState;

    private AddState addState;
    private DeleteState deleteState;
    private EditState editState;
    private MoveState moveState;
    private SelectState selectState;
    private PasteState pasteState;

    public StateManager() {
        init();
    }

    public void init() {
        this.addState = new AddState();
        this.deleteState = new DeleteState();
        this.editState = new EditState();
        this.moveState = new MoveState();
        this.selectState = new SelectState();
        this.pasteState = new PasteState();
        this.currentState = moveState;
    }

    public State<TabView> getCurrentState() {
        return currentState;
    }

    public void setAddState() {
        this.currentState = addState;
    }

    public void setDeleteState() {
        this.currentState = deleteState;
    }

    public void setEditState() {
        this.currentState = editState;
    }

    public void setMoveState() {
        this.currentState = moveState;
    }

    public void setSelectState() {
        this.currentState = selectState;
    }

    public void setPasteState() {
        this.currentState = pasteState;
    }

    public State<TabView> getSelectState() {
        return selectState;
    }

}

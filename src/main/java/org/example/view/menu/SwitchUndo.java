package org.example.view.menu;
import org.example.controller.state.Undo;

public class SwitchUndo implements AppCommand {
    private Undo undo;

    public SwitchUndo(Undo undo) {
        this.undo = undo;
    }

    @Override
    public void execute() {
        undo.executeUndo();
        undo.updateButtons();
    }

}

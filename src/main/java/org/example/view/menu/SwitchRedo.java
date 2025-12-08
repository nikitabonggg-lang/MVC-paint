package org.example.view.menu;
import org.example.controller.state.Undo;

public class SwitchRedo implements AppCommand {
    private Undo undo;

    public SwitchRedo(Undo undo) {
        this.undo = undo;
    }

    @Override
    public void execute() {
        undo.executeRedo();
        undo.updateButtons();
    }

}

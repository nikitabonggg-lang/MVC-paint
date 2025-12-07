package org.example.view.menu;
import org.example.controller.action.AppAction;
import org.example.model.factory.MenuState;

public class SwitchAction implements AppCommand {
    private MenuState menuState;

    private AppAction appAction;

    public SwitchAction(MenuState menuState, AppAction appAction) {
        this.menuState = menuState;
        this.appAction = appAction;
    }

    @Override
    public void execute() {
        menuState.setAction(appAction);

    }
}

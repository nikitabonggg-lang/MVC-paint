package org.example.view.menu;
import org.example.model.factory.MenuState;
import org.example.model.factory.ShapeType;

public class SwitchShape implements  AppCommand{

    private ShapeType shapeType;

    private MenuState state;

    public SwitchShape(ShapeType shapeType, MenuState state) {
        this.shapeType = shapeType;
        this.state = state;
    }

    @Override
    public void execute() {
        state.setShapeType(shapeType);

    }
}

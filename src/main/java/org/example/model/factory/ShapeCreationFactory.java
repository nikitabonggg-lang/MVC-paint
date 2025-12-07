package org.example.model.factory;

import lombok.Setter;
import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.geom.RectangularShape;

public class ShapeCreationFactory {

    @Setter
    private MenuState state;
    private static ShapeCreationFactory instance;

    public static ShapeCreationFactory getInstance() {
        if (instance == null) {
            instance = new ShapeCreationFactory();
        }
        return instance;
    }

    private ShapeCreationFactory() {

    }

    public void config(MenuState state) {
        this.state = state;
    }

    public MyShape createShape(){
        MyShape newShape = new MyShape();
        RectangularShape shape = state.getShapeType().createShape();

        FillBehavior fillBehavior = state.isFill() ? new Fill() : new NoFill();
        fillBehavior.setShape(shape);
        newShape.setFb(fillBehavior);
        fillBehavior.setColor(state.getColor());
        return newShape;
    }
}
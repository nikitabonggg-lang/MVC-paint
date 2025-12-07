package org.example.model.factory;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.AppAction;

import java.awt.*;

@Getter
@Setter
public class MenuState {
    private boolean fill;
    private Color color;
    private ShapeType shapeType;
    private AppAction action;

    public MenuState(){
        shapeType= ShapeType.RECTANGLE;
        color = Color.BLUE;
        fill = false;
    }
    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public void setAction(AppAction action) {
        this.action = action;
    }

}
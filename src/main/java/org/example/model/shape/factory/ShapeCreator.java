package org.example.model.shape.factory;

import org.example.controller.DrawingSettings;
import org.example.model.MyShape;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;

import java.awt.*;


public class ShapeCreator {
    private static ShapeCreator instance;
    private DrawingSettings drawingSettings;

    private ShapeCreator(){

    }

    public static ShapeCreator getInstance(){

        if (instance == null) {
            instance = new ShapeCreator();
        }
        return instance;
    }

    public void configure(DrawingSettings state){
        this.drawingSettings = state;
    }

    public MyShape createShape(){
        if (drawingSettings == null) {
            return MyShapeFactory.createShape(ShapeType.RECTANGLE, Color.GRAY, FillType.FILL);
        }
        return MyShapeFactory.createShape(
                drawingSettings.getShapeType(),
                drawingSettings.getColor(),
                drawingSettings.getFillType()
        );
    }
}

package org.example.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private MyShape currentShape;
    private List<MyShape> shapeList = new ArrayList<>();

    public void  createCurrentShape(MyShape shape){
        currentShape = shape;
        shapeList.add(shape);
    }
    public void addCurrentShape(MyShape sampleShape){
        shapeList.add(sampleShape);
    }
    public MyShape getLastShape() {
        int size = shapeList.size();
        return shapeList.isEmpty() ? null : shapeList.get(size - 1);
    }
    public void  removeLastShape() {
        if(shapeList == null) {
            return;
        } else {
            int size = shapeList.size();
            shapeList.remove(size - 1);
        }

    }

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
    }

    public void changeShape(Point2D x, Point2D y) {
        currentShape.setFrame(x, y);
    }

    public void draw(Graphics2D g) {
        for (MyShape shape : shapeList){
            shape.draw(g);
        }
    }
    public void update()
    {
        this.setChanged();
        this.notifyObservers();
    }

    public List<MyShape> getShapeList() {
        return shapeList;
    }
}

package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.model.shape.ShapeType;
import org.example.model.shape.fill.FillType;
import org.example.model.shape.factory.MyShapeFactory;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private ActionDraw actionDraw;
    private DrawingSettings drawingSettings;



    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) {
                instance = new Controller();
            }
            return instance;
        }
    }

    private Controller() {
        model = new Model();
        drawingSettings = new DrawingSettings();

        MyShape sampleShape = createSampleShape();
        actionDraw = new ActionDraw(model, sampleShape);

        panel = new MyPanel(this);
        model.addObserver(panel);
        frame = new MyFrame();
        frame.setPanel(panel);


        // Устанавливаем связь между контроллерами после полной инициализации
        MenuController menuController = MenuController.getInstance();
        menuController.setMainController(this);
        frame.setMenu();

        frame.revalidate();
    }

    private MyShape createSampleShape() {
        return MyShapeFactory.createShape(
                drawingSettings.getShapeType(),
                drawingSettings.getColor(),
                drawingSettings.getFillType()
        );
    }

    public void setShapeType(ShapeType type) {
        this.drawingSettings.setShapeType(type);
        updateActionDraw();
    }

    public void setCurrentColor(Color color) {
        this.drawingSettings.setColor(color);
        updateActionDraw();
    }

    public void setFillType(FillType fillType) {
        this.drawingSettings.setFill(fillType == FillType.FILL);
        updateActionDraw();
    }

    public Color getCurrentColor() {
        return drawingSettings.getColor();
    }

    private void updateActionDraw() {
        MyShape sampleShape = createSampleShape();
        actionDraw = new ActionDraw(model, sampleShape);
    }

    public void startDrawing(Point2D p) {
        actionDraw.startDrawing(p);
    }

    public void updateDrawing(Point2D p) {
        actionDraw.updateDrawing(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
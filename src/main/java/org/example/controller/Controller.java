package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.state.UndoMachine;
import org.example.model.factory.MenuState;
import org.example.controller.action.AppAction;
import org.example.model.factory.ShapeCreationFactory;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.MenuCreator;

import java.awt.*;
import java.awt.geom.Point2D;


public class Controller {
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private MenuState state;
    private static Controller instansce;
    private UndoMachine machine;

    public static synchronized Controller getInstance(){
        if(instansce == null){
            instansce = new Controller();
        }
        return instansce;
    }
    private Controller() {
        state = new MenuState();
        ShapeCreationFactory shapeCreationFactory = ShapeCreationFactory.getInstance();
        shapeCreationFactory.config(state);

        model = new Model();
        MyShape sampleShape = shapeCreationFactory.createShape();
        sampleShape.setFb(new NoFill());
        AppAction actionDraw = new ActionDraw(model, sampleShape);
        panel = new MyPanel(this);
        model.setMyShape(sampleShape);

        state.setAction(actionDraw);

        model.addObserver(panel);



        frame = new MyFrame();
        frame.setPanel(panel);
        machine = new UndoMachine();

        MenuCreator menuCreator = MenuCreator.getInstance();
        menuCreator.setState(state);
        menuCreator.setModel(model);
        menuCreator.setUndoMachine(machine);
        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.add(menuCreator.createToolBar(), BorderLayout.WEST);
        frame.revalidate();


    }

    public void mousePressedAction(Point2D p){
        AppAction action = state.getAction();
        action.mousePressed((Point) p);
        machine.add(action.cloneAction());
        machine.updateButtons();
    }
    public void mouseDraggedAction(Point2D p){
        AppAction action = state.getAction();
        action.mouseDragged((Point) p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}

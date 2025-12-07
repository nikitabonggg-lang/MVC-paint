package org.example.view.menu;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.controller.state.UndoMachine;
import org.example.model.factory.MenuState;
import org.example.model.factory.ShapeType;
import org.example.model.Model;
import org.example.model.MyShape;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

@Getter
@Setter
public class MenuCreator {
    private static MenuCreator instance;
    private JMenuBar menuBar;
    private ActionDraw actionDraw;
    private AppAction action;
    private Model model;
    private MyShape shape;
    private MenuState state;
    private UndoMachine undoMachine;
    private JRadioButtonMenuItem rgbButton;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private MenuCreator(){

        menuBar = createMenuBar();
    }

    public static MenuCreator getInstance(){
        if (instance == null){
            instance = new MenuCreator();
        }
        return instance;
    }
    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu shapeMenu = createShapeMenu();
        menuBar.add(shapeMenu);

        JMenu colorMenu = createColorMenu();
        menuBar.add(colorMenu);

        JMenu actionMenu = createActionMenu();
        menuBar.add(actionMenu);

        JMenu fillMenu = createFillMenu();
        menuBar.add(fillMenu);

        return menuBar;
    }
    private JMenu createShapeMenu() {

        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> {
            state.setShapeType(ShapeType.RECTANGLE);
        });


        shapeMenu.add(square);
        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> {
            state.setShapeType(ShapeType.ELLIPSE);
        });
        ;
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }
    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem redItem = new JRadioButtonMenuItem("Красный");
        redItem.addActionListener(new CommandActionListener(new SwitchColor(rgbButton, true, state, Color.RED)));
        colorMenu.add(redItem);
        group.add(redItem);

        JRadioButtonMenuItem greenItem = new JRadioButtonMenuItem("Зеленый");
        greenItem.addActionListener(new CommandActionListener(new SwitchColor(rgbButton, true, state, Color.GREEN)));
        colorMenu.add(greenItem);
        group.add(greenItem);

        JRadioButtonMenuItem blueItem = new JRadioButtonMenuItem("Синий");
        blueItem.addActionListener(new CommandActionListener(new SwitchColor(rgbButton, true, state, Color.BLUE)));
        colorMenu.add(blueItem);
        group.add(blueItem);

        rgbButton = new JRadioButtonMenuItem("Другой");
        rgbButton.addActionListener(new CommandActionListener(new SwitchColor(rgbButton, false, state, null)));
        colorMenu.add(rgbButton);
        group.add(rgbButton);

        return colorMenu;
    }
    private JMenu createActionMenu() {
        JMenu shapeMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Рисовать");
        draw.addActionListener(e -> state.setAction(new ActionDraw(model, shape)));
        shapeMenu.add(draw);
        group.add(draw);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Двигать");
        ellipse.addActionListener(e -> state.setAction(new ActionMove(model)));
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }
    private JMenu createFillMenu(){
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem yes = new JRadioButtonMenuItem("Есть");
        yes.addActionListener(e -> state.setFill(true));
        fillMenu.add(yes);
        group.add(yes);
        JRadioButtonMenuItem no = new JRadioButtonMenuItem("Нет");
        no.addActionListener(e -> state.setFill(false));
        fillMenu.add(no);
        group.add(no);
        return fillMenu;
    }
    public JToolBar createToolBar(){
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar(JToolBar.VERTICAL);

        subMenuItems.forEach(jToolBar::add);

        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems(){
        ArrayList<Action> menuItems = new ArrayList<>();
        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(rgbButton, false,  state, null);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));


        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillCommand = new SwitchFill(false, state);
        menuItems.add(new CommandActionListener("Пусто",noFillIco,noFillCommand));


        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(true, state);
        menuItems.add(new CommandActionListener("Заливка", fillIco, fillCommand));

        URL rectangularUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectangularIco = rectangularUrl == null ? null : new ImageIcon(rectangularUrl);
        AppCommand rectangularCommand = new SwitchShape(ShapeType.RECTANGLE, state);
        menuItems.add(new CommandActionListener("Фигура", rectangularIco, rectangularCommand));

        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseCommand = new SwitchShape(ShapeType.ELLIPSE, state);
        menuItems.add(new CommandActionListener("Фигура", ellipseIco, ellipseCommand));

        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawCommand = new SwitchAction(state, new ActionDraw(model, shape));
        menuItems.add(new CommandActionListener("Рисовать", drawIco, drawCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(state, new ActionMove(model));
        menuItems.add(new CommandActionListener("Двигать", moveIco, moveCommand));

        URL redoUrl = getClass().getClassLoader().getResource("ico/redo_16x16.png");
        ImageIcon redoIco = redoUrl == null ? null : new ImageIcon(redoUrl);
        AppCommand redoC = new SwitchRedo(undoMachine);
        CommandActionListener redoListeenr = new CommandActionListener("Вперед-назад", redoIco, redoC);
        menuItems.add(redoListeenr);

        URL undoUrl = getClass().getClassLoader().getResource("ico/undo_16x16.png");
        ImageIcon undoIco = undoUrl == null ? null : new ImageIcon(undoUrl);
        AppCommand undoC = new SwitchUndo(undoMachine);
        CommandActionListener undoListener = new CommandActionListener("Вперед-назад", undoIco, undoC);
        menuItems.add(undoListener);
        undoMachine.setUndo(undoListener);
        undoListener.setEnabled(false);
        undoMachine.setRedo(redoListeenr);
        redoListeenr.setEnabled(false);


        return menuItems;

    }

    public void setActionDraw(ActionDraw actionDraw) {
        this.actionDraw = actionDraw;
    }
    public void setState(MenuState state) {
        this.state = state;
    }
    public void setUndoMachine(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }


}
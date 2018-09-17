package commanderKeen.states;

import commanderKeen.main.GamePanel;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.VolatileImage;

public class GameStateManager {
    public static final int LOAD_STATE = 0;
    public static final int MENU_STATE = 1;
    public static final int MAP_STATE = 2;
    public static final int LEVEL_STATE = 3;
    public JPanel panel;

    public State getState(){
        return states[state];
    }

    public void setState(int state) {
        this.state = state;
        switch (state){
            case 0:
                los = new LoadState(this);
                states[0] = los;
                break;
            case 1:
                mes = new MenuState(this);
                states[1] = mes;
                break;
            case 2:
                mas = new MapState(this);
                states[2] = mas;
                break;
            case 3:
                les = new LevelState(this);
                states[3] = les;
                break;
        }
    }

    public int state;
    private MapState mas;
    private MenuState mes;
    private LevelState les;
    private LoadState los;
    private State[] states;

    public GameStateManager(int state, GamePanel panel){
        this.state = state;
        this.panel = panel;
        states = new State[4];
        los = new LoadState(this);
        states[0] = los;
        states[1] = mes;
        states[2] = mas;
        states[3] = les;
    }

    public void update(){
        try {
            states[state].update();
        } catch (Exception e) {}
    }
    public void render(Graphics2D g2d){
        try {
            states[state].renderState(g2d);
        } catch (Exception e) {}
    }
    public void keyPressed(KeyEvent e , int k){
        states[state].keyPressed(e, k);
    }
    public void keyReleased(KeyEvent e ,int k){
        states[state].keyReleased(e, k);
    }
    public void mousePressed(MouseEvent e){
        states[state].mousePressed(e);
    }
    public void mouseReleased(MouseEvent e){
        states[state].mouseReleased(e);
    }
    public void windowResized(){
        states[state].windowResized();
    }
}

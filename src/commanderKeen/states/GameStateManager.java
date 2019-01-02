package commanderKeen.states;

import commanderKeen.levels.Level;
import commanderKeen.main.GameFx;
import javafx.scene.input.ScrollEvent;

import java.awt.Graphics2D;
import javafx.scene.input.*;

public class GameStateManager {
    public static final int LOAD_STATE = 0;
    public static final int MENU_STATE = 1;
    public static final int MAP_STATE = 2;
    public static final int EDITOR_STATE = 3;
    public GameFx game;

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
            case 4:
                es = new EditorState(this);
                states[4] = es;
                break;
        }
    }

    private int state;
    private MapState mas;
    private MenuState mes;
    private LoadState los;
    private EditorState es;
    private State[] states;

    public GameStateManager(int state, GameFx game){
        this.state = state;
        this.game = game;
        states = new State[5];
        switch (state){
            case EDITOR_STATE:
                es = new EditorState(this);
                break;
            case MENU_STATE:
                mes = new MenuState(this);
                break;
        }
        states[0] = los;
        states[1] = mes;
        states[2] = mas;
        states[3] = es;
    }

    public void update(){
        try {
            states[state].update();
        } catch (Exception ignored) {}
    }
    public void render(Graphics2D g2d){
        try {
            states[state].renderState(g2d);
        } catch (Exception ignored) {}
    }

    public void keyPressed(KeyEvent e){
        states[state].keyPressed(e);
    }
    public void keyReleased(KeyEvent e){
        states[state].keyReleased(e);
    }
    public void mousePressed(MouseEvent e){
        states[state].mousePressed(e);
    }
    public void mouseReleased(MouseEvent e){
        states[state].mouseReleased(e);
    }
    public void mouseWheelMoved(ScrollEvent e) {
        states[state].mouseWheelMoved(e);
    }
    public void mouseMoved(MouseEvent e) {
        states[state].mouseMoved(e);
    }
    public void mouseDragged(MouseEvent e) {
        states[state].mouseDragged(e);
    }
}

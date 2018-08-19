package commanderKeen.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameStateManager {
    public static final int PLAY_STATE = 2;
    public static final int MENU_STATE = 0;
    public static final int LOAD_STATE = 1;

    public void setState(int state) {
        this.state = state;
        switch (state){
            case 0:
                acs = new MenuState(this);
                states[0] = acs;
                break;
            case 1:
                ls = new LevelState(this);
                states[1] = ls;
                break;
            case 2:
                ps = new MapState(this);
                states[2] = ps;
                break;
        }
    }

    public int state;
    private MapState ps;
    private MenuState acs = new MenuState(this);
    private LevelState ls;
    private State[] states;

    public GameStateManager(int state){
        this.state = state;
        states = new State[4];
        states[0] = acs;
        states[1] = ls;
        states[2] = ps;
    }

    public void update(){
        try {
            states[state].update();
        } catch (Exception e) {}
    }
    public void render(Graphics2D g2d){
        try {
            states[state].render(g2d);
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
}

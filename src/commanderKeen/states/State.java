package commanderKeen.states;

import commanderKeen.main.Game;
import commanderKeen.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.VolatileImage;

public abstract class State {

    protected GameStateManager gsm;

    public VolatileImage image;
    private int scale;

    public State(GameStateManager gsm, int scale) {
        this.gsm = gsm;
        this.scale = scale;
        image = gsm.panel.createVolatileImage(GamePanel.width /scale, GamePanel.height /scale);
    }

    protected void setScale(int scale){
        this.scale = scale;
    }

    public abstract void update();
    protected abstract void render(Graphics2D g);
    public void renderState(Graphics2D g){
        Graphics2D g2d = image.createGraphics();
        render(g2d);
        g.drawImage(image, 0, 0, GamePanel.width, GamePanel.height, null);
    };
    public abstract void keyPressed(KeyEvent e, int k);
    public abstract void keyReleased(KeyEvent e, int k);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);

    public void windowResized() {image = gsm.panel.createVolatileImage(GamePanel.width /scale, GamePanel.height /scale);}
}

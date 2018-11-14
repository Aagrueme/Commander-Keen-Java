package commanderKeen.states;

import commanderKeen.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;

public abstract class State {

    GameStateManager gsm;

    protected double scaleX;
    protected double scaleY;

    State(GameStateManager gsm, double scaleX, double scaleY) {
        this.gsm = gsm;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    void setScale(double scaleX, double scaleY){
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public abstract void update();
    protected abstract void render(Graphics2D g);
    void renderState(Graphics2D g){
        VolatileImage img = gsm.panel.createVolatileImage((int)Game.ORIGINAL_WIDTH, (int)Game.ORIGINAL_HEIGHT);
        Graphics2D g2d = img.createGraphics();
        Color color = g2d.getColor();
        g2d.setColor(Game.BACKGROUND_COLOR);
        g2d.fillRect(0, 0, (int)Game.ORIGINAL_WIDTH, (int)Game.ORIGINAL_HEIGHT);
        g2d.setColor(color);
        render(g2d);
        g.setTransform(AffineTransform.getScaleInstance(scaleX, scaleY));
        g.drawImage(img, 0, 0, null);
    }
    public abstract void keyPressed(KeyEvent e, int k);
    public abstract void keyReleased(KeyEvent e, int k);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
}

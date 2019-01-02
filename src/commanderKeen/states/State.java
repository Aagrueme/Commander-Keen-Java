package commanderKeen.states;

import commanderKeen.main.GameFx;
import javafx.scene.input.ScrollEvent;

import java.awt.*;
import javafx.scene.input.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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
        BufferedImage img = new BufferedImage((int)GameFx.ORIGINAL_WIDTH, (int)GameFx.ORIGINAL_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Color color = g2d.getColor();
        g2d.setColor(GameFx.BACKGROUND_COLOR);
        g2d.fillRect(0, 0, (int)GameFx.ORIGINAL_WIDTH, (int)GameFx.ORIGINAL_HEIGHT);
        g2d.setColor(color);
        render(g2d);
        g.setTransform(AffineTransform.getScaleInstance(scaleX, scaleY));
        g.drawImage(img, 0, 0, null);
    }
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseWheelMoved(ScrollEvent e);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
}

package commanderKeen.util;

import commanderKeen.entitiy.mob.Keen;
import commanderKeen.main.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Camera {
    private Keen player;
    private double camX;
    private double camY;

    public Camera(Keen player){
        this.player = player;
        this.player.setCamera(this);
        this.camX = -player.getX() + (Game.ORIGINAL_WIDTH / 2 - 8);
        this.camY = -player.getY() + (Game.ORIGINAL_HEIGHT / 2 - 8);
    }

    public void update(){
        camX -= player.camToX;
        camY -= player.camToY;
    }

    public void setCamX(double camX) {
        this.camX = camX;
    }
    public void setCamY(double camY) {
        this.camY = camY;
    }

    public Graphics2D getGraphics(Graphics2D g2d) {
        g2d.setTransform(AffineTransform.getTranslateInstance(camX, camY));
        return g2d;
    }

    public void addCamX(int x) {
        this.camX += x;
    }

    public void addCamY(int y) {
        this.camY += y;
    }
}

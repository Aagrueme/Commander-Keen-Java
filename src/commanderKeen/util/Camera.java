package commanderKeen.util;

import commanderKeen.entitiy.mob.Keen;

public class Camera {
    private Keen player;
    private double camX;
    private double camY;

    public Camera(Keen player){
        this.player = player;
    }

    public void update(){
        camX += player.camToX;
        camY += player.camToY;
        player.camToX = 0;
        player.camToY = 0;
    }

    public double getCamX() {
        return camX;
    }

    public double getCamY() {
        return camY;
    }

    public void setCamX(double camX) {
        this.camX = camX;
    }

    public void setCamY(double camY) {
        this.camY = camY;
    }
}

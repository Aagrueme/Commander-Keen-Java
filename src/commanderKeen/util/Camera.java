package commanderKeen.util;

import commanderKeen.entitiy.mob.Player;
import commanderKeen.states.State;

public class Camera {
    private Player player;
    private double camX;
    private double camY;

    public Camera(Player player){
        this.player = player;
    }

    public void update(){
        camX += player.camToX;
        camY += player.camToY;
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

package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;

import commanderKeen.main.GameFx;
import commanderKeen.states.State;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class MapKeen extends Keen {

    protected final float GRAVITY = 0.2F;
    protected final float MAX_FALLING_SPEED = 4.5F;
    protected float jumpStart = -4.0F;

    private double jumpHeight = 4.5f;
    private static Spritesheet animationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_map.png"), 3, 2, 16, 16, 16, 0);

    {
        idle = DOWN;
        speed = 1;
    }

    public MapKeen(Level level, State state) {
        super(level, 16 * 4, 16 * 33, 13, 16, new Animation(animationSprite, 3, 3, 71), state);
    }

    public void keyPressed(KeyEvent e){
        switch (e.getCode()){
            case W: setUp(true);break;
            case S: setDown(true);break;
            case A: setLeft(true);break;
            case D: setRight(true);break;
            case CONTROL: interact();
        }
    }

    private void interact() {
        for(int yy=0;yy< level.level[0].length;yy++) {
            for (int xx=0;xx<level.level.length;xx++) {
                if(getBoundsBottom().intersects(level.level[xx][yy].getBlock().getBounds())){
                    level.level[xx][yy].getBlock().interact(this, state);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        switch (e.getCode()){
            case W: setUp(false);break;
            case S: setDown(false);break;
            case A: setLeft(false);break;
            case D: setRight(false);break;
        }
    }

    public boolean getRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public boolean getLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public boolean getUp() {
        return up;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public boolean getDown() {
        return down;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
}

package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;
import commanderKeen.util.Camera;
import commanderKeen.util.IHasRenderer;
import commanderKeen.util.IHasUpdater;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public abstract class Keen implements IHasUpdater, IHasRenderer {
    protected static final int RIGHT = 0;
    protected static final int DOWN = 1;
    protected static final int LEFT = 2;
    protected static final int UP = 3;
    public double camToY;
    public double camToX;

    public boolean right = false;
    public boolean left = false;
    public boolean jump = false;
    public boolean falling = false;
    protected final float GRAVITY = 0.2F;
    protected final float MAX_FALLING_SPEED = 4.5F;
    protected float jumpStart = -4.0F;

    protected double x;
    protected double y;
    protected Animation animation;
    protected Spritesheet idleSprite;

    protected double dx;
    protected double dy;

    private Camera camera;
    protected Level level;
    protected float speed = 0.75f;
    protected int idle = LEFT;

    protected BufferedImage texture;

    public Keen(Level level, double x, double y, Animation animation, Spritesheet idleSprite){
        this.level = level;
        this.x = x;
        this.y = y;
        this.animation = animation;
        this.idleSprite = idleSprite;
        this.animation.startAnimation();
        this.texture = animation.getImage();
    }

    @Override
    public void render(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        g2d.setTransform(AffineTransform.getTranslateInstance( g2d.getTransform().getTranslateX() + x, g2d.getTransform().getTranslateY() + y));
        g2d.drawImage(texture, 0, 0, null);
        g2d.setTransform(transform);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    protected void move() {
        x += dx;
        y += dy;
        camToX += dx;
        camToY += dy;
        dx = 0;
        dy = 0;
    }

    protected void calculateMovement() {
        if(left) dx = -speed;
        if(right) dx = speed;

        if(falling && !jump) {
            dy += GRAVITY;
            if(dy > MAX_FALLING_SPEED) dy = MAX_FALLING_SPEED;
        }

        if(jump && !falling) {
            dy = jumpStart;
            jump = false;
            falling = true;
        }
    }
    
    protected void calculateAnimation(){
        animation.update();
        if(dx > 0 && animation.getCurrentState() != LEFT) {
            animation.setState(LEFT);
            idle = LEFT;
            texture = animation.getImage();
        } else if(dx < 0 && animation.getCurrentState() != RIGHT) {
            animation.setState(RIGHT);
            idle = RIGHT;
            texture = animation.getImage();
        }else if(dx == 0) {
            texture = idleSprite.getImage(idle, 0);
            return;
        }
        texture = animation.getImage();
    }

    @Override
    public void update() {
        calculateMovement();
        calculateAnimation();
        collision();
        move();
    }

    protected void collision() {
        boolean accepted = false;
        if(dx > 0) {
            for (int i = 0;!accepted; i++) {
                if (level.level[(int) (x + dx) / 16][(int) (y) / 16].getBlock().testCollision()) {
                    accepted = true;
                }else{
                    dx -= i;
                }
            }
        }else if(dx < 0) {
            for (int i = 0; !accepted; i++) {
                if (level.level[(int) (x + dx) / 16][(int) (y) / 16].getBlock().testCollision()) {
                    accepted = true;
                }else{
                    dx += i;
                }
            }
        }
        accepted = false;
        if(dy > 0) {
            for (int i = 0; !accepted; i++) {
                if (level.level[(int)( x) / 16][(int) (y + dy) / 16].getBlock().testCollision()) {
                    accepted = true;
                }else{
                    dy -= i;
                }
            }
        }else if(dy < 0) {
            for (int i = 0; !accepted; i++) {
                if (level.level[(int)( x) / 16][(int) (y + dy) / 16].getBlock().testCollision()) {
                    accepted = true;
                }else{
                    dy += i;
                }
            }
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}

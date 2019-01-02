package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;
import commanderKeen.main.GameFx;
import commanderKeen.util.IHasRenderer;
import commanderKeen.util.IHasUpdater;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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

    protected Level level;
    protected float speed = 2;
    protected int idle = DOWN;

    protected int width;
    protected int height;

    private Rectangle boundsBottom;
    private Rectangle boundsTop;
    private Rectangle boundsRight;
    private Rectangle boundsLeft;

    protected BufferedImage texture;
    private double jumpHeight = 4.5f;

    public Keen(Level level, double x, double y, int width, int height, Animation animation, Spritesheet idleSprite){
        this.level = level;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.animation = animation;
        this.idleSprite = idleSprite;
        this.animation.startAnimation();
        this.texture = animation.getImage();


        int middleBoundsWidth = (int) Math.floor(width / 2.0);

        this.boundsLeft = new Rectangle(0, (int) Math.floor(Math.floor(height /2.0) /2.0), (int) Math.ceil((width - middleBoundsWidth) / 2.0), (int) Math.ceil(height /2.0));

        this.boundsTop = new Rectangle(boundsLeft.width, 0, middleBoundsWidth, (int) Math.floor(height/2.0));

        this.boundsBottom = new Rectangle(boundsTop.x, boundsTop.height, middleBoundsWidth, (int) Math.ceil(height /2.0));

        this.boundsRight = new Rectangle(boundsLeft.width + middleBoundsWidth, boundsLeft.y, (int) Math.ceil((width - middleBoundsWidth) / 2.0), (int) Math.floor(height/2.0));
    }

    @Override
    public void render(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        g2d.setTransform(AffineTransform.getTranslateInstance( g2d.getTransform().getTranslateX() + x, g2d.getTransform().getTranslateY() + y));
        g2d.drawImage(texture, 0, 0, null);
        g2d.setTransform(transform);
        if (GameFx.debug) {
            drawHitBox(g2d);
        }

    }

    private void drawHitBox(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.draw(getBoundsBottom());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
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
    protected Rectangle getBoundsBottom() {
        return new Rectangle((int)x + boundsBottom.x, (int)(y + boundsBottom.y), boundsBottom.width, boundsBottom.height);
    }
    protected Rectangle getBoundsTop() {
        return new Rectangle((int)x + boundsTop.x, (int)y, boundsTop.width, boundsTop.height);
    }
    protected Rectangle getBoundsRight() {
        return new Rectangle((int)x + boundsRight.x, (int)(y+boundsRight.y), boundsRight.width, boundsRight.height);
    }
    protected Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + boundsLeft.y, boundsLeft.width, boundsLeft.height);
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
        for(int yy=0;yy< level.level[0].length;yy++) {
            for (int xx=0;xx<level.level.length;xx++) {
                if(getBoundsBottom().intersects(level.level[xx][yy].getBlock().getBounds())) {
                    if(level.level[xx][yy].getBlock().isSolid()) {
                        if(dy > 0) {
                            dy = 0;
                        }
                    }
                }
                if(getBoundsTop().intersects(level.level[xx][yy].getBlock().getBounds())) {
                    if(level.level[xx][yy].getBlock().isSolid()){
                        if(dy < 0) {
                            dy = 0;
                        }
                    }
                }
                if(getBoundsRight().intersects(level.level[xx][yy].getBlock().getBounds())) {
                    if(level.level[xx][yy].getBlock().isSolid()){
                        if(dx > 0) {
                            dx = 0;
                        }
                    }
                }
                if(getBoundsLeft().intersects(level.level[xx][yy].getBlock().getBounds())) {
                    if(level.level[xx][yy].getBlock().isSolid()){
                        if(dx < 0) {
                            dx = 0;
                        }
                    }
                }
            }

        }
    }
}

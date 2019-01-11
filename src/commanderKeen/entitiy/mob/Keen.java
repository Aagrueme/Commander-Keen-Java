package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;
import commanderKeen.main.GameFx;
import commanderKeen.states.State;
import commanderKeen.util.IHasRenderer;
import commanderKeen.util.IHasUpdater;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Keen implements IHasUpdater, IHasRenderer {
    protected static int RIGHT = 0;
    protected static int DOWN = 1;
    protected static int LEFT = 2;
    protected static int UP = 3;

    public boolean right = false;
    public boolean left = false;
    public boolean up = false;
    public boolean down = false;

    protected double dx;
    protected double dy;

    protected Level level;
    protected float speed = 2;

    protected double x;
    protected double y;

    protected int width;
    protected int height;

    private Rectangle boundsBottom;
    private Rectangle boundsTop;
    private Rectangle boundsRight;
    private Rectangle boundsLeft;

    public double camToY;
    public double camToX;

    protected Animation animation;

    protected State state;

    private static Spritesheet idleSprite;

    protected int idle = DOWN;

    protected BufferedImage texture;
    protected boolean ground;

    public Keen(Level level, double x, double y, int width, int height, Animation animation, Spritesheet idleSprite, State state) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.animation = animation;
        this.animation.startAnimation();
        this.texture = animation.getImage();
        this.idleSprite = idleSprite;
        this.state = state;


        int middleBoundsWidth = (int) Math.floor(width / 2.0);

        this.boundsLeft = new Rectangle(0, (int) Math.floor(Math.floor(height /2.0) /2.0), (int) Math.ceil((width - middleBoundsWidth) / 2.0), (int) Math.ceil(height /2.0));

        this.boundsTop = new Rectangle(boundsLeft.width, 0, middleBoundsWidth, (int) Math.floor(height/2.0));

        this.boundsBottom = new Rectangle(boundsTop.x, boundsTop.height, middleBoundsWidth, (int) Math.ceil(height /2.0));

        this.boundsRight = new Rectangle(boundsLeft.width + middleBoundsWidth, boundsLeft.y, boundsLeft.width, boundsLeft.height);
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

    @Override
    public void update() {
        calculateMovement();

        //TODO: "calculateAnimation();" and "collision();" can be switched to enable or disable animation while a collision occurs. -currently disabled
        collision();
        calculateAnimation();

        move();
    }

    protected void calculateMovement() {
        if(left) dx = -speed;
        if(right) dx = speed;
        if(up) dy = -speed;
        if(down) dy = speed;
    }

    public void keyPressed(KeyEvent e){
        switch (e.getCode()){
            case A: setLeft(true);break;
            case D: setRight(true);break;
        }
    }

    public void keyReleased(KeyEvent e){
        switch (e.getCode()){
            case A: setLeft(false);break;
            case D: setRight(false);break;
        }
    }

    protected void collision() {
        for(int yy=0;yy< level.level[0].length;yy++) {
            for (int xx=0;xx<level.level.length;xx++) {
                if(level.level[xx][yy].getBlock().collision(getBoundsBottom())) {
                    if(dy > 0) {
                        dy = 0;
                    }
                }
                if(level.level[xx][yy].getBlock().collision(getBoundsTop())) {
                    if(dy < 0) {
                        dy = 0;
                    }
                }
                if(level.level[xx][yy].getBlock().collision(getBoundsRight())) {
                    if(dx > 0) {
                        dx = 0;
                    }
                }
                if(level.level[xx][yy].getBlock().collision(getBoundsLeft())) {
                    if(dx < 0) {
                        dx = 0;
                    }
                }
            }
        }
    }

    protected void calculateAnimation() {
        animation.update();
        if(dx > 0) {
            if(animation.getCurrentState() != RIGHT) {
                animation.setState(RIGHT);
                idle = RIGHT;
            }
            texture = animation.getImage();
        } else if(dx < 0) {
            if(animation.getCurrentState() != LEFT) {
                animation.setState(LEFT);
                idle = LEFT;
            }
            texture = animation.getImage();
        }else if(dy > 0) {
            if(animation.getCurrentState() != DOWN) {
                animation.setState(DOWN);
                idle = DOWN;
            }
            texture = animation.getImage();
        } else if(dy < 0) {
            if(animation.getCurrentState() != UP) {
                animation.setState(UP);
                idle = UP;
            }
            texture = animation.getImage();
        }else if(dx == 0 && dy == 0) {
            texture = idleSprite.getImage(idle, 0);
        }
    }

    protected void move() {
        x += dx;
        y += dy;
        camToX += dx;
        camToY += dy;
        dx = 0;
        dy = 0;
    }

    private void drawHitBox(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.draw(getBoundsBottom());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
    }

    protected Rectangle getBoundsBottom() {
        return new Rectangle((int)x + boundsBottom.x, (int)y + boundsBottom.y, boundsBottom.width, boundsBottom.height);
    }
    protected Rectangle getBoundsTop() {
        return new Rectangle((int)x + boundsTop.x, (int)y, boundsTop.width, boundsTop.height);
    }
    protected Rectangle getBoundsRight() {
        return new Rectangle((int)x + boundsRight.x, (int)y+boundsRight.y, boundsRight.width, boundsRight.height);
    }
    protected Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + boundsLeft.y, boundsLeft.width, boundsLeft.height);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
}

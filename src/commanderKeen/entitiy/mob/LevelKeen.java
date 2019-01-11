package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;
import commanderKeen.states.State;
import javafx.scene.input.KeyEvent;
import static javafx.scene.input.KeyCode.*;

import java.awt.image.BufferedImage;

public class LevelKeen extends Keen {

    protected final float GRAVITY = 0.2f;
    protected final float MAX_FALLING_SPEED = 4.5f;
    protected float jumpStart = -4.5f;
    public boolean falling = true;

    private static final double MAX_JUMP_HEIGHT = -160.0;

    private boolean jump = false;

    private static Spritesheet animationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_level.png"), 1, 2, 16, 24, 16, 0);
    private static Spritesheet idleSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_level.png"), 1, 0, 16, 24);

    public LevelKeen(Level level, double x, double y, State state) {
        super(level, x, y, 16, 24,  new Animation(animationSprite, 1, 3, 71), idleSprite, state);
        LEFT = 1;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if(e.getCode() == CONTROL){
        }
    }

    private void chargeJump() {
        System.out.println(jump);
        System.out.println(jumpStart);
        new Thread(()-> {
            while (!jump && !falling){
                if(jumpStart >= MAX_JUMP_HEIGHT) {
                    jumpStart -= 0.5;
                    System.out.println("chargeJump");
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hi");
            }

            jumpStart = 0;
        }).start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        if(e.getCode() == CONTROL){
            jump = true;
        }
    }

    @Override
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

    @Override
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
        }else if(dx == 0 && dy == 0) {
            texture = idleSprite.getImage(idle, 0);
        }
    }

    @Override
    protected void move() {
        x += dx;
        y += dy;
        camToX += dx;
        camToY += dy;
        dx = 0;
    }
}

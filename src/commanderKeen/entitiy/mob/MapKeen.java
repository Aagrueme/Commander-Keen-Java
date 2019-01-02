package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;

import java.awt.image.BufferedImage;

public class MapKeen extends Keen {
    private static Spritesheet idleSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_map.png"), 3, 0, 16, 16);
    private static Spritesheet animationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_map.png"), 3, 2, 16, 16, 16, 0);

    private boolean up = false;
    private boolean down = false;

    {
        idle = DOWN;
        width = 9;
        speed = 1;
    }

    public MapKeen(Level level) {
        super(level, 16 * 4, 16 * 33, 8, 16, new Animation(animationSprite, 3, 3, 71), idleSprite);
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

    @Override
    protected void move() {
        x += dx;
        y += dy;
        camToX += dx;
        camToY += dy;
        dx = 0;
        dy = 0;
    }

    @Override
    protected void calculateMovement() {
        if(left) dx = -speed;
        if(right) dx = speed;
        if(up) dy = -speed;
        if(down) dy = speed;
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

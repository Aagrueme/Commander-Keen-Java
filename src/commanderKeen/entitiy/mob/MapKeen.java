package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;

import java.awt.image.BufferedImage;

public class MapKeen extends Keen {
    private static Spritesheet idleSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_map.png"), 3, 3, 16, 16);
    private static Spritesheet animationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_map.png"), 3, 2, 16, 16, 16, 0);

    private int up = 0;
    private int down = 0;

    public MapKeen(Level level) {
        super(level, 0, 0, new Animation(animationSprite, 3, 3, 71), idleSprite);
    }

    @Override
    protected void calculateAnimation() {
        animation.update();
        if(dx > 0 && animation.getCurrentState() != LEFT) {
            animation.setState(LEFT);
            idle = LEFT;
            texture = animation.getImage();
        } else if(dx < 0 && animation.getCurrentState() != RIGHT) {
            animation.setState(RIGHT);
            idle = RIGHT;
            texture = animation.getImage();
        }else if(dy > 0 && animation.getCurrentState() != DOWN) {
            animation.setState(DOWN);
            idle = DOWN;
            texture = animation.getImage();
        } else if(dy < 0 && animation.getCurrentState() != UP) {
            animation.setState(UP);
            idle = UP;
            texture = animation.getImage();
        }else if(dx == 0) {
            texture = idleSprite.getImage(idle, 0);
        }
    }

    @Override
    protected void calculateMovement() {
        if(left > 0) dx = -speed;
        if(right > 0) dx = speed;
        if(up > 0) dx = -speed;
        if(down > 0) dx = speed;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }
}

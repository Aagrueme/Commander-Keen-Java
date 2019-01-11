package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.levels.Level;

import commanderKeen.states.State;
import javafx.scene.input.KeyEvent;

import java.awt.image.BufferedImage;

public class MapKeen extends Keen {

    private static Spritesheet animationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_map.png"), 3, 2, 16, 16, 16, 0);
    private static Spritesheet idleSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/assets/entity/keen_map.png"), 3, 0, 16, 16);

    public MapKeen(Level level, State state) {
        super(level, 16 * 4, 16 * 33, 13, 16, new Animation(animationSprite, 3, 3, 71), idleSprite, state);
        speed = 1;
    }

    @Override
    public void keyPressed(KeyEvent e){
        super.keyPressed(e);

        switch (e.getCode()){
            case W: setUp(true);break;
            case S: setDown(true);break;
            case CONTROL:
                interact();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        super.keyReleased(e);

        switch (e.getCode()){
            case W: setUp(false);break;
            case S: setDown(false);break;
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

    protected void setUp(boolean up) {
        this.up = up;
    }
    protected void setDown(boolean down) {
        this.down = down;
    }
}

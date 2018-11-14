package commanderKeen.menu;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ResourceLoader;
import aagrueme.com.github.api.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;

public class Cursor {

    private Animation animation;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Cursor(){
        try {
            animation = new Animation(new Spritesheet(ImageIO.read(ResourceLoader.load("commanderKeen/assets/menu/Cursor.png")), 1, 5, 8, 8, 9*8, 0), 0, 5, 50L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert animation != null;
        animation.startAnimation();
    }

    public void render (Graphics2D g2d){
        g2d.drawImage(animation.getImage(), x, y, null);
    }

    public void update () {
        animation.update();
    }
}

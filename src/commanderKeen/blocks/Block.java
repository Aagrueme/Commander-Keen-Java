package commanderKeen.blocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Block implements Cloneable {

    private int x;
    private int y;

    protected Animation animation;

    private BufferedImage texture;
    private boolean newObject = false;

    {
        try {
            texture = ImageIO.read(ResourceLoader.load("commanderKeen/textures/blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Block(Animation animation){
        this();
        this.animation = animation;
        setTexture(animation.getImage());
    }

    public Block(){
        Blocks.blocks.add(this);
    }

    private void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    protected void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public Block createBlock(int x, int y){
        try {
            Block block = (Block)clone();
            block.setLocation(x, y);
            block.newObject = false;
            return block;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(){
        if (animation != null) {
            animation.update();
            texture = animation.getImage();
        }
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(texture, x, y, null);
    }

    public void render(Graphics2D g2d, int x, int y){
        g2d.drawImage(texture, x, y, null);
    }

    public boolean isNewObject() {
        return newObject;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

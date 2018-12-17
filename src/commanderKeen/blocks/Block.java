package commanderKeen.blocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ResourceLoader;
import commanderKeen.entitiy.mob.Keen;
import commanderKeen.main.GameFx;
import commanderKeen.registry.GameRegistry;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public abstract class Block implements Cloneable, Serializable {

    private int x;
    private int y;
    private int width=16;
    private int height=16;
    private String registryName;
    private int animationState;
    private Animation animation;
    private BufferedImage texture;
    private boolean newObject = false;
    private boolean solid = true;

    {
        try {
            texture = ImageIO.read(ResourceLoader.load("commanderKeen/textures/blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Block(String registryName, Animation animation, int state, boolean solid) {
        this(registryName, animation, state);
        this.solid = solid;
        setTexture(this.animation.getImage());
        this.animation.startAnimation();
    }

    public Block(String registryName, boolean solid) {
        this(registryName);
        this.solid = solid;
    }

    public Block(String registryName, Animation animation, int state){
        this(registryName);
        this.animationState = state;
        this.animation = animation;
        setTexture(this.animation.getImage());
        this.animation.startAnimation();
    }

    public Block(String registryName){
        this.registryName = registryName;
        Blocks.blocks.add(this);
        GameRegistry.registerBlock(this);
    }

    private void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    protected void setTexture(BufferedImage texture) {
        this.texture = texture;
    }
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public Rectangle getBounds() {
        return new Rectangle(x ,y,width,height);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Block createBlock(int x, int y){
        try {
            Block block = (Block)clone();
            block.setLocation(x, y);
            block.newObject = true;
            block.animation = this.animation;
            if(animation != null) {
                block.animation.setState(animationState);
            }
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
        if (GameFx.debug) {
            g2d.setColor(Color.BLACK);
            g2d.draw(getBounds());
        }
    }

    public void renderEditorBlock(Graphics2D g2d, int x, int y){
        render(g2d, x, y);
    }

    public boolean isNewObject() {
        return newObject;
    }

    @Override
    public String toString() {
        return registryName;
    }

    public String getRegistryName() {
        return registryName;
    }

    public boolean isSolid() {
        return solid;
    }
}

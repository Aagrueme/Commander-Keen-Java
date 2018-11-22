package commanderKeen.blocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ResourceLoader;
import com.sun.istack.internal.NotNull;
import commanderKeen.registry.GameRegistry;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Block implements Cloneable {

    private int x;
    private int y;
    private String registryName;
    private int animationState;
    public Animation animation;
    private BufferedImage texture;
    private boolean newObject = false;

    {
        try {
            texture = ImageIO.read(ResourceLoader.load("commanderKeen/textures/blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Block(String registryName, Animation animation, int state){
        this(registryName);
        this.animationState = state;
        this.animation = animation;
        animation.setState(state);
        setTexture(animation.getImage());
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

    public BufferedImage getTexture() {
        return texture;
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
}

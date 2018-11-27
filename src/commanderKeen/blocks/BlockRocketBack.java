package commanderKeen.blocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;

import java.awt.image.BufferedImage;

public class BlockRocketBack extends Block {
    public BlockRocketBack() {
        super("block_rocket_back_show", new Animation(new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/animations/rocket_back_sprite.png"), 0, 3, 16, 16), 0, 3, 71),  0,false);
        setTexture(animation.getImage());
        animation.startAnimation();
    }
}

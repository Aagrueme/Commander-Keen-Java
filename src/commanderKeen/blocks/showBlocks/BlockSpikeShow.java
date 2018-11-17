package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockSpikeShow extends Block {
    public BlockSpikeShow(){
        super(new Animation(new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/animations/spike_sprite.png"), 0, 3, 16, 16), 0, 4, 71), 0);
        animation.startAnimation();
    }
}

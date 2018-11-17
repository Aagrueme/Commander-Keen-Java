package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockTrapShow extends Block {
    public BlockTrapShow(){
        super(new Animation(new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/animations/trap_sprite.png"), 0, 3, 16, 16), 0, 4, 70), 0);
        animation.startAnimation();
    }
}
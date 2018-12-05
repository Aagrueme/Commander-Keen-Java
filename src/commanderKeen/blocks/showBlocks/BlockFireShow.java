package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockFireShow extends Block {

    public BlockFireShow(Variation variation){
        super("block_fire_show_" + variation.toString().toLowerCase(), new Animation(new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/animations/fire_sprite.png"), 1, 3, 16, 16), 1, 3, 71), variation.state);

    }

    public enum Variation{
        DARK(0),
        BRIGHT(1);

        private int state;

        Variation(int state){
            this.state = state;
        }
    }
}

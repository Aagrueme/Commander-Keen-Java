package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.Animation;
import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockTentacleShow extends Block {

    public BlockTentacleShow(Variation variation){
        super("block_tentacle_show_" + variation.toString().toLowerCase(), new Animation(new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/animations/tentacle_sprite.png"), 2, 3, 16, 16), 2, 3, 70), variation.state);
        animation.startAnimation();
    }

    public enum Variation{
        Left(0),
        Top(1),
        Right(2);
        private int state;

        Variation(int state){
            this.state = state;
        }
    }
}

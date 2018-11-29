package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;

import java.awt.image.BufferedImage;

public class BlockLevelDone extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/level_done.png"), 0, 4, 16, 16);

    public BlockLevelDone(Variation variation) {
        super("block_level_done_" + variation.name(), false);
        setTexture(variation.texture);
    }

    public enum Variation{
        small(variationSprite.getImage(0, 0)),
        big_left_top(variationSprite.getImage(0, 1)),
        big_right_top(variationSprite.getImage(0, 2)),
        big_right_bottom(variationSprite.getImage(0, 3)),
        big_left_bottom(variationSprite.getImage(0, 4));

        private BufferedImage texture;

        Variation(BufferedImage texture){
            this.texture = texture;
        }
    }
}

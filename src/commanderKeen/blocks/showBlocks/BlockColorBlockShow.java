package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockColorBlockShow extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/color_block.png"), 0, 5, 16, 16);

    public BlockColorBlockShow(Variation variation){
        super("block_color_show_" + variation.toString().toLowerCase(), false);
        setTexture(variation.texture);
    }

    public enum Variation{
        PURPLE(variationSprite.getImage(0)),
        BLUE(variationSprite.getImage(1)),
        RED(variationSprite.getImage(2)),
        GREEN(variationSprite.getImage(3)),
        YELLOW(variationSprite.getImage(4));

        private BufferedImage texture;

        Variation(BufferedImage texture){
            this.texture = texture;
        }
    }
}

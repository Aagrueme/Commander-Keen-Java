package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockBottleShow extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/bottle.png"), 0, 4, 16, 16);

    public BlockBottleShow(Variation variation){
        super("block_bottle_"+variation.toString().toLowerCase());
        setTexture(variation.texture);
    }

    public enum Variation{
        LEFT_BOTTOM(variationSprite.getImage(0)),
        RIGHT_BOTTOM(variationSprite.getImage(1)),
        LEFT_TOP(variationSprite.getImage(2)),
        RIGHT_TOP(variationSprite.getImage(3));

        private BufferedImage texture;

        Variation(BufferedImage texture){
            this.texture = texture;
        }
    }
}

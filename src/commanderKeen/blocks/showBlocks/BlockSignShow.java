package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockSignShow extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/sign.png"), 0, 2, 16, 16);

    public BlockSignShow(Variation variation){
        setTexture(variation.texture);
    }

    public enum Variation{
        BOTTOM(variationSprite.getImage(0)),
        TOP(variationSprite.getImage(1));

        private BufferedImage texture;

        Variation (BufferedImage texture){
            this.texture = texture;
        }
    }
}
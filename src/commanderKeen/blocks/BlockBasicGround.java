package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;

import java.awt.image.BufferedImage;

public class BlockBasicGround extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/basic_ground.png"), 0, 9, 16, 16);

    BlockBasicGround(Variation variation){
        super("block_basic_ground_" + variation.toString().toLowerCase());
        setTexture(variation.texture);
    }

    public enum Variation{

        Bottom(variationSprite.getImage( 0)),
        SideRight(variationSprite.getImage(1)),
        SideLeft(variationSprite.getImage(2)),
        Full(variationSprite.getImage( 3)),
        EdgeLeftTop(variationSprite.getImage(4)),
        EdgeRightTop(variationSprite.getImage(5)),
        EdgeRightBottom(variationSprite.getImage(6)),
        EdgeLeftBottom(variationSprite.getImage(7)),
        Top(variationSprite.getImage(8));

        private BufferedImage texture;

        Variation(BufferedImage texture) {
            this.texture = texture;
        }
    }

}

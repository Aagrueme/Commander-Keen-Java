package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;

import java.awt.image.BufferedImage;

public class BlockMap extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/map.png"), 0, 24, 16, 16);

    public BlockMap(Variation variation) {
        super("block_map_" + variation.name().toLowerCase());
        setTexture(variation.texture);
    }
    
    public enum Variation{
        block_1(variationSprite.getImage(0)),
        blovk_2(variationSprite.getImage(1)),
        blovk_3(variationSprite.getImage(2)),
        blovk_4(variationSprite.getImage(3)),
        blovk_5(variationSprite.getImage(4)),
        blovk_6(variationSprite.getImage(5)),
        blovk_7(variationSprite.getImage(6)),
        blovk_8(variationSprite.getImage(7)),
        blovk_9(variationSprite.getImage(8)),
        blovk_10(variationSprite.getImage(9)),
        blovk_11(variationSprite.getImage(10)),
        blovk_12(variationSprite.getImage(11)),
        blovk_13(variationSprite.getImage(12)),
        blovk_14(variationSprite.getImage(13)),
        blovk_15(variationSprite.getImage(14)),
        blovk_16(variationSprite.getImage(15)),
        blovk_17(variationSprite.getImage(16)),
        blovk_18(variationSprite.getImage(17)),
        blovk_19(variationSprite.getImage(18)),
        blovk_20(variationSprite.getImage(19)),
        blovk_21(variationSprite.getImage(20)),
        blovk_22(variationSprite.getImage(21)),
        blovk_23(variationSprite.getImage(22)),
        blovk_24(variationSprite.getImage(23));

        private BufferedImage texture;

        Variation(BufferedImage texture){
            this.texture = texture;
        }
    }
}

package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static commanderKeen.blocks.BlockMap.Variation.block_11;
import static commanderKeen.blocks.BlockMap.Variation.block_8;

public class BlockMap extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/map.png"), 0, 25, 16, 16);
    private Variation variation;

    BlockMap(Variation variation) {
        super("block_map_" + variation.name().toLowerCase());
        this.variation = variation;
        setTexture(variation.texture);
    }

    @Override
    public Rectangle getBounds() {
        if (variation.equals(block_11)) {
            return new Rectangle(getX() + 5, getY(), 11, 16);
        } else if(variation.equals(block_8)){
            return new Rectangle(getX() + 5, getY(), 11, 16);
        }else{
            return super.getBounds();
        }
    }

    public enum Variation{
        block_1(variationSprite.getImage(0)),
        block_2(variationSprite.getImage(1)),
        block_3(variationSprite.getImage(2)),
        block_4(variationSprite.getImage(3)),
        block_5(variationSprite.getImage(4)),
        block_6(variationSprite.getImage(5)),
        block_7(variationSprite.getImage(6)),
        block_8(variationSprite.getImage(7)),
        block_9(variationSprite.getImage(8)),
        block_10(variationSprite.getImage(9)),
        block_11(variationSprite.getImage(10)),
        block_12(variationSprite.getImage(11)),
        block_13(variationSprite.getImage(12)),
        block_14(variationSprite.getImage(13)),
        block_15(variationSprite.getImage(14)),
        block_16(variationSprite.getImage(15)),
        block_17(variationSprite.getImage(16)),
        block_18(variationSprite.getImage(17)),
        block_19(variationSprite.getImage(18)),
        block_20(variationSprite.getImage(19)),
        block_21(variationSprite.getImage(20)),
        block_22(variationSprite.getImage(21)),
        block_23(variationSprite.getImage(22)),
        block_24(variationSprite.getImage(23));

        private BufferedImage texture;

        Variation(BufferedImage texture){
            this.texture = texture;
        }
    }
}

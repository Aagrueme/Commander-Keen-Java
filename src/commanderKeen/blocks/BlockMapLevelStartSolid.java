package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.main.Game;
import commanderKeen.states.MapState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockMapLevelStartSolid extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/map_level_start.png"), 0, 18, 16, 16);
    private Variation variation;

    public BlockMapLevelStartSolid(Variation variation) {
        super("block_map_level_start_" + variation.name() + "_solid_true", true);
        this.variation = variation;
        setTexture(variation.texture);
    }

    public enum Variation{
        block_1(variationSprite.getImage(0,0), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_TOP),
        block_2(variationSprite.getImage(0,1), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_TOP),
        block_3(variationSprite.getImage(0,2), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_BOTTOM),
        block_4(variationSprite.getImage(0,3), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_BOTTOM),
        block_5(variationSprite.getImage(0,4), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_TOP),
        block_6(variationSprite.getImage(0,5), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_TOP),
        block_7(variationSprite.getImage(0,6), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_BOTTOM),
        block_8(variationSprite.getImage(0,7), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_BOTTOM),
        block_9(variationSprite.getImage(0,8), Blocks.BLOCK_LEVEL_DONE_SMALL),
        block_10(variationSprite.getImage(0,9), Blocks.BLOCK_LEVEL_DONE_SMALL),
        block_11(variationSprite.getImage(0,10), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_TOP),
        block_12(variationSprite.getImage(0,11), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_TOP),
        block_13(variationSprite.getImage(0,12), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_BOTTOM),
        block_14(variationSprite.getImage(0,13), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_BOTTOM),
        block_15(variationSprite.getImage(0,14), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_TOP),
        block_16(variationSprite.getImage(0,15), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_TOP),
        block_17(variationSprite.getImage(0,16), Blocks.BLOCK_LEVEL_DONE_BIG_LEFT_BOTTOM),
        block_18(variationSprite.getImage(0,17), Blocks.BLOCK_LEVEL_DONE_BIG_RIGHT_BOTTOM),
        block_19(variationSprite.getImage(0,18), Blocks.BLOCK_LEVEL_DONE_SMALL);

        private BufferedImage texture;
        private BlockLevelDone blockDone;

        Variation(BufferedImage texture, BlockLevelDone blockDone){
            this.texture = texture;
            this.blockDone = blockDone;
        }
    }


    public BlockLevelDone getBlockDone() {
        return variation.blockDone;
    }

    @Override
    public void renderEditorBlock(Graphics2D g2d, int x, int y) {
        g2d.drawImage(variation.texture, x, y, null);
        g2d.setColor(Color.RED);
        g2d.drawRect(x, y, 4, 4);
    }
}

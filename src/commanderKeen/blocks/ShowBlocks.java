package commanderKeen.blocks;

import commanderKeen.blocks.showBlocks.*;

public class ShowBlocks {
    public final BlockFireShow BLOCK_FIRE_SHOW_DARK = new BlockFireShow(BlockFireShow.Variation.DARK);
    public final BlockFireShow BLOCK_FIRE_SHOW_BRIGHT = new BlockFireShow(BlockFireShow.Variation.BRIGHT);

    public final BlockBottleShow BLOCK_BOTTLE_SHOW_LEFT_BOTTOM = new BlockBottleShow(BlockBottleShow.Variation.LEFT_BOTTOM);
    public final BlockBottleShow BLOCK_BOTTLE_SHOW_RIGHT_BOTTOM = new BlockBottleShow(BlockBottleShow.Variation.RIGHT_BOTTOM);
    public final BlockBottleShow BLOCK_BOTTLE_SHOW_RIGHT_TOP = new BlockBottleShow(BlockBottleShow.Variation.RIGHT_TOP);
    public final BlockBottleShow BLOCK_BOTTLE_SHOW_LEFT_TOP = new BlockBottleShow(BlockBottleShow.Variation.LEFT_TOP);

    public final BlockColorBlockShow BLOCK_COLOR_BLOCK_SHOW_PURPLE = new BlockColorBlockShow(BlockColorBlockShow.Variation.PURPLE);
    public final BlockColorBlockShow BLOCK_COLOR_BLOCK_SHOW_BLUE = new BlockColorBlockShow(BlockColorBlockShow.Variation.BLUE);
    public final BlockColorBlockShow BLOCK_COLOR_BLOCK_SHOW_RED = new BlockColorBlockShow(BlockColorBlockShow.Variation.RED);
    public final BlockColorBlockShow BLOCK_COLOR_BLOCK_SHOW_GREEN = new BlockColorBlockShow(BlockColorBlockShow.Variation.GREEN);
    public final BlockColorBlockShow BLOCK_COLOR_BLOCK_SHOW_YELLOW = new BlockColorBlockShow(BlockColorBlockShow.Variation.YELLOW);

    public final BlockBearShow BLOCK_BEAR_SHOW = new BlockBearShow();

    public final BlockSpikeShow BLOCK_SPIKE_SHOW = new BlockSpikeShow();
}

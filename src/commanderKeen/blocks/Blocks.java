package commanderKeen.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Blocks {
    public static List<Block> blocks = new ArrayList<>();

    public static final ShowBlocks ShowBlocks = new ShowBlocks();

    public static final BlockBasicGround BLOCK_BASIC_GROUND_BOTTOM = new BlockBasicGround(BlockBasicGround.Variation.Bottom);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_TOP = new BlockBasicGround(BlockBasicGround.Variation.Top);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_FULL = new BlockBasicGround(BlockBasicGround.Variation.Full);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_SIDE_LEFT = new BlockBasicGround(BlockBasicGround.Variation.SideLeft);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_SIDE_RIGHT = new BlockBasicGround(BlockBasicGround.Variation.SideRight);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_EDGE_LEFT_BOTTOM = new BlockBasicGround(BlockBasicGround.Variation.EdgeLeftBottom);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_EDGE_RIGHT_BOTTOM = new BlockBasicGround(BlockBasicGround.Variation.EdgeRightBottom);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_EDGE_RIGHT_TOP = new BlockBasicGround(BlockBasicGround.Variation.EdgeRightTop);
    public static final BlockBasicGround BLOCK_BASIC_GROUND_EDGE_LEFT_TOP = new BlockBasicGround(BlockBasicGround.Variation.EdgeLeftTop);

    public static final BlockRocketBack BLOCK_ROCKET_BACK = new BlockRocketBack();
    public static final BlockRocketFront BLOCK_ROCKET_Front = new BlockRocketFront();

    static{
        for (BlockMap.Variation variation:BlockMap.Variation.values()) {
            new BlockMap(variation);
        }
    }

    static{
        for (BlockMapLevelStart.Variation variation:BlockMapLevelStart.Variation.values()) {
            new BlockMapLevelStart(variation, false);
        }
    }

    static{
        for (BlockMapLevelStart.Variation variation:BlockMapLevelStart.Variation.values()) {
            new BlockMapLevelStart(variation, true);
        }
    }

    public static final BlockAir BLOCK_AIR = new BlockAir();
    public static final BlockNull BLOCK_NULL = new BlockNull();
}

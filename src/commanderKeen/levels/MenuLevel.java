package commanderKeen.levels;

import commanderKeen.blocks.Blocks;
import commanderKeen.blocks.ShowBlocks;
import commanderKeen.util.LevelSlot;

import java.io.Serializable;

public class MenuLevel extends Level implements Serializable {

    public MenuLevel() {
        super(new LevelSlot[20][5], 0, 8 * 16);

        setBlocks(convertFileToLevelData("commanderKeen/levels/menu_level.txt", new LevelBlockShortcut[]{
                new LevelBlockShortcut("g", Blocks.BLOCK_BASIC_GROUND_BOTTOM),
                new LevelBlockShortcut("a", Blocks.BLOCK_AIR),
                new LevelBlockShortcut("sr", Blocks.BLOCK_BASIC_GROUND_SIDE_RIGHT),
                new LevelBlockShortcut("sl", Blocks.BLOCK_BASIC_GROUND_SIDE_LEFT),
                new LevelBlockShortcut("f", Blocks.BLOCK_BASIC_GROUND_FULL),
                new LevelBlockShortcut("t", Blocks.BLOCK_BASIC_GROUND_TOP),
                new LevelBlockShortcut("elt", Blocks.BLOCK_BASIC_GROUND_EDGE_LEFT_TOP),
                new LevelBlockShortcut("ert", Blocks.BLOCK_BASIC_GROUND_EDGE_RIGHT_TOP),
                new LevelBlockShortcut("erb", Blocks.BLOCK_BASIC_GROUND_EDGE_RIGHT_BOTTOM),
                new LevelBlockShortcut("elb", Blocks.BLOCK_BASIC_GROUND_EDGE_LEFT_BOTTOM),
                new LevelBlockShortcut("bfs", Blocks.ShowBlocks.BLOCK_FIRE_SHOW_BRIGHT),
                new LevelBlockShortcut("blb", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_LEFT_BOTTOM),
                new LevelBlockShortcut("brb", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_RIGHT_BOTTOM),
                new LevelBlockShortcut("brt", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_RIGHT_TOP),
                new LevelBlockShortcut("blt", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_LEFT_TOP),
                new LevelBlockShortcut("b", Blocks.ShowBlocks.BLOCK_BEAR_SHOW),
                new LevelBlockShortcut("s", Blocks.ShowBlocks.BLOCK_SPIKE_SHOW),
                new LevelBlockShortcut("cbb", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_BLUE),
                new LevelBlockShortcut("cbg", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_GREEN),
                new LevelBlockShortcut("cbr", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_RED),
                new LevelBlockShortcut("cby", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_YELLOW)
        }));

    }

}

package commanderKeen.levels;

import aagrueme.com.github.api.ResourceLoader;
import commanderKeen.util.LevelSlot;

import java.io.IOException;

public class MenuLevel extends Level {

    public MenuLevel() {
        super(new LevelSlot[20][5], 0, 8 * 16);

        try {
            setBlocks(convertLevelFileToLevelData(ResourceLoader.load("commanderKeen/levels/menu_level.level")/*, new BlockShortcut[]{
                    new BlockShortcut("g", Blocks.BLOCK_BASIC_GROUND_BOTTOM),
                    new BlockShortcut("a", Blocks.BLOCK_AIR),
                    new BlockShortcut("sr", Blocks.BLOCK_BASIC_GROUND_SIDE_RIGHT),
                    new BlockShortcut("sl", Blocks.BLOCK_BASIC_GROUND_SIDE_LEFT),
                    new BlockShortcut("f", Blocks.BLOCK_BASIC_GROUND_FULL),
                    new BlockShortcut("t", Blocks.BLOCK_BASIC_GROUND_TOP),
                    new BlockShortcut("elt", Blocks.BLOCK_BASIC_GROUND_EDGE_LEFT_TOP),
                    new BlockShortcut("ert", Blocks.BLOCK_BASIC_GROUND_EDGE_RIGHT_TOP),
                    new BlockShortcut("erb", Blocks.BLOCK_BASIC_GROUND_EDGE_RIGHT_BOTTOM),
                    new BlockShortcut("elb", Blocks.BLOCK_BASIC_GROUND_EDGE_LEFT_BOTTOM),
                    new BlockShortcut("bfs", Blocks.ShowBlocks.BLOCK_FIRE_SHOW_BRIGHT),
                    new BlockShortcut("blb", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_LEFT_BOTTOM),
                    new BlockShortcut("brb", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_RIGHT_BOTTOM),
                    new BlockShortcut("brt", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_RIGHT_TOP),
                    new BlockShortcut("blt", Blocks.ShowBlocks.BLOCK_BOTTLE_SHOW_LEFT_TOP),
                    new BlockShortcut("b", Blocks.ShowBlocks.BLOCK_BEAR_SHOW),
                    new BlockShortcut("s", Blocks.ShowBlocks.BLOCK_SPIKE_SHOW),
                    new BlockShortcut("trap", Blocks.ShowBlocks.BLOCK_TRAP_SHOW),
                    new BlockShortcut("st", Blocks.ShowBlocks.BLOCK_SIGN_SHOW_TOP),
                    new BlockShortcut("sb", Blocks.ShowBlocks.BLOCK_SIGN_SHOW_BOTTOM),
                    new BlockShortcut("cbb", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_BLUE),
                    new BlockShortcut("cbg", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_GREEN),
                    new BlockShortcut("cbr", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_RED),
                    new BlockShortcut("cby", Blocks.ShowBlocks.BLOCK_COLOR_BLOCK_SHOW_YELLOW)
            }*/));
        } catch (IOException ignored) {
        }

    }

}

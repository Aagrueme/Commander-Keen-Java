package commanderKeen.registry;

import commanderKeen.blocks.Block;
import commanderKeen.blocks.BlockShortcut;
import commanderKeen.blocks.Blocks;

import java.util.ArrayList;
import java.util.List;

public class GameRegistry {
    private  static List<BlockShortcut> blocks = new ArrayList<>();

    public static void registerBlock(Block block){
        if(!block.equals(Blocks.BLOCK_NULL)) {
            blocks.add(new BlockShortcut(block.getRegistryName(), block));
        }
    }

    public static Block getBlock(String name){
        for (BlockShortcut shortcut : blocks) {
            if(shortcut.id.equalsIgnoreCase(name)){
                return shortcut.actualBlock;
            }
        }
        System.err.println(name);
        return Blocks.BLOCK_NULL;
    }
}

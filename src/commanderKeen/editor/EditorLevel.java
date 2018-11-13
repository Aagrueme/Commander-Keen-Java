package commanderKeen.editor;

import commanderKeen.blocks.Block;
import commanderKeen.levels.Level;
import commanderKeen.util.LevelSlot;

public class EditorLevel extends Level {
    public EditorLevel(int width, int height) {
        super(new LevelSlot[width][height]);
    }

    public void setBlock(Block block, int x, int y){
        level[x][y].setBlock(block);
    }
}

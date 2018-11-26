package commanderKeen.levels;

import commanderKeen.util.LevelSlot;

import java.io.IOException;

public class MapLevel extends Level {
    public MapLevel() {
        super(new LevelSlot[57][60], 0, 0);
        try {
            setBlocks(convertFileToLevelData("H:/Desktop/Java/Projects/Commander-Keen-Java/res/commanderKeen/levels/map_level.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

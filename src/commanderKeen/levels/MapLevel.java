package commanderKeen.levels;

import aagrueme.com.github.api.ResourceLoader;

import commanderKeen.util.LevelSlot;

import java.io.IOException;

public class MapLevel extends Level {
    public MapLevel() {
        super(new LevelSlot[57][60], 0, 0);
        try {
            setBlocks(convertFileToLevelData(ResourceLoader.load("commanderKeen/levels/map_level.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

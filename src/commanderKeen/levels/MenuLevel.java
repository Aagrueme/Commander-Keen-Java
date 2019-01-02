package commanderKeen.levels;

import aagrueme.com.github.api.ResourceLoader;
import commanderKeen.util.LevelSlot;

import java.io.IOException;

public class MenuLevel extends Level {

    public MenuLevel() {
        super(new LevelSlot[20][5], 0, 8 * 16);

        try {
            setBlocks(convertLevelFileToLevelData(ResourceLoader.load("commanderKeen/levels/menu_level.level")));
        } catch (IOException ignored) {
        }

    }

}

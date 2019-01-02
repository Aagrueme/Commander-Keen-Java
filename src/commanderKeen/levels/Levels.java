package commanderKeen.levels;

import java.util.ArrayList;
import java.util.List;

public class Levels {
    private static List<Level> levels = new ArrayList<>();

    public static void add(Level level) {
        levels.add(level);
    }

    public static Level getLevel (Class<? extends Level> c){
        for (Level level : levels) {
            if (level.getClass().equals(c)) {
                return level;
            }
        }

        return null;
    }
}

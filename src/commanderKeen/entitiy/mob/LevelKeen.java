package commanderKeen.entitiy.mob;

import aagrueme.com.github.api.Animation;
import commanderKeen.levels.Level;
import commanderKeen.states.State;

public class LevelKeen extends Keen {
    public LevelKeen(Level level, double x, double y, int width, int height, Animation animation, State state) {
        super(level, x, y, width, height, animation, state);
    }
}

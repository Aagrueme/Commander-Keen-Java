package commanderKeen.map;

import commanderKeen.levels.Level;
import commanderKeen.util.Camera;
import commanderKeen.util.LevelSlot;

public class Map extends Level {
    Camera camera;
    public Map(LevelSlot[][] blocks, Camera camera) {
        super(blocks, 0, 0);
        this.camera = camera;
    }
}

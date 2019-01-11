package commanderKeen.states;

import aagrueme.com.github.api.ImageLoader;
import commanderKeen.entitiy.mob.Keen;
import commanderKeen.entitiy.mob.LevelKeen;
import commanderKeen.entitiy.mob.MapKeen;
import commanderKeen.levels.Level;
import commanderKeen.levels.MapLevel;
import commanderKeen.main.GameFx;
import commanderKeen.util.Camera;
import javafx.scene.input.ScrollEvent;

import java.awt.*;
import javafx.scene.input.*;

public class MapState extends State {

	private boolean levelState = false;

    private Camera camera;
    Level level;
	Keen keen;

	public MapState(GameStateManager gsm) {
		super(gsm, GameFx.width / GameFx.ORIGINAL_WIDTH, GameFx.height / GameFx.ORIGINAL_HEIGHT);
        level = new MapLevel();
        keen = new LevelKeen(level, 16 * 3, 16*33, this);
        camera = new Camera(keen);
	}

	@Override
	public void update() {
		setScale(GameFx.width / 320d, GameFx.height / 200d);
		level.update();
		keen.update();
		camera.update();
	}

	@Override
	public void render(Graphics2D g) {
		Graphics2D g2d = camera.getGraphics(g);
		g2d.drawImage(ImageLoader.loadImage("commanderKeen/assets/menu/map.png"), -96, -32, null);
	    level.render(g2d);
	    keen.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    keen.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
        keen.keyReleased(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseWheelMoved(ScrollEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	public void openLevel(Level level) {
		levelState = true;
        this.level = level;
		keen = new MapKeen(level, this);
		camera = new Camera(keen);
	}
}

package commanderKeen.states;

import aagrueme.com.github.api.ImageLoader;
import commanderKeen.entitiy.mob.MapKeen;
import commanderKeen.levels.MapLevel;
import commanderKeen.main.Game;
import commanderKeen.main.GameFx;
import commanderKeen.main.GamePanel;
import commanderKeen.util.Camera;
import javafx.scene.input.ScrollEvent;

import java.awt.*;
import javafx.scene.input.*;

import static javafx.scene.input.KeyCode.D;

public class MapState extends State {

    private Camera camera;
    MapLevel level;
	MapKeen keen;

	public MapState(GameStateManager gsm) {
		super(gsm, GameFx.width / GameFx.ORIGINAL_WIDTH, GameFx.height / GameFx.ORIGINAL_HEIGHT);
        level = new MapLevel();
        keen = new MapKeen(level);
        camera = new Camera(keen);
	}

	@Override
	public void update() {
		setScale(GameFx.width / 320d, GameFx.height / 200d);
		level.update();
        camera.update();
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
	    switch (e.getCode()){
			case W: keen.setUp(true);break;
            case S: keen.setDown(true);break;
            case A: keen.setLeft(true);break;
            case D: keen.setRight(true);break;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
        switch (e.getCode()){
            case W: keen.setUp(false);break;
            case S: keen.setDown(false);break;
            case A: keen.setLeft(false);break;
            case D: keen.setRight(false);break;
        }
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
}

package commanderKeen.states;

import aagrueme.com.github.api.ImageLoader;
import commanderKeen.entitiy.mob.MapKeen;
import commanderKeen.levels.MapLevel;
import commanderKeen.main.Game;
import commanderKeen.main.GamePanel;
import commanderKeen.util.Camera;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MapState extends State {

    private Camera camera;
	private MapLevel level;
	private MapKeen keen;

	public MapState(GameStateManager gsm) {
		super(gsm, GamePanel.width / Game.ORIGINAL_WIDTH, GamePanel.height / Game.ORIGINAL_HEIGHT);
        level = new MapLevel();
        keen = new MapKeen(level);
        camera = new Camera(keen);
	}

	@Override
	public void update() {
		setScale(GamePanel.width / 320d, GamePanel.height / 200d);
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
	public void keyPressed(KeyEvent e, int k) {
	    switch (k){
			case KeyEvent.VK_W: keen.setUp(true);break;
            case KeyEvent.VK_S: keen.setDown(true);break;
            case KeyEvent.VK_A: keen.setLeft(true); break;
            case KeyEvent.VK_D: keen.setRight(true);break;
            case KeyEvent.VK_UP: keen.setUp(true);break;
            case KeyEvent.VK_DOWN: keen.setDown(true);break;
            case KeyEvent.VK_LEFT: keen.setLeft(true); break;
            case KeyEvent.VK_RIGHT: keen.setRight(true);break;
        }
	}

	@Override
	public void keyReleased(KeyEvent e, int k) {
	    switch (k){
            case KeyEvent.VK_W: keen.setUp(false);break;
            case KeyEvent.VK_S: keen.setDown(false);break;
            case KeyEvent.VK_A: keen.setLeft(false); break;
            case KeyEvent.VK_D: keen.setRight(false);break;
            case KeyEvent.VK_UP: keen.setUp(false);break;
            case KeyEvent.VK_DOWN: keen.setDown(false);break;
            case KeyEvent.VK_LEFT: keen.setLeft(false); break;
            case KeyEvent.VK_RIGHT: keen.setRight(false);break;
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}
}

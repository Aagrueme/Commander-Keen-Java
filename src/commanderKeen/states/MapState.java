package commanderKeen.states;

import commanderKeen.entitiy.mob.MapKeen;
import commanderKeen.levels.MapLevel;
import commanderKeen.main.Game;
import commanderKeen.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MapState extends State {

	MapLevel level;
	MapKeen keen;

	public MapState(GameStateManager gsm) {
		super(gsm, GamePanel.width / Game.ORIGINAL_WIDTH, GamePanel.height / Game.ORIGINAL_HEIGHT);
		level = new MapLevel();
		keen =  new MapKeen(level);
	}

	@Override
	public void update() {
		setScale(GamePanel.width / 320d, GamePanel.height / 200d);
		level.update();
		keen.update();
	}

	@Override
	public void render(Graphics2D g2d) {
	    level.render(g2d);
	    keen.render(g2d);
	}

	@Override
	public void keyPressed(KeyEvent e, int k) {
	    switch (k){
            case KeyEvent.VK_W:
                level.setY(level.getY() + 10);
                keen.setUp(true);
                break;
            case KeyEvent.VK_S:
                level.setY(level.getY() - 10);
                keen.setDown(true);
                break;
            case KeyEvent.VK_A:
                level.setX(level.getX() + 10);
                keen.setLeft(true);
                break;
            case KeyEvent.VK_D:
                level.setX(level.getX() - 10);
                keen.setRight(true);
                break;
        }
	}

	@Override
	public void keyReleased(KeyEvent e, int k) {
		switch (k){
			case KeyEvent.VK_W: keen.setUp(false);break;
			case KeyEvent.VK_A: keen.setLeft(false);break;
			case KeyEvent.VK_S: keen.setDown(false);break;
			case KeyEvent.VK_D: keen.setRight(false);break;
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

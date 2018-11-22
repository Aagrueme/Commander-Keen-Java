package commanderKeen.states;

import commanderKeen.main.Game;
import commanderKeen.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MapState extends State {

	public MapState(GameStateManager gsm) {
		super(gsm, GamePanel.width / Game.ORIGINAL_WIDTH, GamePanel.height / Game.ORIGINAL_HEIGHT);
	}

	@Override
	public void update() {
		setScale(GamePanel.width / 320d, GamePanel.height / 200d);
	}

	@Override
	public void render(Graphics2D g) {

	}

	@Override
	public void keyPressed(KeyEvent e, int k) {

	}

	@Override
	public void keyReleased(KeyEvent e, int k) {

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

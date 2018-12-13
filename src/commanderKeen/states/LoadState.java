package commanderKeen.states;

import javafx.scene.input.ScrollEvent;

import java.awt.Graphics2D;
import javafx.scene.input.*;

public class LoadState extends State {

	public LoadState(GameStateManager gsm) {
		super(gsm, 10, 10);
		gsm.setState(GameStateManager.MENU_STATE);
	}

	@Override
	public void update() {}

	@Override
	public void render(Graphics2D g2d) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
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

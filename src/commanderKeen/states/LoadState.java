package commanderKeen.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

}

package commanderKeen.states;

import commanderKeen.util.Animation;
import commanderKeen.util.ResourceLoader;
import commanderKeen.util.Spritesheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState extends State {

	private Animation animation;

	public MenuState(GameStateManager gsm) {
		super(gsm, 1);
		try {
			animation = new Animation(new Spritesheet(ImageIO.read(ResourceLoader.load("commanderKeen/assets/menu/Cursor.png")), 1, 5, 8, 8, 9*8, 0), 0, 5, 50L);
		} catch (Exception e) {
			e.printStackTrace();
		}
		animation.startAnimation();

	}

	@Override
	public void update() {
		animation.update();
	}

	@Override
	public void render(Graphics2D g2d) {g2d.drawImage(animation.getImage(), 0, 0, null); }

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

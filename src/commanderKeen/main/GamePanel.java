package commanderKeen.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import commanderKeen.states.GameStateManager;
import commanderKeen.util.Mouse;

public class GamePanel extends JPanel implements ActionListener,KeyListener,MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	public static int width;
	public static int height;
	
	public static Mouse mouse;
	
	public static GameStateManager gsm;

	GamePanel() {
		super();
        setPreferredSize(new Dimension(Game.width,Game.height));
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();

        mouse = new Mouse(this);
        width = getPreferredSize().width;
        height = getPreferredSize().height;

        gsm = new GameStateManager(GameStateManager.ACTION_CHOOSE_STATE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}

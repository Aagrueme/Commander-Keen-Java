package commanderKeen.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import commanderKeen.states.GameStateManager;
import commanderKeen.util.Mouse;

public class GamePanel extends JPanel implements ActionListener,KeyListener,MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	public static int width;
	public static int height;

    GamePanel() {
		super();
        setPreferredSize(new Dimension(Game.width,Game.height));
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocus();

        Game.mouse = new Mouse(this);
        Game.width = getPreferredSize().width;
        Game.height = getPreferredSize().height;

        Game.gsm = new GameStateManager(GameStateManager.MENU_STATE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
        Game.gsm.render(g2d);
	}

	private void update(){
        Game.gsm.update();
    }

    @Override
    public void addNotify() {
        Timer timer = new Timer(1000 / Game.FPS, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

	@Override
	public void mousePressed(MouseEvent e) {
        Game.gsm.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
        Game.gsm.mouseReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
        Game.gsm.keyPressed(e, e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
        Game.gsm.keyReleased(e, e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}

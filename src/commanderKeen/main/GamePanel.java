package commanderKeen.main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import commanderKeen.states.GameStateManager;
import commanderKeen.util.Mouse;

public class GamePanel extends JPanel implements MouseMotionListener,ComponentListener,ActionListener,KeyListener,MouseListener,MouseWheelListener {
	
	private static final long serialVersionUID = 1L;
	
	public static int width;
	public static int height;

    GamePanel(){
        super();
        setPreferredSize(new Dimension(Game.WIDTH,Game.HEIGHT));
        addKeyListener(this);
        addMouseListener(this);
        addComponentListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        requestFocus();

        Game.mouse = new Mouse(this);
        width = getPreferredSize().width;
        height = getPreferredSize().height;

        Game.gsm = new GameStateManager(GameStateManager.EDITOR_STATE, this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Game.gsm.render(g2d);
    }

    @Override
    public void addNotify(){
        super.addNotify();
        Timer timer = new Timer(1000 / Game.FPS, this);
        timer.start();
    }

    private void update() {
        Game.gsm.update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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
    public void mousePressed(MouseEvent e) {
        Game.gsm.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Game.gsm.mouseReleased(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        Game.gsm.mouseWheelMoved(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        if(e.getSource().equals(this)){
            width = getWidth();
            height = getHeight();
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Game.gsm.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Game.gsm.mouseMoved(e);
    }
}

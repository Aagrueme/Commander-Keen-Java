package commanderKeen.main;

import commanderKeen.states.GameStateManager;
import commanderKeen.util.Mouse;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame {
	
	static final long serialVersionUID = 1L;

    static int width = 600;
    static int height = 400;

    public static int FPS = 60;

    public static Mouse mouse;

    public static GameStateManager gsm;

	Game(){
		super("Shooter");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().add(new GamePanel(), BorderLayout.CENTER);
        pack();

        setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new Game();
	}

}

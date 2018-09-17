package commanderKeen.main;

import commanderKeen.states.GameStateManager;
import commanderKeen.util.Mouse;

import java.awt.BorderLayout;
import java.awt.image.VolatileImage;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame {
	
	static final long serialVersionUID = 1L;

    static int width = 600;
    static int height = 400;

    static int FPS = 60;

    public static int SCALE = 3;

    public static Mouse mouse;
    public static GameStateManager gsm;

    Game(){
        super("Minecraft Sky Survival Platformer of Awesomeness");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().add(new GamePanel(), BorderLayout.CENTER);
        pack();

        setVisible(true);
    }

    public static void main (String[] args){
        new Game();
    }

}

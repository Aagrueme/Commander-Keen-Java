package commanderKeen.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame {
	
	static final long serialVersionUID = 1L;

    static final int width = 600;
    static final int height = 400;

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

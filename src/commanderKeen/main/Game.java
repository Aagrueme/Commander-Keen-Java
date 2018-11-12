package commanderKeen.main;

import commanderKeen.states.GameStateManager;
import commanderKeen.util.Mouse;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends JFrame implements ComponentListener {
	
	static final long serialVersionUID = 1L;

	public static final boolean originalSize = true;

    public static int width;
    public static int height;

    static final int WIDTH;
    static final int HEIGHT;

    public static final double ORIGINAL_WIDTH = 320;
    public static final double ORIGINAL_HEIGHT = 200;

    public static boolean debug = false;

    static{
        if(originalSize) {
            WIDTH = 320;
            width = 320;
            HEIGHT = 200;
            height = 200;
        }else{
            WIDTH = 1200;
            width = 1200;
            HEIGHT = 750;
            height = 750;
        }
    }

    static int FPS = 60;

    public static int SCALE = 3;

    public static Mouse mouse;
    public static GameStateManager gsm;

    Game(){
        super("Commander Keen");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        addComponentListener(this);

        getContentPane().add(new GamePanel(), BorderLayout.CENTER);
        pack();

        setVisible(true);
    }

    public static void main (String[] args){
        debug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
        new Game();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if(e.getComponent() == this){
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
}

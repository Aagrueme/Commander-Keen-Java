package commanderKeen.states;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.PositionAnimation;
import commanderKeen.levels.MenuLevel;
import commanderKeen.main.GameFx;
import commanderKeen.menu.Cursor;
import commanderKeen.util.Button;
import javafx.scene.input.ScrollEvent;

import java.awt.*;
import javafx.scene.input.*;

public class MenuState extends State {

    private Button buttonNewGame = new Button(() -> gsm.setState(GameStateManager.MAP_STATE));
    private Button buttonContinueGame = new Button(() -> System.out.println("Clicked buttonContinueGame"));
    private Button buttonStory = new Button(() -> System.out.println("Clicked buttonStory"));
    private Button buttonAboutID = new Button(() -> System.out.println("Clicked buttonAboutID"));
    private Button buttonHighScores = new Button(() -> System.out.println("Clicked buttonHighScores"));
    private Button buttonOrderingInfo = new Button(() -> System.out.println("Clicked buttonOrderingInfo"));
    private Button buttonPreviews = new Button(() -> System.out.println("Clicked buttonPreviews"));
    private Button buttonRestartDemo = new Button(() -> System.out.println("Clicked buttonRestartDemo"));

    private Cursor cursor;
    private PositionAnimation cursorAnimation;
    private int cursorYPositions[] = new int[]{56, 56 + 8, 56 + (8 * 2), 56 + (8 * 3), 56 + (8 * 4), 56 + (8 * 5), 56 + (8 * 6), 56 + (8 * 7)};
    private int currentCursorYPosition = 0;

    private Button buttons[] = new Button[]{buttonNewGame, buttonContinueGame, buttonStory, buttonAboutID, buttonHighScores, buttonOrderingInfo ,buttonPreviews, buttonRestartDemo};

    private MenuLevel level;
    private boolean active;

	MenuState(GameStateManager gsm) {
		super(gsm, GameFx.width / GameFx.ORIGINAL_WIDTH, GameFx.height / GameFx.ORIGINAL_HEIGHT);
		level = new MenuLevel();
		cursor = new Cursor();
		cursor.setX(96);
        cursor.setY(56);
	}

	@Override
	public void update(){
	    cursor.update();
	    if(cursorAnimation != null){
	        cursorAnimation.update();
            cursor.setX(cursorAnimation.getX());
	        cursor.setY(cursorAnimation.getY());
        }
        setScale(GameFx.width / 320d, GameFx.height / 200d);
	    level.update();
    }

	@Override
	public void render(Graphics2D g2d) {
	    g2d.drawImage(ImageLoader.loadImage("commanderKeen/assets/menu/menuBackground.png"), 0, 0, null);
        level.render(g2d);
        if(active) {
            g2d.drawImage(ImageLoader.loadImage("commanderKeen/assets/menu/Menu.png"), 80, 48, null);
            cursor.render(g2d);
        }
	}

	private void moveCursor(){
        if (cursorAnimation == null) {
            cursorAnimation = new PositionAnimation(new Point(cursor.getX(), cursor.getY()), new Point(cursor.getX(), cursorYPositions[currentCursorYPosition]), 10L);
            cursorAnimation.startAnimation();
        }else if (!cursorAnimation.isRunning()) {
            cursorAnimation = new PositionAnimation(new Point(cursor.getX(), cursor.getY()), new Point(cursor.getX(), cursorYPositions[currentCursorYPosition]), 10L);
            cursorAnimation.startAnimation();
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
        if(active) {
            switch (e.getCode()) {
                case UP:
                    if (currentCursorYPosition <= 0) {
                        currentCursorYPosition = cursorYPositions.length - 1;
                    } else {
                        currentCursorYPosition--;
                    }
                    moveCursor();
                    break;
                case DOWN:
                    if (currentCursorYPosition >= cursorYPositions.length - 1) {
                        currentCursorYPosition = 0;
                    } else {
                        currentCursorYPosition++;
                    }
                    moveCursor();
                    break;
                case ENTER:
                    buttons[currentCursorYPosition].clicked();
                    break;
            }
        }else {
            active = true;
        }
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

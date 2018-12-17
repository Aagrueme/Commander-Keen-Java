package commanderKeen.main;

import commanderKeen.blocks.Blocks;
import commanderKeen.states.GameStateManager;
import commanderKeen.util.Mouse;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.management.ManagementFactory;

public class GameFx extends Application {

    public static final Color BACKGROUND_COLOR = new Color(168, 168, 168);

    public static final boolean originalSize = false;

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

    public static GameFx instance;

    public static GameStateManager gsm;

    private Stage window;
    private ImageView view;

    BufferedImage bf;

    @Override
    public void start(Stage primaryStage) {
        bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        window = primaryStage;
        window.setTitle("Commander Keen");
        window.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.F10));
        window.setOnCloseRequest(event -> System.exit(0));

        BorderPane layout = new BorderPane();
        WritableImage wi = SwingFXUtils.toFXImage(bf, null);

        view = new ImageView(wi);

        layout.setCenter(view);

        Scene scene = new Scene(layout, width, height);

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            height = newValue.intValue();
        });

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            width = newValue.intValue();
        });

        setListeners(scene);

        //init blocks
        Blocks.init();

        window.setScene(scene);

        JFrame frame = new JFrame("Do you want to open the editor?");

        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        int editor = JOptionPane.showConfirmDialog(frame, "Do you want to open the editor?");

        frame.dispose();

        switch (editor){
            case -1:
                System.exit(1);
                break;
            case 0:
                GameFx.gsm = new GameStateManager(GameStateManager.EDITOR_STATE, this);
                break;
            case 1:
                GameFx.gsm = new GameStateManager(GameStateManager.MENU_STATE, this);
                break;
            case 2:
                System.exit(1);
                break;
        }

        new Thread(this::run).start();

        instance = this;
        window.show();
    }

    private void update() {
        gsm.update();
    }

    private void render(){
        bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bf.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        gsm.render(g2d);
        view.setImage(SwingFXUtils.toFXImage(bf, null));
    }

    public void run() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = FPS;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long lastFpsTime = 0;
        while (true) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            lastFpsTime += updateLength;
            if (lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
            }

            this.update();

            this.render();

            try {
                long gameTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                //System.out.println(gameTime);
                Thread.sleep(gameTime);
            } catch (Exception e) {
            }
        }
    }

    private void setListeners(Scene scene){
        scene.setOnKeyPressed(event -> gsm.keyPressed(event));
        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.F10 && !window.isFullScreen()) {
                window.setFullScreen(true);
            }
            gsm.keyReleased(event);
        });
        scene.setOnMousePressed(event -> gsm.mousePressed(event));
        scene.setOnMouseReleased(event -> gsm.mouseReleased(event));
        scene.setOnMouseMoved(event -> gsm.mouseMoved(event));
        scene.setOnMouseDragged(event -> gsm.mouseDragged(event));
        scene.setOnScroll(event -> gsm.mouseWheelMoved(event));
    }

    public static void main(String[] args){
        debug = ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
        launch(args);
    }
}

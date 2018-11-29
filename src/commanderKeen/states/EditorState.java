package commanderKeen.states;

import aagrueme.com.github.api.ImageLoader;
import commanderKeen.blocks.Block;
import commanderKeen.blocks.Blocks;
import commanderKeen.levels.EditorLevel;
import commanderKeen.main.Game;
import commanderKeen.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class EditorState extends State {

    private EditorLevel level;
    private Block[] blocks;
    private static Rectangle levelBox = new Rectangle(0, 0, 258, 200);

    private Block selectedBlock = Blocks.BLOCK_BASIC_GROUND_BOTTOM;

    private int blocksBoxY = 0;
    private int selected = Blocks.blocks.indexOf(selectedBlock);

    EditorState(GameStateManager gsm) {
        super(gsm, GamePanel.width / 320d, GamePanel.height / 200d);

        int levelWidth = 10;
        int levelHeight = 10;

        for (boolean valid = false;!valid;){
            valid = true;
            String input = JOptionPane.showInputDialog(gsm.panel, "Enter wished width!", "Width", JOptionPane.INFORMATION_MESSAGE);
            if(input == null){
                System.exit(0);
            }
            try {
                levelWidth = Integer.parseInt(input);
            }catch (NumberFormatException e){
                valid = false;
            }
        }

        for (boolean valid = false;!valid;){
            valid = true;
            String input = JOptionPane.showInputDialog(gsm.panel, "Enter wished height!", "Height", JOptionPane.INFORMATION_MESSAGE);
            if(input == null){
                System.exit(0);
            }
            try {
                levelHeight = Integer.parseInt(input);
            }catch (NumberFormatException e){
                valid = false;
            }
        }

        level = new EditorLevel(levelWidth, levelHeight);
        level.setBlocks(new ArrayList<>());

        blocks = Blocks.blocks.toArray(new Block[0]);

    }

    @Override
    public void update() {
        setScale(GamePanel.width / 320d, GamePanel.height / 200d);
        levelBox = new Rectangle(0, 0, (int)(258 * scaleX), (int)(200 * scaleY));
        level.update();
    }

    @Override
    protected void render(Graphics2D g2d) {
        g2d.setTransform(AffineTransform.getTranslateInstance(level.getX(), level.getY()));
        g2d.drawImage(ImageLoader.loadImage("commanderKeen/assets/menu/map.png"), -96, -32, null);
        g2d.setTransform(AffineTransform.getTranslateInstance(0,0));
        g2d.setColor(new Color(168, 168, 168, 100));
        g2d.fill(levelBox);
        level.render(g2d);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(258, 0, 258, 200);
        g2d.setColor(Game.BACKGROUND_COLOR);
        g2d.fillRect(259, 0, 61, 200);

        for (int i = 0; i < blocks.length; i++) {
            Block block = blocks[i];
            block.renderEditorBlock(g2d, 259 + (10 + i % 2 * 26), (10 + (i / 2 * 16 + i / 2 * 10)) + blocksBoxY);
        }

        g2d.setColor(Color.RED);
        g2d.drawImage(Blocks.BLOCK_NULL.getTexture(), 259 + (10 + selected % 2 * 26), (10 + (selected / 2 * 16 + selected / 2 * 10)) + blocksBoxY, null);
    }

    @Override
    public void keyPressed(KeyEvent e, int k) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                if (level.isGridEnabled()) {
                    level.setGrid(false);
                }else if(!level.isGridEnabled()){
                    level.setGrid(true);
                }
                break;
            case KeyEvent.VK_ENTER:
                level.save();
                break;
            case KeyEvent.VK_O:
                level.open();
                break;
        }
    }

    private boolean mouseWheelPressed = false;

    @Override
    public void keyReleased(KeyEvent e, int k) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (levelBox.contains(e.getPoint())) {
            if (e.getButton() == MouseEvent.BUTTON2) {
                mouseWheelPressed = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (levelBox.contains(e.getPoint())) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                int blockX = (int)((e.getX() / scaleX - level.getX()) / 16);
                int blockY = (int)((e.getY() / scaleY - level.getY()) / 16);
                if(!((blockX > level.width) || (blockY >= level.height))) {
                    level.setBlock(selectedBlock, blockX, blockY);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                int blockX = (int)((e.getX() / scaleX - level.getX()) / 16);
                int blockY = (int)((e.getY() / scaleY - level.getY()) / 16);
                if(!((blockX > level.width) || (blockY >= level.height))) {
                    level.setBlock(Blocks.BLOCK_AIR, blockX, blockY);
                }
            }else if(e.getButton() == MouseEvent.BUTTON2) {
                mouseWheelPressed = false;
            }
        }else{
            for (int i = 0; i < blocks.length; i++) {
                Rectangle rect = new Rectangle((int)((259 + (10 + i % 2 * 26)) * scaleX), (int)((((10 + (i / 2 * 16 + i / 2 * 10))) + blocksBoxY) * scaleY), (int)(16 * scaleX), (int)(16 * scaleY));
                if (rect.contains(e.getPoint())){
                    selectedBlock = Blocks.blocks.get(i);
                    selected = i;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(!levelBox.contains(e.getPoint())) {
            if (e.getWheelRotation() < 0) {
                if (blocksBoxY != 0) {
                    blocksBoxY += 5;
                }
            } else if (e.getWheelRotation() > 0) {
                if (abs(blocksBoxY) <= ((blocks.length /2 + blocks.length % 2) * 26) - 190)
                    blocksBoxY -= 5;
            }
        }
    }

    private Point mousePoint;

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
    }

    private double abs(double d){
        return d - d * 2;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double x = abs(mousePoint.getX() /scaleX - e.getX() /scaleX);
        double y = abs(mousePoint.getY() /scaleY - e.getY() /scaleY);
        if (mouseWheelPressed) {
            if ((int) x != 0 || (int) y != 0) {
                if (level.getX() + x <= 0 && level.getX() + x + level.width * 16 >= 258) {
                    level.setX(level.getX() + x);
                }
                if (level.getY() + y <= 0 && level.getY() + y + level.height * 16 >= 200) {
                    level.setY(level.getY() + y);
                }
                mousePoint = e.getPoint();
            }
        }else {
            mousePoint = e.getPoint();
        }
    }
}

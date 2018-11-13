package commanderKeen.states;

import commanderKeen.blocks.Block;
import commanderKeen.blocks.Blocks;
import commanderKeen.editor.EditorLevel;
import commanderKeen.main.Game;
import commanderKeen.main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class EditorState extends State {

    private EditorLevel level;
    private BufferedImage[] blocks;
    private static Rectangle levelBox = new Rectangle(0, 0, 258, 200);

    private Block selectedBlock = Blocks.BLOCK_BASIC_GROUND_BOTTOM;

    EditorState(GameStateManager gsm) {
        super(gsm, GamePanel.width / 320d, GamePanel.height / 200d);

        int levelWidth = 10;
        int levelHeight = 10;

        for (boolean valid = false;!valid;){
            valid = true;
            String input = JOptionPane.showInputDialog(gsm.panel, "Enter wished width!", "Width", JOptionPane.INFORMATION_MESSAGE);
            try {
                levelWidth = Integer.parseInt(input);
            }catch (NumberFormatException e){
                valid = false;
            }
        }

        for (boolean valid = false;!valid;){
            valid = true;
            String input = JOptionPane.showInputDialog(gsm.panel, "Enter wished height!", "Height", JOptionPane.INFORMATION_MESSAGE);
            try {
                levelHeight = Integer.parseInt(input);
            }catch (NumberFormatException e){
                valid = false;
            }
        }

        level = new EditorLevel(levelWidth, levelHeight);
        level.setBlocks(new ArrayList<>());

        List<BufferedImage> list = new ArrayList<>();

        for (Block block : Blocks.blocks) {
            if (!(block.equals(Blocks.BLOCK_AIR) || block.equals(Blocks.BLOCK_NULL))){
                list.add(block.getTexture());
            }
        }

        blocks = list.toArray(new BufferedImage[0]);

    }

    @Override
    public void update() {
        setScale(GamePanel.width / 320d, GamePanel.height / 200d);
        level.update();
    }

    @Override
    protected void render(Graphics2D g2d) {
        level.render(g2d);
        g2d.setColor(Color.BLACK);
        g2d.drawLine(258, 0, 258, 200);
        g2d.setColor(Game.BACKGROUND_COLOR);
        g2d.fillRect(259, 0, 61, 200);

        for (int i = 0; i < blocks.length; i++) {
            BufferedImage texture = blocks[i];
            g2d.drawImage(texture, 259 + (10 + i % 2 * 26), 10 + (i / 2 * 16 + i / 2 * 10), null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e, int k) {

    }

    @Override
    public void keyReleased(KeyEvent e, int k) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        levelBox = new Rectangle(0, 0, (int)(258 * scaleX), (int)(200 * scaleY));
        if (levelBox.contains(e.getPoint())) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                int blockX = (int)(e.getX() / scaleX / 16);
                int blockY = (int)(e.getY() / scaleY / 16);
                System.out.println(blockX);
                System.out.println(blockY);
                level.setBlock(selectedBlock, blockX, blockY);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                int blockX = (int)(e.getX() / scaleX / 16);
                int blockY = (int)(e.getY() / scaleY / 16);
                level.setBlock(Blocks.BLOCK_AIR, blockX, blockY);
            }
        }else{

        }
    }
}

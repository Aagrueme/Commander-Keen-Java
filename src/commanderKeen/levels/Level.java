package commanderKeen.levels;

import aagrueme.com.github.api.ResourceLoader;
import com.sun.istack.internal.NotNull;
import commanderKeen.blocks.Block;
import commanderKeen.blocks.Blocks;
import commanderKeen.main.Game;
import commanderKeen.util.LevelSlot;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Level {

    //                  x y
    protected LevelSlot[][] level;

    public ArrayList<Block> blocks;
    public int width;
    public int height;
    private int x;
    private int y;

    public Level(@NotNull LevelSlot[][] blocks){
        this(blocks, 0, 0);
    }

    public Level(@NotNull LevelSlot[][] blocks, int x, int y){
        this.level = blocks;
        this.width = blocks.length;
        this.height = blocks[0].length;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void init(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                level[x][y] = new LevelSlot();
                try{
                    level[x][y].setBlock(blocks.get(y * width + x).createBlock(x * 16 + this.x, y * 16 + this.y));
                } catch (IndexOutOfBoundsException e){
                }
            }
        }
    }

    public void update() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    level[x][y].getBlock().update();
                } catch (NullPointerException e) {
                }
            }
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(Color.white);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    level[x][y].getBlock().render(g2d, x * 16, y * 16);
                }catch (NullPointerException e) {
                }
                if(Game.debug){
                    g2d.drawRect(x * 16 + this.x, y * 16 + this.y, 16, 16);
                }
            }
        }
    }


    public void setBlocks(ArrayList<Block> blocks) {
        if(blocks.size() != width * height){
            while (blocks.size() < width * height){
                blocks.add(Blocks.BLOCK_AIR);
            }
        }

        this.blocks = blocks;
        init();
    }

    protected ArrayList<Block> convertFileToLevelData(String path, LevelBlockShortcut pBlocks[]){
        ArrayList<Block> blocks = new ArrayList<>();
        try {
            String s;
            BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.load(path)));
            while ((s = reader.readLine()) != null){
                String blockStrings[] = s.split(" ");
                for (String s1 : blockStrings) {
                    boolean b = true;
                    if(b) {
                        for (LevelBlockShortcut shortcut : pBlocks) {
                            if (s1.equalsIgnoreCase(shortcut.id) && b) {
                                blocks.add(shortcut.actualBlock);
                                b = false;
                            }
                        }
                        if (b) {
                            blocks.add(Blocks.BLOCK_NULL);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blocks;
    }

    protected class LevelBlockShortcut{
        public Block actualBlock;
        public String id;
        protected LevelBlockShortcut(String id, Block actualBlock){
            this.id = id;
            this.actualBlock = actualBlock;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

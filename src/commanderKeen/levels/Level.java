package commanderKeen.levels;

import aagrueme.com.github.api.ResourceLoader;
import com.sun.istack.internal.NotNull;
import commanderKeen.blocks.Block;
import commanderKeen.blocks.Blocks;
import commanderKeen.main.Game;
import commanderKeen.registry.GameRegistry;
import commanderKeen.util.LevelSlot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;

public abstract class Level {

    protected LevelSlot[][] level;

    public ArrayList<Block> blocks;
    public int width;
    public int height;
    private double x;
    private double y;

    private boolean grid;

    public Level(@NotNull LevelSlot[][] blocks){
        this(blocks, 0, 0);
    }

    public Level(@NotNull LevelSlot[][] blocks, double x, double y){
        this.level = blocks;
        this.width = blocks.length;
        this.height = blocks[0].length;
        this.x = x;
        this.y = y;
        this.grid = Game.debug;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    private void init(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                level[x][y] = new LevelSlot();
                Block block = blocks.get(y * width + x);
                try{
                    if(block.isNewObject()){
                        level[x][y].setBlock(block);
                    }else{
                        level[x][y].setBlock(block.createBlock(x * 16, y * 16));
                    }
                } catch (IndexOutOfBoundsException ignored){}
            }
        }
    }

    public void update() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    level[x][y].getBlock().update();
                } catch (NullPointerException ignored) {}
            }
        }
    }

    public void render(Graphics2D g2d) {
        g2d.setTransform(AffineTransform.getTranslateInstance(x, y));
        g2d.setColor(Color.white);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    level[x][y].getBlock().render(g2d, x * 16, y * 16);
                } catch (NullPointerException ignored) {}
                if(grid){
                    int width = 16;
                    int height = 16;
                    if(x == this.width -1){
                        width --;
                    }

                    if(y == this.height -1){
                        height --;
                    }
                    g2d.drawRect(x * 16, y * 16, width, height);
                }
            }
        }
        g2d.setTransform(AffineTransform.getTranslateInstance(0, 0));
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

    protected ArrayList<Block> convertFileToLevelData(String path) throws IOException {
        JSONArray array = new JSONObject(new BufferedReader(new InputStreamReader(ResourceLoader.load(path))).readLine()).getJSONArray("level");
        ArrayList<Block> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Block block = GameRegistry.getBlock(array.getString(i));
            list.add(i, block);
        }
        return list;

        /*ArrayList<Block> blocks = new ArrayList<>();
        try {
            String s;
            BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.load(path)));
            while ((s = reader.readLine()) != null){
                String blockStrings[] = s.split(" ");
                for (String s1 : blockStrings) {
                    boolean b = true;
                    if(b) {
                        for (BlockShortcut shortcut : pBlocks) {
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
        return blocks;*/
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setGrid(boolean grid) {
        this.grid = grid;
    }

    public boolean isGridEnabled() {
        return grid;
    }
}

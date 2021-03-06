package commanderKeen.levels;

import com.blogspot.debukkitsblog.util.FileStorage;
import com.blogspot.debukkitsblog.util.InputStorage;
import com.sun.istack.internal.NotNull;
import commanderKeen.blocks.Block;
import commanderKeen.blocks.Blocks;
import commanderKeen.main.GameFx;
import commanderKeen.registry.GameRegistry;
import commanderKeen.util.LevelSlot;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;

public abstract class Level {

    public LevelSlot[][] level;

    public ArrayList<Block> blocks;
    public int width;
    public int height;
    protected double x;
    protected double y;

    protected boolean grid;

    public Level(@NotNull LevelSlot[][] blocks){
        this(blocks, 0, 0);
    }

    public Level(@NotNull LevelSlot[][] blocks, double x, double y){
        this.level = blocks;
        this.width = blocks.length;
        this.height = blocks[0].length;
        this.x = x;
        this.y = y;
        this.grid = GameFx.debug;

        Levels.add(this);
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
        AffineTransform transform = g2d.getTransform();
        g2d.setTransform(AffineTransform.getTranslateInstance( g2d.getTransform().getTranslateX() + x, g2d.getTransform().getTranslateY() + y));
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
        g2d.setTransform(transform);
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

    protected ArrayList<Block> convertJsonFileToLevelData(InputStream file) throws IOException {
        JSONArray array = new JSONObject(new BufferedReader(new InputStreamReader(file)).readLine()).getJSONArray("level");
        ArrayList<Block> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Block block = GameRegistry.getBlock(array.getString(i));
            list.add(i, block);
        }
        return list;
    }

    protected ArrayList<Block> convertLevelFileToLevelData(InputStream file) throws IOException {
        String names[] = (String[]) new InputStorage(file).get("level");
        ArrayList<Block> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Block block = GameRegistry.getBlock(names[i]);
            list.add(i, block);
        }
        return list;
    }

    protected ArrayList<Block> convertFileToLevelData(String path) throws IOException {
        if(path.endsWith(".json")){
        return convertJsonFileToLevelData(new File(path));
        }else if(path.endsWith(".level")){
            return convertLevelFileToLevelData(new File(path));
        }else{
            return null;
        }
    }

    protected ArrayList<Block> convertFileToLevelData(File file) throws IOException {
        if(file.getPath().endsWith(".json")){
            return convertJsonFileToLevelData(file);
        }else if(file.getPath().endsWith(".level")){
            return convertLevelFileToLevelData(file);
        }else{
            return null;
        }
    }

    private ArrayList<Block> convertLevelFileToLevelData(File file) throws IOException {
        try {
            FileStorage storage = null;
            try {
                storage = new FileStorage(file);
            } catch (EOFException ignored) {
                JFrame frame = new JFrame("Error");

                frame.setUndecorated(true);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                JOptionPane.showMessageDialog(frame, "The selected File is corrupted!", "Error", JOptionPane.ERROR_MESSAGE);

                frame.dispose();
            }

            assert storage != null;
            ArrayList<Block> list = new ArrayList<>();
            String names[] = (String[])storage.get("level");
            for (String name:names) {
                list.add(GameRegistry.getBlock(name));
            }
            return list;

        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Block> convertJsonFileToLevelData(File file) throws IOException {
        JSONArray array = new JSONObject(new BufferedReader(new FileReader(file)).readLine()).getJSONArray("level");
        ArrayList<Block> list = new ArrayList<>();
        System.out.println("read");
        for (int i = 0; i < array.length(); i++) {
            Block block = GameRegistry.getBlock(array.getString(i));
            list.add(i, block);
        }
        return list;
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

package commanderKeen.levels;

import com.blogspot.debukkitsblog.util.FileStorage;
import commanderKeen.blocks.Block;
import commanderKeen.levels.Level;
import commanderKeen.util.LevelSlot;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditorLevel extends Level {
    public EditorLevel(int width, int height) {
        super(new LevelSlot[width][height]);
    }

    public void setBlock(Block block, int x, int y){
        if(block.isNewObject()) {
            level[x][y].setBlock(block);
        }else {
            level[x][y].setBlock(block.createBlock(x, y));
        }
        blocks.set(y * width + x, block);
    }

    @Override
    public void render(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        g2d.setTransform(AffineTransform.getTranslateInstance( g2d.getTransform().getTranslateX() + x, g2d.getTransform().getTranslateY() + y));
        g2d.setColor(Color.white);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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
                    level[x][y].getBlock().renderEditorBlock(g2d, x * 16, y * 16);
                    g2d.setColor(Color.WHITE);
                }else{
                    level[x][y].getBlock().render(g2d, x * 16, y * 16);
                }
            }
        }
        g2d.setTransform(transform);
    }

    public void save(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setDialogTitle("Save");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Only json files!", "json"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Only level files!", "level"));
            fileChooser.showSaveDialog(null);
            File file = fileChooser.getSelectedFile();
            if (file.getPath().endsWith(".json")) {
                JSONObject json = new JSONObject();
                String names[] = new String[blocks.size()];
                for (int i = 0; i < blocks.size(); i++) {
                    names[i] = blocks.get(i).getRegistryName();
                }
                JSONArray array = new JSONArray(names);
                json.put("level", array);

                //save Json

                file.createNewFile();

                FileWriter fw = new FileWriter(file);
                fw.write(json.toString());
                fw.close();
            }
            if (file.getPath().endsWith(".level")){
                FileStorage storage = null;
                try {
                    storage = new FileStorage(file);
                }catch (EOFException ignored){
                    JFrame frame = new JFrame("Error");

                    frame.setUndecorated(true);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);

                    JOptionPane.showMessageDialog(frame, "The selected File is corrupted!", "Error", JOptionPane.ERROR_MESSAGE);

                    frame.dispose();

                    save();
                }

                String names[] = new String[blocks.size()];
                for (int i = 0; i < blocks.size(); i++) {
                    names[i] = blocks.get(i).getRegistryName();
                }

                assert storage != null;
                storage.store("level", names);
                storage.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        File file = null;
        for (boolean valid = false;!valid;){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setDialogTitle("Open");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Only json files!", "json"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("Only level files!", "level"));
            fileChooser.showOpenDialog(null);
            file = fileChooser.getSelectedFile();
            if (file.exists()) {
                valid = true;
            }
        }
        try {
            setBlocks(convertFileToLevelData(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

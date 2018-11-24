package commanderKeen.levels;

import commanderKeen.blocks.Block;
import commanderKeen.levels.Level;
import commanderKeen.util.LevelSlot;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public void save(){
        JSONObject json = new JSONObject();
        String names[] = new String[blocks.size()];
        for (int i = 0; i < blocks.size(); i++) {
            names[i] = blocks.get(i).getRegistryName();
        }
        JSONArray array = new JSONArray(names);
        json.put("level", array);
        try {
            File file = null;
            for (boolean valid = false;!valid;){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Save");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Only json files!", "json"));
                fileChooser.showOpenDialog(null);
                file = fileChooser.getSelectedFile();
                if (file.exists()) {
                    valid = true;
                }
            }
            FileWriter fw = new FileWriter(file);
            fw.write(json.toString());
            fw.close();
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

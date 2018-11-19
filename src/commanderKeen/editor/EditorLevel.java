package commanderKeen.editor;

import commanderKeen.blocks.Block;
import commanderKeen.levels.Level;
import commanderKeen.registry.GameRegistry;
import commanderKeen.util.LevelSlot;
import org.json.JSONArray;
import org.json.JSONObject;

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
        String blockNames[] = new String[blocks.size()];
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            blockNames[i] = block.getRegistryName();
        }
        JSONArray array = new JSONArray(blockNames);
        json.put("level", array);
        try {
            FileWriter fw = new FileWriter(new File("/home/aagrueme/GitHub/Commander-Keen-Java/res/commanderKeen/levels/menu_level.json"));
            fw.write(json.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

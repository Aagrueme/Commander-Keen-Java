package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.ResourceLoader;
import aagrueme.com.github.api.Spritesheet;
import commanderKeen.entitiy.mob.Keen;
import commanderKeen.levels.Level;
import commanderKeen.main.GameFx;
import commanderKeen.states.MapState;
import commanderKeen.states.State;
import commanderKeen.util.LevelSlot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlockMapLevelStart extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/map_level_start.png"), 0, 18, 16, 16);
    private Variation variation;
    private Level level;

    public BlockMapLevelStart(Variation variation) {
        super("block_map_level_start_" + variation.name() + "_solid_false", false);
        this.variation = variation;
        setTexture(variation.texture);
    }

    public enum Variation{
        block_1(variationSprite.getImage(0,0)),
        block_2(variationSprite.getImage(0,1)),
        block_3(variationSprite.getImage(0,2)),
        block_4(variationSprite.getImage(0,3)),
        block_5(variationSprite.getImage(0,4)),
        block_6(variationSprite.getImage(0,5)),
        block_7(variationSprite.getImage(0,6)),
        block_8(variationSprite.getImage(0,7)),
        block_9(variationSprite.getImage(0,8)),
        block_10(variationSprite.getImage(0,9)),
        block_11(variationSprite.getImage(0,10)),
        block_12(variationSprite.getImage(0,11)),
        block_13(variationSprite.getImage(0,12)),
        block_14(variationSprite.getImage(0,13)),
        block_15(variationSprite.getImage(0,14)),
        block_16(variationSprite.getImage(0,15)),
        block_17(variationSprite.getImage(0,16)),
        block_18(variationSprite.getImage(0,17)),
        block_19(variationSprite.getImage(0,18));

        private BufferedImage texture;

        Variation(BufferedImage texture){
            this.texture = texture;
        }
    }

    @Override
    public void interact(Keen player, State rawState) {
        MapState state = (MapState) rawState;
        state.openLevel(new Level(new LevelSlot[116][117], 0, 0) {
            {
                try {
                    setBlocks(convertLevelFileToLevelData(ResourceLoader.load("commanderKeen/levels/level_1.level")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void renderEditorBlock(Graphics2D g2d, int x, int y) {
        g2d.drawImage(variation.texture, x, y, null);
        g2d.setColor(Color.GREEN);
        g2d.drawRect(x, y, 4, 4);
    }
}

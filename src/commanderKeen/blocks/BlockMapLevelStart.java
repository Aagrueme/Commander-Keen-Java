package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;
import aagrueme.com.github.api.Spritesheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockMapLevelStart extends Block {

    private static Spritesheet variationSprite = new Spritesheet((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/map_level_start.png"), 0, 22, 16, 16);
    private Variation variation;
    private boolean solid;

    public BlockMapLevelStart(Variation variation, boolean solid) {
        super("block_map_level_start_" + variation.name() + "_solid_" + solid, solid);
        this.variation = variation;
        this.solid = solid;
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
        block_19(variationSprite.getImage(0,18)),
        block_20(variationSprite.getImage(0,19)),
        block_21(variationSprite.getImage(0,20)),
        block_22(variationSprite.getImage(0,21));

        private BufferedImage texture;
        private BufferedImage textureSolid;
        private BufferedImage textureNonSolid;

        Variation(BufferedImage texture){
            this.texture = copyImage(texture);
            BufferedImage defaultTexture = copyImage(texture);
            Graphics2D g2d = texture.createGraphics();
            g2d.setColor(Color.RED);
            g2d.drawRect(0, 0, 4, 4);
            this.textureSolid = texture;
            Graphics2D g = defaultTexture.createGraphics();
            g.setColor(Color.GREEN);
            g.drawRect(0, 0, 4, 4);
            this.textureNonSolid = defaultTexture;
        }
    }

    @Override
    public void renderEditorBlock(Graphics2D g2d) {
        if(solid) {
            g2d.drawImage(variation.textureSolid, x, y, null);
        }else{
            g2d.drawImage(variation.textureNonSolid, x, y, null);
        }
    }

    @Override
    public void renderEditorBlock(Graphics2D g2d, int x, int y) {
        if(solid) {
            g2d.drawImage(variation.textureSolid, x, y, null);
        }else{
            g2d.drawImage(variation.textureNonSolid, x, y, null);
        }
    }

    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
}

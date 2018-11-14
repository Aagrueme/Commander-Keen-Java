package commanderKeen.blocks.showBlocks;

import aagrueme.com.github.api.ImageLoader;
import commanderKeen.blocks.Block;

import java.awt.image.BufferedImage;

public class BlockBearShow extends Block {
    private static BufferedImage texture = (BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/bear.png");
    public BlockBearShow(){
        setTexture(texture);
    }
}

package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;

import java.awt.image.BufferedImage;

public class BlockRocketFront extends Block {
    public BlockRocketFront() {
        super("Block_rocket_front", false);
        setTexture((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/rocket.png"));
    }
}

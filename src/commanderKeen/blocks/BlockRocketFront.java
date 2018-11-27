package commanderKeen.blocks;

import aagrueme.com.github.api.ImageLoader;

import java.awt.image.BufferedImage;

public class BlockRocketFront extends Block {
    public BlockRocketFront() {
        super("block_rocket_front_show", false);
        setTexture((BufferedImage) ImageLoader.loadImage("commanderKeen/textures/blocks/rocket_front.png"));
    }
}

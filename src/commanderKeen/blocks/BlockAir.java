package commanderKeen.blocks;

import java.awt.image.BufferedImage;

class BlockAir extends Block {
    BlockAir(){
        setTexture(new BufferedImage(16, 16, BufferedImage.TRANSLUCENT));
    }
}

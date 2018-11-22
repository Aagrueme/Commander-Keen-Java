package commanderKeen.blocks;

public class BlockShortcut{
    public Block actualBlock;
    public String id;
    public BlockShortcut(String id, Block actualBlock){
        this.id = id;
        this.actualBlock = actualBlock;
    }
}
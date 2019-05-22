package net.minecraft.client.addon.lavablock.Block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockLavaPortal extends BlockStairs
{
    public BlockLavaPortal(int par1, Block par2Block, int par3)
    {
        super(par1, par2Block, par3);
    }

    public int quantityDropped(Random par1Random)
    {
        return super.blockID > 3799 && super.blockID < 3800 ? 0 : 1;
    }
}

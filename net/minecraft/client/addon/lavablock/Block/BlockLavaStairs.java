package net.minecraft.client.addon.lavablock.Block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockLavaStairs extends BlockStairs
{
    public BlockLavaStairs(int par1, Block par2Block, int par3)
    {
        super(par1, par2Block, par3);
    }

    public int quantityDropped(Random par1Random)
    {
        return super.blockID > 2554 && super.blockID < 2573 ? 0 : 1;
    }
}

package net.minecraft.client.addon.lavablock.Tile;

import java.util.Random;
import net.minecraft.client.addon.lavablock.Block.BlockGatelock;
import net.minecraft.tileentity.TileEntity;

public class GateTileBlock extends TileEntity
{
    private static final Random random = new Random();
    public boolean isExpl = false;
    float time;

    public GateTileBlock()
    {
        this.time = random.nextFloat() * 200.0F;
    }

    public void updateEntity()
    {
        if (this.isExpl && this.time-- < 1.0F)
        {
            this.isExpl = false;
            BlockGatelock.explodedGateBlock(super.worldObj, super.xCoord, super.yCoord, super.zCoord);
        }
    }
}

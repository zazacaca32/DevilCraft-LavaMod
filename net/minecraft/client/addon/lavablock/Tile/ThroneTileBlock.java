package net.minecraft.client.addon.lavablock.Tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ThroneTileBlock extends TileEntity
{
    public boolean isWorking;
    public int process_status = 0;
    long time = 0L;
    public EntityPlayer klplayer;

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
    }

    public void updateEntity()
    {
        if (!super.worldObj.isRemote && this.isWorking)
        {
            long timeg = System.currentTimeMillis();

            if (timeg > this.time)
            {
                if (this.process_status <= 100)
                {
                    ++this.process_status;
                }

                this.time = timeg + 300L;
            }
        }
    }
}

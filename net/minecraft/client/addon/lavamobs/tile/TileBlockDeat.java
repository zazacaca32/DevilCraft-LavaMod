package net.minecraft.client.addon.lavamobs.tile;

import net.minecraft.client.addon.lavamobs.entity.EntityShadowOfDeath;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileBlockDeat extends TileEntity
{
    private int timer = 200;
    private long time_count = System.currentTimeMillis() + 3600000L;

    public void updateEntity()
    {
        --this.timer;

        if (this.timer <= 0)
        {
            this.timer = 200;

            if (!super.worldObj.isRemote && System.currentTimeMillis() > this.time_count)
            {
                super.worldObj.setBlock(super.xCoord, super.yCoord, super.zCoord, 0);
                EntityShadowOfDeath eentity = new EntityShadowOfDeath(super.worldObj);
                eentity.SetBlockLocate(super.xCoord, super.yCoord, super.zCoord, super.blockMetadata);
                eentity.setLocationAndAngles((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, (float)(90 + super.blockMetadata * 90), 0.0F);
                super.worldObj.spawnEntityInWorld(eentity);
            }
        }
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.time_count = nbttagcompound.getLong("countT");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setLong("countT", this.time_count);
    }
}

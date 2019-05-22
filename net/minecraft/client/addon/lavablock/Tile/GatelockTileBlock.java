package net.minecraft.client.addon.lavablock.Tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class GatelockTileBlock extends TileEntity
{
    public int maxdamage = 30000;
    public int currdamage;
    public boolean flag;

    public GatelockTileBlock()
    {
        this.currdamage = this.maxdamage;
        this.flag = true;
    }

    public boolean canUpdate()
    {
        return false;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.currdamage = nbttagcompound.getInteger("md");
        this.flag = nbttagcompound.getBoolean("flag");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("md", this.currdamage);
        nbttagcompound.setBoolean("flag", this.flag);
    }
}

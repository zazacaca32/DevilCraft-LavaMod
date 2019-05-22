package net.minecraft.client.addon.lavamobs.tile;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.addon.lavamobs.EventHandler;
import net.minecraft.client.addon.lavamobs.entity.EntityRaidBoss;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileBlockRaid extends TileEntity
{
    private int timer = 200;
    private int timersound = 190;
    private boolean timersound2 = true;
    private long time_count = System.currentTimeMillis() + 604800000L;
    public int custom_time = 10079;
    private boolean flag = true;
    private boolean flag2 = true;
    public boolean large = false;

    public void updateEntity()
    {
        --this.timer;

        if (this.timer <= 0)
        {
            this.timer = 200;

            if (!super.worldObj.isRemote)
            {
                if (this.flag && System.currentTimeMillis() > this.time_count - 300000L)
                {
                    this.flag = false;
                    this.large = true;
                    PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
                }

                if (this.flag2 && System.currentTimeMillis() > this.time_count - 10000L)
                {
                    this.flag2 = false;
                    PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 20.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
                }

                this.custom_time = (int)(this.time_count - System.currentTimeMillis()) / 60000;

                if (System.currentTimeMillis() > this.time_count)
                {
                    super.worldObj.setBlock(super.xCoord, super.yCoord, super.zCoord, 0);
                    this.spawnraidboss();
                }
            }
        }

        if (super.worldObj.isRemote)
        {
            --this.timersound;

            if (this.timersound <= 0 && this.large && this.timersound2)
            {
                this.timersound = 190;
                super.worldObj.playSound((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, "lavamobs.blockraid", 1.0F, 1.0F, false);
            }
        }
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.time_count = nbttagcompound.getLong("countT");
        this.large = nbttagcompound.getBoolean("large");
        this.custom_time = nbttagcompound.getInteger("custom_time");
        this.flag2 = nbttagcompound.getBoolean("flag2");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setLong("countT", this.time_count);
        nbttagcompound.setBoolean("large", this.large);
        nbttagcompound.setInteger("custom_time", this.custom_time);
        nbttagcompound.setBoolean("flag2", this.flag2);
    }

    public void spawnraidboss()
    {
        EntityRaidBoss eentity = new EntityRaidBoss(super.worldObj);
        eentity.SetBlockLocate(super.xCoord, super.yCoord, super.zCoord, super.blockMetadata);
        eentity.setLocationAndAngles((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, (float)(90 + super.blockMetadata * 90), 0.0F);
        super.worldObj.spawnEntityInWorld(eentity);
    }

    public Packet getDescriptionPacket()
    {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        Packet132TileEntityData packet = new Packet132TileEntityData(super.xCoord, super.yCoord, super.zCoord, 0, data);
        return packet;
    }

    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        this.readFromNBT(pkt.customParam1);

        if (!this.flag2)
        {
            this.flag2 = true;
            EventHandler.flag = true;
            this.timersound2 = false;
            super.worldObj.playSound((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, "lavamobs.earthquake", 1.0F, 1.0F, false);
        }
    }

    public void UpdateTimeServer(int idInt)
    {
        this.time_count = System.currentTimeMillis() + (long)(idInt * 60000);
    }

    public void UpdateTimeServerL(long idInt)
    {
        this.time_count = idInt;
    }
}

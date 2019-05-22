package net.minecraft.client.addon.lavablock.Tile;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.addon.lavablock.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ElkaTileBlock extends TileEntity
{
    public EntityPlayer nplayer;
    public int count_b = 0;
    public int count_o = 0;
    public int count_p = 0;
    public int time_s = 0;
    private int t = 0;
    public boolean flag_b = false;
    public boolean flag_o = false;
    public boolean flag_p = false;
    private boolean d_flag_b = false;
    private boolean d_flag_o = false;
    private boolean d_flag_p = false;

    public void updateEntity()
    {
        boolean flag_send_isWorking = false;

        if (this.count_p > 49)
        {
            this.flag_p = true;
        }

        if (this.count_o > 99)
        {
            this.flag_o = true;
        }

        if (this.count_b > 299)
        {
            this.flag_b = true;
        }

        if (this.d_flag_b != this.flag_b)
        {
            this.d_flag_b = this.flag_b;
            flag_send_isWorking = true;
        }

        if (this.d_flag_o != this.flag_o)
        {
            this.d_flag_o = this.flag_o;
            flag_send_isWorking = true;
        }

        if (this.d_flag_p != this.flag_p)
        {
            this.d_flag_p = this.flag_p;
            flag_send_isWorking = true;
        }

        if (super.worldObj.isRemote)
        {
            if (this.flag_p && this.flag_o && this.flag_b)
            {
                this.SpawnPar(this.getWorldObj(), super.xCoord, super.yCoord, super.zCoord);
            }
        }
        else
        {
            if (flag_send_isWorking)
            {
                PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
            }

            ++this.t;

            if (this.t % 1200 == 0 && this.flag_p && this.flag_o && this.flag_b)
            {
                if (this.time_s < 600)
                {
                    ++this.time_s;
                    super.worldObj.playSoundEffect((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, "lavablock.newyear", 0.5F, 0.8F);
                }
                else
                {
                    this.setBlockPrez();
                }
            }
        }
    }

    private void setBlockPrez()
    {
        this.time_s = 0;

        if (super.worldObj.isAirBlock(super.xCoord + 1, super.yCoord, super.zCoord + 1))
        {
            super.worldObj.setBlock(super.xCoord + 1, super.yCoord, super.zCoord + 1, ModBlocks.PrizentBlock.blockID);
        }
        else if (super.worldObj.isAirBlock(super.xCoord - 1, super.yCoord, super.zCoord - 1))
        {
            super.worldObj.setBlock(super.xCoord - 1, super.yCoord, super.zCoord - 1, ModBlocks.PrizentBlock.blockID);
        }
        else if (super.worldObj.isAirBlock(super.xCoord - 1, super.yCoord, super.zCoord + 1))
        {
            super.worldObj.setBlock(super.xCoord - 1, super.yCoord, super.zCoord + 1, ModBlocks.PrizentBlock.blockID);
        }
        else if (super.worldObj.isAirBlock(super.xCoord + 1, super.yCoord, super.zCoord - 1))
        {
            super.worldObj.setBlock(super.xCoord + 1, super.yCoord, super.zCoord - 1, ModBlocks.PrizentBlock.blockID);
        }
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        this.count_b = nbttagcompound.getInteger("countB2");
        this.count_o = nbttagcompound.getInteger("countO2");
        this.count_p = nbttagcompound.getInteger("countP2");
        this.time_s = nbttagcompound.getInteger("timeS2");
        this.flag_b = nbttagcompound.getBoolean("flagB2");
        this.flag_o = nbttagcompound.getBoolean("flagO2");
        this.flag_p = nbttagcompound.getBoolean("flagP2");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("countB2", this.count_b);
        nbttagcompound.setInteger("countO2", this.count_o);
        nbttagcompound.setInteger("countP2", this.count_p);
        nbttagcompound.setInteger("timeS2", this.time_s);
        nbttagcompound.setBoolean("flagB2", this.flag_b);
        nbttagcompound.setBoolean("flagO2", this.flag_o);
        nbttagcompound.setBoolean("flagP2", this.flag_p);
    }

    public void SpawnPar(World par1World, int par2, int par3, int par4)
    {
        double d0 = (double)((float)par2 - 0.5F);
        double d1 = (double)((float)par3 + 1.0F);
        double d2 = (double)((float)par4 - 0.5F);
        double d3 = Math.random() * 2.0D;
        double d4 = Math.random() * 2.0D;
        double d5 = Math.random() * 5.0D;
        par1World.spawnParticle("snowshovel", d0 + d3, d1 + d5, d2 + d4, 0.0D, 0.0D, 0.0D);
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
    }

    public void putElka(ItemStack par1ItemStack)
    {
        if (par1ItemStack.getItemDamage() == 0)
        {
            this.count_b += par1ItemStack.stackSize;
        }
        else if (par1ItemStack.getItemDamage() == 1)
        {
            this.count_o += par1ItemStack.stackSize;
        }
        else if (par1ItemStack.getItemDamage() == 2)
        {
            this.count_p += par1ItemStack.stackSize;
        }
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return par1ItemStack.itemID == 2830 && (par1ItemStack.getItemDamage() == 0 || par1ItemStack.itemID == 2830 && (par1ItemStack.getItemDamage() == 1 || par1ItemStack.itemID == 2830 && (par1ItemStack.getItemDamage() == 2 || par1ItemStack.itemID == 2830 && (par1ItemStack.getItemDamage() == 3 || par1ItemStack.hasTagCompound() && ModBlocks.getOrCreateNbtData(par1ItemStack).getByte("nw") == 19))));
    }

    public ItemStack addToInv(ItemStack f)
    {
        if (f.getItemDamage() == 0)
        {
            this.count_b = this.addToInvSlot(f, this.count_b, 300);
        }
        else if (f.getItemDamage() == 1)
        {
            this.count_o = this.addToInvSlot(f, this.count_o, 100);
        }
        else if (f.getItemDamage() == 2)
        {
            this.count_p = this.addToInvSlot(f, this.count_p, 50);
        }
        else if (f.getItemDamage() == 3 && this.count_b < 300 && this.count_o < 100 && this.count_p < 50)
        {
            this.addToInvSlot(f, 1, 2);
            this.count_p = 50;
            this.count_o = 100;
            this.count_b = 300;
        }

        return f;
    }

    public int addToInvSlot(ItemStack f, int stack, int count)
    {
        if (f.stackSize + stack > count)
        {
            f.stackSize = f.stackSize + stack - count;
            return count;
        }
        else
        {
            stack += f.stackSize;
            f.stackSize = 0;
            return stack;
        }
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
    }
}

package net.minecraft.client.addon.lavablock.Tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class ArmorStandTile extends TileEntity implements ISidedInventory
{
    public int rotation = 0;
    public boolean triger = false;
    public ItemStack[] inventoryContents = new ItemStack[5];
    private int[] intt = new int[0];

    public void onInventoryChanged()
    {
        super.onInventoryChanged();

        if (!super.worldObj.isRemote)
        {
            this.triger = true;
        }
    }

    public Packet getDescriptionPacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        this.write(compound);
        return new Packet132TileEntityData(super.xCoord, super.yCoord, super.zCoord, 0, compound);
    }

    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet)
    {
        NBTTagCompound compound = packet.customParam1;
        this.read(compound);
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.read(compound);
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        this.write(compound);
    }

    private void read(NBTTagCompound compound)
    {
        this.rotation = compound.getByte("rotation");
        NBTTagList items = compound.getTagList("Items");

        for (int i = 0; i < items.tagCount(); ++i)
        {
            NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
            int slot = item.getByte("Slot") & 255;

            if (slot >= 0 && slot < this.inventoryContents.length)
            {
                this.inventoryContents[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    private void write(NBTTagCompound compound)
    {
        compound.setByte("rotation", (byte)this.rotation);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < this.inventoryContents.length; ++i)
        {
            NBTTagCompound item;

            if (this.inventoryContents[i] != null)
            {
                item = new NBTTagCompound();
                item.setByte("Slot", (byte)i);
                this.inventoryContents[i].writeToNBT(item);
                list.appendTag(item);
            }
            else
            {
                item = new NBTTagCompound();
                item.setByte("Slot", (byte)i);
                (new ItemStack(0, 0, 0)).writeToNBT(item);
                list.appendTag(item);
            }
        }

        compound.setTag("Items", list);
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
    }

    public int getSizeInventory()
    {
        return this.inventoryContents.length;
    }

    public ItemStack getStackInSlot(int i)
    {
        return this.inventoryContents[i];
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.inventoryContents[par1] != null)
        {
            ItemStack itemstack;

            if (this.inventoryContents[par1].stackSize <= par2)
            {
                itemstack = this.inventoryContents[par1];
                this.inventoryContents[par1] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.inventoryContents[par1].splitStack(par2);

                if (this.inventoryContents[par1].stackSize == 0)
                {
                    this.inventoryContents[par1] = null;
                }

                this.onInventoryChanged();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.inventoryContents[par1] != null)
        {
            ItemStack itemstack = this.inventoryContents[par1];
            this.inventoryContents[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.inventoryContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    public String getInvName()
    {
        return "inv.Armor_Stand";
    }

    public boolean isInvNameLocalized()
    {
        return false;
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }

    public boolean isStackValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }

    public int[] getAccessibleSlotsFromSide(int var1)
    {
        return this.intt;
    }

    public boolean canInsertItem(int i, ItemStack itemstack, int j)
    {
        return false;
    }

    public boolean canExtractItem(int i, ItemStack itemstack, int j)
    {
        return false;
    }
}

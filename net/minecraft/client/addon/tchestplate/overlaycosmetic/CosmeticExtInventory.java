package net.minecraft.client.addon.tchestplate.overlaycosmetic;

import net.minecraft.client.addon.tchestplate.donate.api.IOffert;
import net.minecraft.client.addon.tchestplate.packets.PacketMASendItemStackInSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CosmeticExtInventory implements IInventory
{
    public ItemStack[] inventory = new ItemStack[6];
    public ItemStack[] doubleinventory = new ItemStack[6];
    public ItemStack Coupon;
    public ItemStack[] invOfferts = new ItemStack[4];
    public static CosmeticExtInventory instance;
    public int statusDonateOffert = 0;
    public int coin = 0;
    public EntityPlayer player;
    IOffert offert;

    public CosmeticExtInventory(EntityPlayer player)
    {
        this.player = player;
    }

    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    public boolean isStackInSlot(int slot)
    {
        return this.inventory[slot] != null;
    }

    public ItemStack getStackInSlot(int slot)
    {
        return this.inventory[slot];
    }

    public ItemStack decrStackSize(int slot, int quantity)
    {
        if (this.inventory[slot] != null)
        {
            ItemStack split;

            if (this.inventory[slot].stackSize <= quantity)
            {
                split = this.inventory[slot];
                this.inventory[slot] = null;
                return split;
            }
            else
            {
                split = this.inventory[slot].splitStack(quantity);

                if (this.inventory[slot].stackSize == 0)
                {
                    this.inventory[slot] = null;
                }

                return split;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return null;
    }

    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        this.inventory[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "";
    }

    public boolean isInvNameLocalized()
    {
        return false;
    }

    public int getInventoryStackLimit()
    {
        return 1;
    }

    public void onInventoryChanged()
    {
        if (!this.player.worldObj.isRemote && this.player instanceof EntityPlayerMP)
        {
            for (int i = 0; i < this.getSizeInventory(); ++i)
            {
                if (!ItemStack.areItemStacksEqual(this.inventory[i], this.doubleinventory[i]))
                {
                    this.doubleinventory[i] = ItemStack.copyItemStack(this.inventory[i]);
                    ((EntityPlayerMP)this.player).getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this.player, (new PacketMASendItemStackInSlot(this.player.entityId, (byte)1, (byte)i, new Object[] {this.inventory[i]})).makePacket());
                }
            }
        }
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return true;
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }

    public boolean isStackValidForSlot(int slot, ItemStack itemstack)
    {
        return false;
    }

    public void saveToNBT(NBTTagCompound tags)
    {
        NBTTagList tagList = new NBTTagList();

        for (int var51 = 0; var51 < this.inventory.length; ++var51)
        {
            if (this.inventory[var51] != null)
            {
                NBTTagCompound invSlot = new NBTTagCompound();
                invSlot.setByte("Slots", (byte)var51);
                this.inventory[var51].writeToNBT(invSlot);
                tagList.appendTag(invSlot);
            }
        }

        tags.setTag("CosmExt.Inventory", tagList);
        NBTTagCompound var5 = new NBTTagCompound();

        if (this.Coupon != null)
        {
            this.Coupon.writeToNBT(var5);
        }
    }

    public void readFromNBT(NBTTagCompound tags)
    {
        NBTTagList tagList = tags.getTagList("CosmExt.Inventory");

        for (int var71 = 0; var71 < tagList.tagCount(); ++var71)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)tagList.tagAt(var71);
            int j = nbttagcompound.getByte("Slots") & 255;
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

            if (itemstack != null)
            {
                this.inventory[j] = itemstack;
            }
        }
    }
}

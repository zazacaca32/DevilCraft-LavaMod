package net.minecraft.client.addon.tchestplate.items.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryItemPets implements IInventory
{
    private ItemStack[] stackList = new ItemStack[1];
    private Container eventHandler;

    public InventoryItemPets(Container par1Container)
    {
        this.eventHandler = par1Container;
    }

    public int getSizeInventory()
    {
        return this.stackList.length;
    }

    public ItemStack getStackInSlot(int par1)
    {
        return par1 >= this.getSizeInventory() ? null : this.stackList[par1];
    }

    public String getInvName()
    {
        return "container.itempets";
    }

    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.stackList[par1] != null)
        {
            ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        return null;
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.stackList[par1] = par2ItemStack;
    }

    public int getInventoryStackLimit()
    {
        return 1;
    }

    public void onInventoryChanged()
    {
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }

    public boolean isInvNameLocalized()
    {
        return false;
    }

    public boolean isStackValidForSlot(int i, ItemStack jar)
    {
        return false;
    }
}

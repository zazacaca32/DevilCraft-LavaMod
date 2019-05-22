package net.minecraft.client.addon.lavamobs.tile;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileBlockLavaTotem extends TileEntity implements ISidedInventory
{
    public ItemStack[] inventoryContents;
    private int[] intt;

    public TileBlockLavaTotem()
    {
        this.inventoryContents = new ItemStack[] {new ItemStack(LavaChestPlate.scroll[0], 1, 0), new ItemStack(LavaChestPlate.scroll[1], 1, 0), new ItemStack(LavaChestPlate.scroll[2], 1, 0), new ItemStack(LavaChestPlate.scroll[4], 1, 0), null};
        this.intt = new int[0];
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
        return "inv.Lava_Totem";
    }

    public boolean isInvNameLocalized()
    {
        return false;
    }

    public int getInventoryStackLimit()
    {
        return 1;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false : entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }

    public boolean isStackValidForSlot(int i, ItemStack itemstack)
    {
        return false;
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

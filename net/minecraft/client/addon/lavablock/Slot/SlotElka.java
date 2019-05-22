package net.minecraft.client.addon.lavablock.Slot;

import net.minecraft.client.addon.lavablock.ModBlocks;
import net.minecraft.client.addon.lavablock.Tile.ElkaTileBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotElka extends Slot
{
    public ElkaTileBlock tile;

    public SlotElka(ElkaTileBlock tile, int par2, int par3, int par4)
    {
        super((IInventory)null, par2, par3, par4);
        this.tile = tile;
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return this.tile.isItemValid(par1ItemStack);
    }

    public void putStack(ItemStack par1ItemStack)
    {
        if (!ModBlocks.isClient())
        {
            this.tile.putElka(par1ItemStack);
        }
    }

    public ItemStack decrStackSize(int par1)
    {
        return null;
    }

    public boolean canTakeStack(EntityPlayer par1EntityPlayer)
    {
        return false;
    }

    public void onSlotChanged()
    {
    }

    public boolean isSlotInInventory(IInventory par1IInventory, int par2)
    {
        return false;
    }

    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        this.onSlotChanged();
    }

    public ItemStack getStack()
    {
        return null;
    }

    public int getSlotStackLimit()
    {
        return 64;
    }

    public ItemStack addToInv(ItemStack f)
    {
        return this.tile.addToInv(f);
    }
}

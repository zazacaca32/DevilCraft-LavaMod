package net.minecraft.client.addon.tchestplate.donate.slots;

import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDonateScroll extends Slot
{
    DonateManagerInv inv;
    private int typeOffset;
    private final int realOffset;

    public SlotDonateScroll(IInventory var1, DonateManagerInv var2, int var3, int var4, int var5)
    {
        super((IInventory)null, 0, var4, var5);
        this.inv = var2;
        this.typeOffset = this.realOffset = var3;
    }

    public void setScroll(int var1, int var2)
    {
        this.typeOffset = this.realOffset + var1 * var2;
    }

    public ItemStack getStack()
    {
        return this.inv.getStackInSlot(this.typeOffset);
    }

    public ItemStack getRealStack()
    {
        return this.inv.getFixedStackInSlot(this.typeOffset);
    }

    public void putStack(ItemStack var1)
    {
        this.inv.setInventorySlotContents(this.typeOffset, var1);
        this.onSlotChanged();
    }

    public void onSlotChanged()
    {
        this.inv.onInventoryChanged();
    }

    public int getSlotStackLimit()
    {
        return this.inv.getInventoryStackLimit();
    }

    public ItemStack decrStackSize(int var1)
    {
        System.out.println(this.typeOffset);
        return this.inv.decrStackSize(this.typeOffset, var1);
    }

    public boolean isSlotInInventory(IInventory var1, int var2)
    {
        return false;
    }

    public boolean canTakeStack(EntityPlayer var1)
    {
        return this.inv != null;
    }
}

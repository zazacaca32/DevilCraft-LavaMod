package net.minecraft.client.addon.tchestplate.donate.slots;

import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDonateAdmScroll extends Slot
{
    DonateManagerInv inv;
    private int typeOffset;
    private final int realOffset;

    public SlotDonateAdmScroll(IInventory var1, DonateManagerInv var2, int var3, int var4, int var5)
    {
        super((IInventory)null, 0, var4, var5);
        this.inv = var2;
        this.typeOffset = this.realOffset = var3;
    }

    public void setScroll(int var1, int var2)
    {
        this.typeOffset = this.realOffset + var1 * var2;
    }

    public int getScroll()
    {
        return this.typeOffset;
    }

    public ItemStack getStack()
    {
        return Utils.isClient() ? this.inv.getStackInSlot(this.typeOffset) : null;
    }

    public void putStack(ItemStack var1)
    {
        this.inv.setInventorySlotContents(this.inv.findfirstEmpyslot(), var1);
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

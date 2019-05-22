package net.minecraft.client.addon.tchestplate.donate.slots;

import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDonateAdmConfig extends Slot
{
    DonateManagerInv inv;
    int SlotIndex;

    public SlotDonateAdmConfig(int var1, DonateManagerInv var2, int var3, int var4, int var5)
    {
        super((IInventory)null, var3, var4, var5);
        this.inv = var2;
        this.SlotIndex = var1;
    }

    public ItemStack getStack()
    {
        return this.inv.getStackInSlot(this.SlotIndex);
    }

    public void putStack(ItemStack var1)
    {
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
        return this.inv.decrStackSize(this.SlotIndex, var1);
    }

    public boolean isSlotInInventory(IInventory var1, int var2)
    {
        return false;
    }

    public boolean canTakeStack(EntityPlayer var1)
    {
        return false;
    }
}

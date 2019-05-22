package net.minecraft.client.addon.tchestplate.donate.slots;

import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDonateOffert extends Slot
{
    DonateManagerInv inv;
    public ArmorExtInventory armor;

    public SlotDonateOffert(IInventory var1, DonateManagerInv var2, int var3, int var4, int var5)
    {
        super((IInventory)null, var3, var4, var5);
        this.inv = var2;
        this.armor = (ArmorExtInventory)var1;
    }

    public ItemStack getStack()
    {
        return this.armor.invOfferts[this.getSlotIndex()];
    }

    public void putStack(ItemStack var1)
    {
        this.armor.invOfferts[this.getSlotIndex()] = var1;
    }

    public void onSlotChanged()
    {
    }

    public int getSlotStackLimit()
    {
        return 64;
    }

    public ItemStack decrStackSize(int var1)
    {
        return null;
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

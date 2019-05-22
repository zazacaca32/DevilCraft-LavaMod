package net.minecraft.client.addon.tchestplate.overlaycosmetic;

import net.minecraft.client.addon.tchestplate.slots.BaseSlotInv;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotInvCosmeticExt extends BaseSlotInv
{
    public SlotInvCosmeticExt(BaseSlotInv.Access access, IInventory par1iInventory, int SlotIndex, int X, int Y)
    {
        super(access, par1iInventory, SlotIndex, X, Y);
    }

    public boolean accept(ItemStack stack)
    {
        return stack != null && stack.getItem() != null && stack.getItem() instanceof ICos && ((ICos)stack.getItem()).getSlot() == this.getSlotIndex();
    }
}

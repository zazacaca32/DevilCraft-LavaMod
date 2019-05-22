package net.minecraft.client.addon.tchestplate.slots;

import net.minecraft.client.addon.tchestplate.overlaygui.IArmorExt;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotInvArmorExt extends BaseSlotInv
{
    public SlotInvArmorExt(BaseSlotInv.Access access, IInventory par1iInventory, int SlotIndex, int X, int Y)
    {
        super(access, par1iInventory, SlotIndex, X, Y);
    }

    public boolean accept(ItemStack stack)
    {
        return stack != null && stack.getItem() != null && stack.getItem() instanceof IArmorExt && ((IArmorExt)stack.getItem()).getSlot() == this.getSlotIndex();
    }
}

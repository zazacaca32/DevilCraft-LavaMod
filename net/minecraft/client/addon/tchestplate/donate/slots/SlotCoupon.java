package net.minecraft.client.addon.tchestplate.donate.slots;

import net.minecraft.client.addon.tchestplate.items.ItemDonate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCoupon extends Slot
{
    public SlotCoupon(IInventory var1, int var2, int var3, int var4)
    {
        super(var1, var2, var3, var4);
    }

    public boolean isItemValid(ItemStack var1)
    {
        return var1 != null && var1.getItemDamage() == 0 && var1.getItem() instanceof ItemDonate && ((ItemDonate)var1.getItem()).isValidCoupon(var1);
    }

    public boolean canTakeStack(EntityPlayer var1)
    {
        return true;
    }
}

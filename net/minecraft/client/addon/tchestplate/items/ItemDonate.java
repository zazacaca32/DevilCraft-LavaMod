package net.minecraft.client.addon.tchestplate.items;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.item.ItemStack;

public class ItemDonate extends MultiItemBase
{
    public ItemDonate(int par1, int count)
    {
        super(par1, count);
        this.setMaxStackSize(1);
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    public int getMaxSumm(ItemStack coupon)
    {
        return '썐';
    }

    public int getDiscount(ItemStack coupon)
    {
        return 1;
    }

    public boolean isValidCoupon(ItemStack stack)
    {
        return true;
    }
}

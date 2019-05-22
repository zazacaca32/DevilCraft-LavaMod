package net.minecraft.client.addon.tchestplate.items;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;

public class ItemCrystalPalladium extends MultiItemBase
{
    public ItemCrystalPalladium(int i, int j, int count)
    {
        super(i, count);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }
}

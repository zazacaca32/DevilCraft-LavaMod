package net.minecraft.client.addon.tchestplate.items;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;

public class ItemCrystalCobalt extends MultiItemBase
{
    public ItemCrystalCobalt(int i, int j, int count)
    {
        super(i, count);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }
}

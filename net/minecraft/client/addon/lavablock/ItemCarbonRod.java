package net.minecraft.client.addon.lavablock;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCarbonRod extends Item
{
    public ItemCarbonRod(int par1)
    {
        super(par1);
        this.setUnlocalizedName("carbonrodtem");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }
}

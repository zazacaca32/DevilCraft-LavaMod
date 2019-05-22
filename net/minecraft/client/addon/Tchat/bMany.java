package net.minecraft.client.addon.Tchat;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class bMany extends Item
{
    public bMany(int i, int j)
    {
        super(i);
        super.maxStackSize = j;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("Many:bMany");
    }
}

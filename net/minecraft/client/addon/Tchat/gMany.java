package net.minecraft.client.addon.Tchat;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class gMany extends Item
{
    public gMany(int i, int j)
    {
        super(i);
        super.maxStackSize = j;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("Many:gMany");
    }
}

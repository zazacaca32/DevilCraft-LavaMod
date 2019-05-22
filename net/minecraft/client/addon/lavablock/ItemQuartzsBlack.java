package net.minecraft.client.addon.lavablock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemQuartzsBlack extends Item
{
    public ItemQuartzsBlack(int par1)
    {
        super(par1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("lavablock:" + this.getUnlocalizedName().substring(5));
    }
}

package net.minecraft.client.addon.Tchat;

import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class premiumItem extends Item
{
    public premiumItem(int i, int j)
    {
        super(i);
        super.maxStackSize = j;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("Many:premiumItem");
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§cИсспользовать итем об блок");
        par2List.add("§cУдачи ;3");
    }
}

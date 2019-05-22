package net.minecraft.client.addon.Tchat;

import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DeluxeItem extends Item
{
    public DeluxeItem(int i, int j)
    {
        super(i);
        super.maxStackSize = j;
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("Many:DeluxeItem");
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§aПривелегия дается только при пожертвовании.");
        par2List.add("§aПриобретая с рук вы не получите привелегию.");
    }
}

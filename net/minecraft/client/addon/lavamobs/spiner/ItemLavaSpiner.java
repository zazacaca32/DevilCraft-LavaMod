package net.minecraft.client.addon.lavamobs.spiner;

import net.minecraft.client.addon.lavamobs.MultiItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLavaSpiner extends MultiItem
{
    public ItemLavaSpiner(int par1, int sId)
    {
        super(par1, sId);
        super.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName("lava_spiner");
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 2000;
    }

    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}

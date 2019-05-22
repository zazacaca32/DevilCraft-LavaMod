package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCPBox extends MultiItemBase
{
    public ItemCPBox(int par1, int count)
    {
        super(par1, count);
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (!entityplayer.capabilities.isCreativeMode)
        {
            --itemstack.stackSize;
        }

        entityplayer.dropPlayerItem(new ItemStack(LavaChestPlate.itemCP.itemID, 64, itemstack.getItemDamage()));
        return itemstack;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§8Дропает 64 пузырька с лава энергией");
    }
}

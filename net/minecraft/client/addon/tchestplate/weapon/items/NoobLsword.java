package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class NoobLsword extends Item
{
    public NoobLsword(int par1)
    {
        super(par1);
        this.setMaxDamage(5000);
        this.setMaxStackSize(1);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:nooblsword");
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return 1;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (!player.worldObj.isRemote)
        {
            entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 100);
            return true;
        }
        else
        {
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public int getItemEnchantability()
    {
        return 0;
    }

    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§8 Урон 100!");
    }
}

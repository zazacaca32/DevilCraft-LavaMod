package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BaseStaffWeapon extends BaseEnergyOilItem
{
    public BaseStaffWeapon(int par1, int max, int cooldown, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, max, cooldown, par2EnumToolMaterial);
        this.setMaxDamage(5000);
        this.setMaxStackSize(1);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLiving par7EntityLiving)
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
    public boolean isFull3D()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }
}

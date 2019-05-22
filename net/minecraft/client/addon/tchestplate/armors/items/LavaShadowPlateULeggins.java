package net.minecraft.client.addon.tchestplate.armors.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;

public class LavaShadowPlateULeggins extends LavaChestPlateLeggins implements IShadowPlate
{
    public LavaShadowPlateULeggins(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4, 4001, 1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("underqoder:SLegginsU");
    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "/mods/underqoder/Armors/ShadowU/ShadowU.png";
    }

    public long getShildDamage(EntityPlayer player, ItemStack armor, int damage, int armorslot)
    {
        return this.discharge(armor, damage);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }

    public void regenerateLavaEnergy(ItemStack armor, int energy, int co)
    {
        if (co == 2)
        {
            this.charge(armor, energy);
        }
    }
}

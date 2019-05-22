package net.minecraft.client.addon.tchestplate.accesories.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.armors.items.LavaChestPlateHelmet;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;

public class AccesoriasBant extends LavaChestPlateHelmet
{
    public AccesoriasBant(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:Bant");
    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "/mods/models/accesories/bant.png";
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par2List, par4);
        par2List.add("§7 Украшение для девочек");
    }
}

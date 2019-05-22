package net.minecraft.client.addon.tchestplate.armors.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.util.KeyboardClient;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;

public class NoobPlateHelmet extends LavaChestPlateHelmet
{
    private long time = 0L;

    public NoobPlateHelmet(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4, 1001, 1);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("underqoder:nHelmet");
    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return "/mods/underqoder/Armors/Ultima/POEBOTA.png";
    }

    public long getShildDamage(EntityPlayer player, ItemStack armor, int damage, int armorslot)
    {
        return this.discharge(armor, damage);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        String[] info = this.getInfo(par1ItemStack).split(":");
        par2List.add("§a " + info[0] + "L");
        par2List.add("§7 Можно видеть ночью на " + KeyboardClient.tcNightKey);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }
}

package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.armors.items.IShadowPlate;
import net.minecraft.client.addon.tco.tiny.entity.EntityMan;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTrader;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;

public class ShadowLMacheteU extends Item
{
    public ShadowLMacheteU(int par1)
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
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:ShadowMacheteU");
    }

    public int getDamageVsEntity(Entity par1Entity, ItemStack itemStack)
    {
        return par1Entity instanceof EntityPlayer ? 400 : 800;
    }

    @ForgeSubscribe
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if (entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 400) && !(entity instanceof EntityManTrader) && !(entity instanceof EntityMan))
        {
            ItemStack[] var4 = player.getLastActiveItems();
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; ++var6)
            {
                ItemStack armor = var4[var6];

                if (armor != null && armor.getItem() instanceof IShadowPlate)
                {
                    ((IShadowPlate)armor.getItem()).regenerateLavaEnergy(armor, 40, 2);
                }
            }
        }

        return true;
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
        par2List.add("§8 Наносит урон 400!");
        par2List.add("§8 Возвращает 80 урона в виде энергии");
        par2List.add("§8 Деиствует только на Ультима Сумеречный сет");
    }
}

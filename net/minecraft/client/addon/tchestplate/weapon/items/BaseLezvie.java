package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseLezvie extends Item
{
    public BaseLezvie(int par1)
    {
        super(par1);
        this.setNoRepair();
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    public int getItemEnchantability()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }

    public static short publicEnchantOther(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("oup") ? par1ItemStack.stackTagCompound.getShort("oup") : 0;
    }

    public short getEnchantOther(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("oup") ? par1ItemStack.stackTagCompound.getShort("oup") : 0;
    }

    public boolean incEnchantOther(int inc, ItemStack par1ItemStack)
    {
        Utils.getOrCreateNbtData(par1ItemStack);

        if (par1ItemStack.stackTagCompound == null)
        {
            return false;
        }
        else if (par1ItemStack.stackTagCompound.hasKey("oup"))
        {
            short oup = par1ItemStack.stackTagCompound.getShort("oup");
            oup += (short)inc;
            par1ItemStack.stackTagCompound.setShort("oup", oup);
            return true;
        }
        else
        {
            par1ItemStack.stackTagCompound.setShort("oup", (short)inc);
            return true;
        }
    }

    public int dCDemonLezvie(Entity e, ItemStack i)
    {
        short oup = this.getEnchantOther(i);
        return oup == 0 ? (e instanceof EntityPlayer ? 900 : 1200) : (oup == 2 ? (e instanceof EntityPlayer ? 1200 : 1500) : (oup == 4 ? (e instanceof EntityPlayer ? 1500 : 1900) : (oup == 8 ? (e instanceof EntityPlayer ? 1900 : 2400) : (oup != 16 && oup <= 16 ? 1 : (e instanceof EntityPlayer ? 2400 : 2700)))));
    }

    public int dCUlezvie(Entity e, ItemStack i)
    {
        short oup = this.getEnchantOther(i);
        return oup == 0 ? (e instanceof EntityPlayer ? 850 : 1100) : (oup == 2 ? (e instanceof EntityPlayer ? 1000 : 1200) : (oup == 4 ? (e instanceof EntityPlayer ? 1200 : 1400) : (oup == 8 ? (e instanceof EntityPlayer ? 1400 : 1600) : (oup != 16 && oup <= 16 ? 1 : (e instanceof EntityPlayer ? 1800 : 2000)))));
    }

    public int dChanterLezvie(Entity e, ItemStack i)
    {
        short oup = this.getEnchantOther(i);
        return oup == 0 ? (e instanceof EntityPlayer ? 800 : 1000) : (oup == 2 ? (e instanceof EntityPlayer ? 1000 : 1200) : (oup == 4 ? (e instanceof EntityPlayer ? 1300 : 1500) : (oup == 8 ? (e instanceof EntityPlayer ? 1600 : 1800) : (oup != 16 && oup <= 16 ? 1 : (e instanceof EntityPlayer ? 1900 : 2100)))));
    }

    public int dCLavaLukNew(Entity e, ItemStack i)
    {
        short oup = this.getEnchantOther(i);
        return oup == 0 ? (e instanceof EntityPlayer ? 1000 : 1000) : (oup == 2 ? (e instanceof EntityPlayer ? 1200 : 1200) : (oup == 4 ? (e instanceof EntityPlayer ? 1400 : 1400) : (oup == 8 ? (e instanceof EntityPlayer ? 1600 : 1600) : (oup != 16 && oup <= 16 ? 1 : (e instanceof EntityPlayer ? 1800 : 1800)))));
    }
}

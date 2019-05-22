package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class BaseHammerWeapon extends BaseWeapon
{
    public BaseHammerWeapon(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        this.setMaxDamage(5000);
        this.setMaxStackSize(1);
        super.maxCharge = Integer.valueOf(30);
        this.setCreativeTab(LavaChestPlate.tabArmor);
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§a " + super.maxCharge + "/" + this.getCharge(par1ItemStack) + " L");
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return super.maxCharge.intValue() > this.getCharge(stack).intValue();
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 5000;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2)
    {
        return false;
    }

    ItemStack CreateItem(ItemStack itemstack)
    {
        return itemstack;
    }

    public short getEnchantHammer(ItemStack par1ItemStack)
    {
        return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("hup") ? par1ItemStack.stackTagCompound.getShort("hup") : 0;
    }

    public boolean incEnchantHammer(int inc, ItemStack par1ItemStack)
    {
        Utils.getOrCreateNbtData(par1ItemStack);

        if (par1ItemStack.stackTagCompound == null)
        {
            return false;
        }
        else if (par1ItemStack.stackTagCompound.hasKey("hup"))
        {
            short up = par1ItemStack.stackTagCompound.getShort("hup");
            up += (short)inc;
            par1ItemStack.stackTagCompound.setShort("hup", up);
            return true;
        }
        else
        {
            par1ItemStack.stackTagCompound.setShort("hup", (short)inc);
            return true;
        }
    }
}

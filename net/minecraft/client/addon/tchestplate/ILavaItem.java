package net.minecraft.client.addon.tchestplate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface ILavaItem
{
    Integer getMaxCharge(NBTTagCompound var1);

    Integer getCharge(ItemStack var1);

    double getAbsorbation();

    Integer charge(ItemStack var1, int var2);

    long discharge(ItemStack var1, int var2);

    long getShildDamage(EntityPlayer var1, ItemStack var2, int var3, int var4);
}

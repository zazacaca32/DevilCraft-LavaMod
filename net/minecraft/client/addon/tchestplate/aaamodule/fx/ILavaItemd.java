package net.minecraft.client.addon.tchestplate.aaamodule.fx;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract interface ILavaItemd
{
  public abstract Integer getMaxCharge(NBTTagCompound paramNBTTagCompound); 
  
  public abstract Integer getCharge(ItemStack paramItemStack);
  
  public abstract Integer charge(ItemStack paramItemStack, int paramInt);
  
  public abstract double getAbsorbation();
}

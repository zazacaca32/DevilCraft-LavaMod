package net.minecraft.client.addon.tchestplate.items;

import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModelPlayer;

public interface IRenderItemChestPlateExt extends IRenderItemChestPlate
{
    void doRender(ModelPlayer var1, EntityPlayer var2, float var3, float var4, ExtendedPlayer var5, float var6, float var7, float var8, float var9, ItemStack var10);
}

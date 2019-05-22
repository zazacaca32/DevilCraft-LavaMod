package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IUseItem_
{
    void onUsingItemTick1(ItemStack var1, ExtendedPlayer var2, int var3, Side var4);

    void onPlayerStoppedUsing1(ItemStack var1, World var2, ExtendedPlayer var3, int var4, Side var5);
}

package net.minecraft.client.addon.tchestplate.armors.items;

import net.minecraft.item.ItemStack;

public interface IPredatorPlate
{
    short min = 100;
    short maxInvise = 300;

    short getMode(ItemStack var1);

    void setMode(ItemStack var1, int var2);

    short getChargeInvise(ItemStack var1);
}

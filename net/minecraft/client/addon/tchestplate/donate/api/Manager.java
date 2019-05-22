package net.minecraft.client.addon.tchestplate.donate.api;

import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;

public class Manager
{
    public static IDonateUser getDonateUser(EntityPlayer player)
    {
        return ExtendedPlayer.get(player).inventoryExt;
    }
}

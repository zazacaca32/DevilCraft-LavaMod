package net.minecraft.client.addon.tchestplate.donate.api;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IDonateUser
{
    @SideOnly(Side.SERVER)
    boolean startOffert(int var1, IOffert var2);

    @SideOnly(Side.SERVER)
    boolean startOffertTraderCoin(String var1, int var2, IOffertTraderCoin var3);

    @SideOnly(Side.SERVER)
    int setCoin(int var1);

    int getCoin();

    @SideOnly(Side.SERVER)
    void refrechCoin();

    int getStatus();

    void setStatus(int var1);
}

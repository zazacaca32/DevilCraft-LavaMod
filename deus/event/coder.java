package deus.event;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface coder
{
    @SideOnly(Side.SERVER)
    int setCoin(int var1);

    int getCoin();

    @SideOnly(Side.SERVER)
    void refrechCoin();

    int getStatus();

    void setStatus(int var1);
}

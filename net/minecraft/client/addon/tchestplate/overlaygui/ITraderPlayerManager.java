package net.minecraft.client.addon.tchestplate.overlaygui;

import net.minecraft.client.gui.ScaledResolution;

public interface ITraderPlayerManager
{
    int getshowSendMemu();

    void setshowSendMemu(int var1);

    int getplayer2_id();

    void setplayer2_id(int var1);

    boolean getisActive();

    void setisActive(boolean var1);

    void onUpdate(long var1);

    void handleUpdateBytesPacket(byte var1, byte[] var2);

    Object getshowmenu();

    void renderOverlay(ScaledResolution var1);
}

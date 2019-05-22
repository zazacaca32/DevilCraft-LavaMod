package net.minecraft.client.addon.tchestplate.overlaygui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.gui.ScaledResolution;

public class TraderPlayerManager implements ITraderPlayerManager
{
    ExtendedPlayer extendedPlayer;
    private boolean isActive = false;
    private byte showSendMemu = 0;
    long lasttick = 0L;
    public byte timeBack = 0;
    @SideOnly(Side.CLIENT)
    public TraderPlayerManager.ShowMenu showmenu;
    private int player2_id = -1;

    public TraderPlayerManager(ExtendedPlayer extendedPlayer)
    {
        this.extendedPlayer = extendedPlayer;
    }

    public boolean isActive()
    {
        return this.isActive;
    }

    @SideOnly(Side.CLIENT)
    public void renderOverlay(ScaledResolution scaledResolution)
    {
    }

    public int getshowSendMemu()
    {
        return this.showSendMemu;
    }

    public void setshowSendMemu(int n)
    {
        this.showSendMemu = (byte)n;
    }

    public int getplayer2_id()
    {
        return this.player2_id;
    }

    public void setplayer2_id(int player2_id)
    {
        this.player2_id = player2_id;
    }

    public boolean getisActive()
    {
        return this.isActive;
    }

    public void setisActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public Object getshowmenu()
    {
        return null;
    }

    public void onUpdate(long p0)
    {
    }

    public void handleUpdateBytesPacket(byte p0, byte[] p1)
    {
    }

    @SideOnly(Side.CLIENT)
    public static class ShowMenu
    {
        final String pos1;
        final String pos2;
        public final String player2;
        public final int player2_id;
        int time;

        public ShowMenu(String s, String s2, int n)
        {
            this(s, s2, (String)null, -1, n);
        }

        public ShowMenu(String pos1, String pos2, String player2, int player2_id, int time)
        {
            this.pos1 = pos1;
            this.pos2 = pos2;
            this.player2 = player2;
            this.time = time;
            this.player2_id = player2_id;
        }
    }
}

package net.minecraft.client.addon.tchestplate.fx.manager;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class DeusK extends FX_base
{
    int ticks = 0;
    int intervalMills = 2;

    public DeusK()
    {
    }

    public DeusK(int Milliseconds, byte duration)
    {
        super(Milliseconds, duration);
    }

    public void ds(Entity pointedEntity)
    {
    }

    public void Initialization(ExtendedPlayer Epl, EntityPlayer player, Side side)
    {
    }

    public void Update(ExtendedPlayer Epl, EntityPlayer player, Side side)
    {
        if (side == Side.CLIENT && this.ticks++ % this.intervalMills == 0)
        {
            this.ds(player);
        }
    }

    public void Finalization(ExtendedPlayer Epl, EntityPlayer player, Side side)
    {
    }

    public byte getIDeffect()
    {
        return (byte)4;
    }
}

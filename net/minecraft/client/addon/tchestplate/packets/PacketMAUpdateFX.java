package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.fx.manager.FX_base;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAUpdateFX extends PacketMA
{
    private byte idEffect;
    private int EntityID;
    private int timemills;
    private byte duration;

    public PacketMAUpdateFX(byte idEffect, int EntityID, int millis, byte duration)
    {
        this.EntityID = EntityID;
        this.idEffect = idEffect;
        this.timemills = millis;
        this.duration = duration;
    }

    public PacketMAUpdateFX()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeByte(this.idEffect);
        out.writeInt(this.EntityID);
        out.writeInt(this.timemills);
        out.writeByte(this.duration);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.idEffect = in.readByte();
        this.EntityID = in.readInt();
        this.timemills = in.readInt();
        this.duration = in.readByte();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isClient())
        {
            try
            {
                Entity var51 = player.worldObj.getEntityByID(this.EntityID);

                if (var51 != null && var51 instanceof EntityPlayer)
                {
                    FX_base f = ManagerFX.getFXfromID(this.idEffect);
                    f.setMilliseconds(this.timemills);
                    f.setDuration(this.duration);
                    ExtendedPlayer.get((EntityPlayer)var51).addEffect(f);
                }
            }
            catch (Exception var5)
            {
                System.out.println("PacketMAUpdateFX->" + var5.getMessage());
            }
        }
    }
}

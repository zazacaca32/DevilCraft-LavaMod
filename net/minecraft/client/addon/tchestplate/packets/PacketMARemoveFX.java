package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMARemoveFX extends PacketMA
{
    private byte idEffect;
    private int EntityID;

    public PacketMARemoveFX(byte idEffect, int EntityID)
    {
        this.EntityID = EntityID;
        this.idEffect = idEffect;
    }

    public PacketMARemoveFX()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeByte(this.idEffect);
        out.writeInt(this.EntityID);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.idEffect = in.readByte();
        this.EntityID = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isClient())
        {
            try
            {
                Entity var41 = player.worldObj.getEntityByID(this.EntityID);

                if (var41 != null && var41 instanceof EntityPlayer)
                {
                    ExtendedPlayer.get((EntityPlayer)var41).removeEffect(this.idEffect);
                }
            }
            catch (Exception var4)
            {
                System.out.println("PacketMARemoveFX->" + var4.getMessage());
            }
        }
    }
}

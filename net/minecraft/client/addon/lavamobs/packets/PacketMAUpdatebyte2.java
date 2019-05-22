package net.minecraft.client.addon.lavamobs.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAUpdatebyte2 extends PacketMA
{
    byte idevent;
    byte idmob;
    short mobcount;

    public PacketMAUpdatebyte2(byte idevent, byte idmob, short mobcount)
    {
        this.idevent = idevent;
        this.idmob = idmob;
        this.mobcount = mobcount;
    }

    public PacketMAUpdatebyte2()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeByte(this.idevent);
        out.writeByte(this.idmob);
        out.writeShort(this.mobcount);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.idevent = in.readByte();
        this.idmob = in.readByte();
        this.mobcount = in.readShort();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (!side.isServer())
        {
            try
            {
                ExtendedPlayer Epl = ExtendedPlayer.get(player);
                Epl.idevent = this.idevent;
                Epl.idmob = this.idmob;
                Epl.mobcount = this.mobcount;
            }
            catch (Exception var4)
            {
                ;
            }
        }
    }
}

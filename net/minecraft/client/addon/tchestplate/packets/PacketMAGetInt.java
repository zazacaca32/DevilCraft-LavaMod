package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAGetInt extends PacketMA
{
    private int num;

    public PacketMAGetInt(int num)
    {
        this.num = num;
    }

    public PacketMAGetInt()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeInt(this.num);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.num = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (!side.isClient())
        {
            throw new PacketMA.ProtocolException("Cannot send INTEGER this packet to the server!");
        }
    }
}

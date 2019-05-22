package net.minecraft.client.addon.Tchat.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.Tchat.lavabow;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAparticle extends PacketMA
{
    private double x;
    private double y;
    private double z;
    private int ID;
    private int counter;

    public PacketMAparticle(int iD, double x, double y, double z, int counter)
    {
        this.ID = iD;
        this.x = x;
        this.y = y;
        this.z = z;
        this.counter = counter;
    }

    public PacketMAparticle()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeInt(this.ID);
        out.writeDouble(this.x);
        out.writeDouble(this.y);
        out.writeDouble(this.z);
        out.writeInt(this.counter);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.ID = in.readInt();
        this.x = in.readDouble();
        this.y = in.readDouble();
        this.z = in.readDouble();
        this.counter = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isClient())
        {
            lavabow.proxy.runSpawnParticle(player.worldObj, this.x, this.y, this.z, this.ID, this.counter);
        }
        else
        {
            throw new PacketMA.ProtocolException("Cannot send this packet to the server!");
        }
    }
}

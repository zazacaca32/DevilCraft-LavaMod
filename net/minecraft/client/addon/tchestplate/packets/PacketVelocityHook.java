package net.minecraft.client.addon.tchestplate.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet28EntityVelocity;

public class PacketVelocityHook extends Packet28EntityVelocity
{
    public PacketVelocityHook()
    {
    }

    public PacketVelocityHook(Entity par1Entity)
    {
        this(par1Entity.entityId, par1Entity.motionX, par1Entity.motionY, par1Entity.motionZ);
    }

    public PacketVelocityHook(int par1, double par2, double par4, double par6)
    {
        super.entityId = par1;
        double d3 = 3.9D;

        if (par2 < -d3)
        {
            par2 = -d3;
        }

        if (par4 < -d3)
        {
            par4 = -d3;
        }

        if (par6 < -d3)
        {
            par6 = -d3;
        }

        if (par2 > d3)
        {
            par2 = d3;
        }

        if (par4 > d3)
        {
            par4 = d3;
        }

        if (par6 > d3)
        {
            par6 = d3;
        }

        super.motionX = (int)(par2 * 8000.0D);
        super.motionY = (int)(par4 * 8000.0D);
        super.motionZ = (int)(par6 * 8000.0D);
    }

    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        super.entityId = par1DataInputStream.readInt();
        super.motionX = par1DataInputStream.readShort();
        super.motionY = par1DataInputStream.readShort();
        super.motionZ = par1DataInputStream.readShort();
    }

    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(super.entityId);
        par1DataOutputStream.writeShort(super.motionX);
        par1DataOutputStream.writeShort(super.motionY);
        par1DataOutputStream.writeShort(super.motionZ);
    }

    public void processPacket(NetHandler par1NetHandler)
    {
        if (!ClientProxy.isShock)
        {
            par1NetHandler.handleEntityVelocity(this);
        }
    }

    public int getPacketSize()
    {
        return 10;
    }

    public boolean isRealPacket()
    {
        return true;
    }

    public boolean containsSameEntityIDAs(Packet par1Packet)
    {
        Packet28EntityVelocity packet28entityvelocity = (Packet28EntityVelocity)par1Packet;
        return packet28entityvelocity.entityId == super.entityId;
    }
}

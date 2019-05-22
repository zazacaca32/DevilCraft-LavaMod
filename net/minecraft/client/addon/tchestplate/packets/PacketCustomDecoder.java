package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.entity.player.EntityPlayer;

public class PacketCustomDecoder extends PacketMA
{
    public static float pizda = 0.0F;
    public static float pizda2 = 0.0F;
    public static float pizda3 = 0.0F;

    public PacketCustomDecoder(float num, float num1, float num2)
    {
        pizda = num;
        pizda2 = num1;
        pizda3 = num2;
    }

    public PacketCustomDecoder()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeFloat(pizda);
        out.writeFloat(pizda2);
        out.writeFloat(pizda3);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        DataInputStream ins = new DataInputStream((InputStream)in);

        try
        {
            pizda = ins.readFloat();
            pizda2 = ins.readFloat();
            pizda3 = ins.readFloat();
            System.out.println("readed");
        }
        catch (IOException var4)
        {
            var4.printStackTrace();
            System.out.println("notreaded");
        }
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (!side.isClient())
        {
            throw new PacketMA.ProtocolException("Cannot send INTEGER this packet to the server!");
        }
    }
}

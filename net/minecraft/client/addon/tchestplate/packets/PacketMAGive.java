package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAGive extends PacketMA
{
    private int ID;

    public PacketMAGive(int ID)
    {
        this.ID = ID;
    }

    public PacketMAGive()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeInt(this.ID);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.ID = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isClient())
        {
            try
            {
                Minecraft mc = Minecraft.getMinecraft();
                WorldClient var4 = mc.theWorld;
            }
            catch (Exception var5)
            {
                ;
            }
        }
        else
        {
            throw new PacketMA.ProtocolException("Cannot send this packet to the server!");
        }
    }
}

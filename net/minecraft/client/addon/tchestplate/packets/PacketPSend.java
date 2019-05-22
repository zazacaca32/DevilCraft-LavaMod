package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class PacketPSend extends PacketMA
{
    private int EFFECTID;

    public PacketPSend(int ID)
    {
        this.EFFECTID = ID;
    }

    public PacketPSend()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeInt(this.EFFECTID);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.EFFECTID = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isClient())
        {
            try
            {
                ExtendedPlayer eplayer = new ExtendedPlayer(player);
                World w = player.worldObj;
                LavaChestPlate.proxy.spawnparts(w, eplayer, this.EFFECTID);
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

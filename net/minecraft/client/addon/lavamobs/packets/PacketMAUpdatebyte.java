package net.minecraft.client.addon.lavamobs.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAUpdatebyte extends PacketMA
{
    private int entityId;

    public PacketMAUpdatebyte()
    {
    }

    public PacketMAUpdatebyte(Entity par1Entity)
    {
        this.entityId = par1Entity.entityId;
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeInt(this.entityId);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.entityId = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        LavaModMobs.proxy.handleupdatebyte(this.entityId);
    }
}

package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

public abstract class PacketMA
{
    public static final String CHANNEL = "tc";
    private static final BiMap idMap;

    public static PacketMA constructPacket(int packetId) throws PacketMA.ProtocolException, IllegalAccessException, InstantiationException
    {
        Class clazz = (Class)idMap.get(Integer.valueOf(packetId));

        if (clazz == null)
        {
            throw new PacketMA.ProtocolException("Unknown Packet Id!");
        }
        else
        {
            return (PacketMA)clazz.newInstance();
        }
    }

    public final int getPacketId()
    {
        if (idMap.inverse().containsKey(this.getClass()))
        {
            return ((Integer)idMap.inverse().get(this.getClass())).intValue();
        }
        else
        {
            throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " is missing a mapping!");
        }
    }

    public final Packet makePacket()
    {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeByte(this.getPacketId());
        this.write(out);
        return PacketDispatcher.getPacket("tc", out.toByteArray());
    }

    protected abstract void write(ByteArrayDataOutput var1);

    protected abstract void read(ByteArrayDataInput var1) throws PacketMA.ProtocolException;

    protected abstract void execute(EntityPlayer var1, Side var2) throws PacketMA.ProtocolException;

    static
    {
        Builder builder = ImmutableBiMap.builder();
        builder.put(Integer.valueOf(1), PacketMAparticle.class);
        builder.put(Integer.valueOf(2), PacketMASwingUseItem.class);
        builder.put(Integer.valueOf(3), PacketMARemoveFX.class);
        builder.put(Integer.valueOf(4), PacketMAUpdateFX.class);
        builder.put(Integer.valueOf(5), PacketMAOpenGui.class);
        builder.put(Integer.valueOf(6), PacketMASendItemStackInSlot.class);
        builder.put(Integer.valueOf(7), PacketMASendItemStackInSlotDonate.class);
        builder.put(Integer.valueOf(8), PacketMAUpdateInt.class);
        builder.put(Integer.valueOf(9), PacketMASendItemStacksDonateBuy.class);
        builder.put(Integer.valueOf(10), PacketMAUpdateByte.class);
        builder.put(Integer.valueOf(11), PacketMAGetInt.class);
        builder.put(Integer.valueOf(12), PacketPSend.class);
        builder.put(Integer.valueOf(13), PacketMAGive.class);
        builder.put(Integer.valueOf(14), PacketInvisible.class);
        builder.put(Integer.valueOf(15), PacketClickSlotDonate.class);
        builder.put(Integer.valueOf(16), PacketCustomDecoder.class);
        idMap = builder.build();
    }

    public static class ProtocolException extends Exception
    {
        public ProtocolException()
        {
        }

        public ProtocolException(String message, Throwable cause)
        {
            super(message, cause);
        }

        public ProtocolException(String message)
        {
            super(message);
        }

        public ProtocolException(Throwable cause)
        {
            super(cause);
        }
    }
}

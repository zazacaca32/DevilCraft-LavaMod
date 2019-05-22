package net.minecraft.client.addon.lavamobs.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import java.util.logging.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandlerMA implements IPacketHandler
{
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        try
        {
            EntityPlayer var10 = (EntityPlayer)player;
            ByteArrayDataInput in = ByteStreams.newDataInput((byte[])packet.data);
            int packetId = in.readUnsignedByte();
            PacketMA demoPacket = PacketMA.constructPacket(packetId);
            demoPacket.read(in);
            demoPacket.execute(var10, var10.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
        }
        catch (PacketMA.ProtocolException var8)
        {
            if (player instanceof EntityPlayerMP)
            {
                ((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
                Logger.getLogger("DemoMod").warning("Player " + ((EntityPlayer)player).username + " caused a Protocol Exception!");
            }
        }
        catch (InstantiationException var9)
        {
            throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var9);
        }
        catch (IllegalAccessException var101)
        {
            throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var101);
        }
    }
}

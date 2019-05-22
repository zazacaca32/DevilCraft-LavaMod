package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import java.util.logging.Logger;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandlerMA {

   public static void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
      try {
         EntityPlayer var91 = (EntityPlayer)player;
         ByteArrayDataInput in = ByteStreams.newDataInput((byte[])packet.data);
         byte packetId = in.readByte();
         PacketMA demoPacket = PacketMA.constructPacket(packetId);
         demoPacket.length(packet.data.length - 1);
         demoPacket.read(in);
         demoPacket.execute(var91, Utils.isClient()?Side.CLIENT:Side.SERVER);
      } catch (PacketMA.ProtocolException var7) {
         if(player instanceof EntityPlayerMP) {
            ((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
            Logger.getLogger("DemoMod111").warning("Player " + ((EntityPlayer)player).username + " caused a Protocol Exception!");
         }
      } catch (InstantiationException var8) {
         throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var8);
      } catch (IllegalAccessException var9) {
         throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var9);
      }

   }
}

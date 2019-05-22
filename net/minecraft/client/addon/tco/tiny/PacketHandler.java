package net.minecraft.client.addon.tco.tiny;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketHandlerMA;
import net.minecraft.client.addon.tco.tiny.packets.old.Packet1Icon;
import net.minecraft.client.addon.tco.tiny.packets.old.Packet3VoteClan;
import net.minecraft.client.addon.tco.tiny.packets.old.Packet4HollyDay;
import net.minecraft.client.addon.tco.tiny.packets.old.Packet9priz;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler {

   Side side = FMLCommonHandler.instance().getEffectiveSide();


   public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
      try {
         if("ll".equals(packet.channel)) {
            PacketHandlerMA.onPacketData(manager, packet, player);
            return;
         }

         if(!"l".equals(packet.channel)) {
            return;
         }

         if(this.side == Side.SERVER) {
            return;
         }

         ByteArrayDataInput var91 = ByteStreams.newDataInput((byte[])packet.data);
         switch(var91.readByte()) {
         case 1:
            FMLLog.getLogger().info("Flag = 1");
            Packet1Icon pac43 = new Packet1Icon();
            pac43.Read(var91);
         case 2:
         case 5:
         case 6:
         case 7:
         case 8:
         default:
            break;
         case 3:
            Packet3VoteClan pac42 = new Packet3VoteClan();
            pac42.Read(var91);
            pac42.ExecuteRead();
            break;
         case 4:
            Packet4HollyDay pac41 = new Packet4HollyDay();
            pac41.Read(var91);
            pac41.ExecuteRead();
            break;
         case 9:
            Packet9priz pac4 = new Packet9priz();
            pac4.Read(var91);
            pac4.ExecuteRead();
         }
      } catch (Exception var9) {
         FMLLog.getLogger().info("Errorpllavapacket= " + player.toString() + " " + var9.getMessage());
      }

   }
}

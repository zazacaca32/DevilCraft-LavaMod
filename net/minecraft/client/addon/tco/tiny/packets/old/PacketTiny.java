package net.minecraft.client.addon.tco.tiny.packets.old;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.addon.tco.tiny.packets.old.PacketTinyAbstract;
import net.minecraft.network.packet.Packet250CustomPayload;

public abstract class PacketTiny extends PacketTinyAbstract {

   ByteArrayDataOutput out = ByteStreams.newDataOutput();


   protected void Write() {
      Packet250CustomPayload packet = new Packet250CustomPayload();
      packet.channel = "l";
      packet.data = this.out.toByteArray();
      packet.length = this.out.toByteArray().length;
      PacketDispatcher.sendPacketToServer(packet);
   }
}

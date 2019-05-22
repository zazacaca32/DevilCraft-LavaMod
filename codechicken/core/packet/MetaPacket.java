package codechicken.core.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

public class MetaPacket extends Packet {

   public ArrayList packets = new ArrayList();


   public MetaPacket(Packet ... packets) {
      Packet[] var5 = packets;
      int var4 = packets.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Packet p = var5[var3];
         this.packets.add(p);
      }

   }

   public MetaPacket(Collection packets) {
      this.packets.addAll(packets);
   }

   public void readPacketData(DataInputStream datain) {
      throw new IllegalStateException("Meta packets can\'t be read");
   }

   public void writePacketData(DataOutputStream dataout) throws IOException {
      Iterator var3 = this.packets.iterator();

      while(var3.hasNext()) {
         Packet p = (Packet)var3.next();
         p.writePacketData(dataout);
      }

   }

   public void processPacket(NetHandler nethandler) {
      Iterator var3 = this.packets.iterator();

      while(var3.hasNext()) {
         Packet p = (Packet)var3.next();
         p.processPacket(nethandler);
      }

   }

   public int getPacketSize() {
      int size = 0;

      Packet p;
      for(Iterator var3 = this.packets.iterator(); var3.hasNext(); size += p.getPacketSize()) {
         p = (Packet)var3.next();
      }

      return size;
   }
}

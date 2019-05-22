package net.minecraft.client.addon.tco.tiny.packets;

import com.google.common.io.ByteArrayDataInput;
import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.packets.PacketTiny;
import net.minecraft.client.addon.tco.tiny.playermod.Flag;
import net.minecraft.client.addon.tco.tiny.playermod.PlayerLogic;

public class Packet1Icon extends PacketTiny {

   public void Read(ByteArrayDataInput in) {
      ArrayList list = new ArrayList(50);
      boolean asDel = true;
      int var4 = 0;

      while(var4++ < 300) {
         list.clear();
         asDel = true;
         byte var8 = in.readByte();
         if(var8 == 51) {
            break;
         }

         String OutputPlayer = in.readUTF();

         byte by;
         for(int i = 0; i < 50 && (by = in.readByte()) != 52; ++i) {
            list.add(Flag.values()[by]);
         }

         if(var8 == 0) {
            PlayerLogic.addToMapFlags(OutputPlayer, (Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])list.toArray(new Flag[0])))))))))));
         } else if(var8 == 1) {
            PlayerLogic.removeMapFlags(OutputPlayer, (Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])((Flag[])list.toArray(new Flag[0])))))))))));
         } else if(var8 == 2) {
            PlayerLogic.removePlayer(OutputPlayer);
         }
      }

      list = null;
   }

   public void ExecuteRead() {}
}

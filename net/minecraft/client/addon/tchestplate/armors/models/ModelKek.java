package net.minecraft.client.addon.tchestplate.armors.models;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class ModelKek implements IPacketHandler {
   public static ModelKek xyina;
   public static float pizda = 0.0F;
   public static float pizda2 = 0.0F;
   public static float pizda3 = 0.0F;

   public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
      if(packet.channel.equals("modeldecoder")) {
         ByteArrayInputStream input = new ByteArrayInputStream(packet.data);
         DataInputStream ins = new DataInputStream(input);

         try {
            pizda = Float.valueOf((float)ins.readInt()).floatValue();
            pizda2 = Float.valueOf((float)ins.readInt()).floatValue();
            pizda3 = Float.valueOf((float)ins.readInt()).floatValue();
            System.out.println("readed");
         } catch (IOException var7) {
            var7.printStackTrace();
            System.out.println("notreaded");
         }
      }

   }
}

package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.Utils.DarkItem;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerDarkController;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class PacketMAUpdateTileWindow extends PacketMA {

   private int windowId;
   private ArrayList itemstacks;
   private long energy;


   public PacketMAUpdateTileWindow(int windowId, ArrayList itemstacks, long energy) {
      this.windowId = windowId;
      this.itemstacks = itemstacks;
      this.energy = energy;
   }

   public PacketMAUpdateTileWindow() {}

   protected void write(ByteArrayDataOutput out) {
      out.writeInt(this.windowId);
      out.writeLong(this.energy);
      out.writeByte(this.itemstacks.size());

      for(int i = 0; i < this.itemstacks.size(); ++i) {
         ((DarkItem)this.itemstacks.get(i)).write(out);
      }

   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      this.windowId = in.readInt();
      this.energy = in.readLong();
      byte len = in.readByte();
      this.itemstacks = new ArrayList(len);

      for(int i = 0; i < len; ++i) {
         DarkItem it = new DarkItem();
         it.read(in);
         this.itemstacks.add(it);
      }

   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      if(side.isClient()) {
         try {
            EntityClientPlayerMP entityclientplayermp = Minecraft.getMinecraft().thePlayer;
            if(this.windowId == entityclientplayermp.openContainer.windowId) {
               Container con = entityclientplayermp.openContainer;
               if(entityclientplayermp.openContainer instanceof ContainerDarkController) {
                  ((ContainerDarkController)con).readPacket(this.itemstacks, this.energy);
               }
            }
         } catch (Exception var5) {
            ;
         }
      }

   }

   protected void length(int length) {}
}

package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class PacketLAControllerMonitor extends PacketMA {

   public byte[] data;
   private int len;


   public PacketLAControllerMonitor() {}

   public PacketLAControllerMonitor(byte[] data) {
      this.data = data;
   }

   protected void write(ByteArrayDataOutput out) {
      out.write(this.data);
   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      in.readFully(this.data = new byte[this.len]);
   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      if(side.isServer()) {
         EntityPlayerMP c211111 = (EntityPlayerMP)player;
         Container nmec = c211111.openContainer;
         if(nmec != null && nmec instanceof ContainerTraderControllerMonitor) {
            DataInputStream stream = new DataInputStream(new ByteArrayInputStream(this.data));

            try {
               int s = stream.readInt();
               ((ContainerTraderControllerMonitor)nmec).tileEntity.triggerUpdateContent = true;
               ((ContainerTraderControllerMonitor)nmec).tileEntity.playerOnline = c211111;
            } catch (Exception var7) {
               ;
            }
         }
      } else {
         ContainerTraderControllerMonitor c2111111 = this.getContainer();
         if(c2111111 != null && c2111111 instanceof ContainerTraderControllerMonitor) {
            c2111111.tileEntity.readPacket(this);
            c2111111.update();
         }
      }

   }

   public ContainerTraderControllerMonitor getContainer() {
      GuiScreen gs = FMLClientHandler.instance().getClient().currentScreen;
      return gs != null && gs instanceof GuiTraderControllerMonitor?(ContainerTraderControllerMonitor)((GuiTraderControllerMonitor)gs).inventorySlots:null;
   }

   protected void length(int length) {
      this.len = length;
   }
}

package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderClose;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTraderOpen;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.gui.LAGui;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class PacketLAInvFakeUP64 extends PacketMA {

   public byte[] data;
   private int len;


   public PacketLAInvFakeUP64() {}

   public PacketLAInvFakeUP64(byte[] data) {
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
         EntityPlayerMP c311111 = (EntityPlayerMP)player;
         Container c2 = c311111.openContainer;
         if(c2 != null && c2 instanceof MEContainerBase) {
            DataInputStream stream = new DataInputStream(new ByteArrayInputStream(this.data));

            try {
               int var71 = stream.readInt();
               stream.readUTF();
            } catch (Exception var8) {
               ;
            }

            try {
               PacketDispatcher.sendPacketToPlayer(((MEContainerBase)c2).GetNetworkIME().getDataPacketLA(), (Player)player);
            } catch (IOException var7) {
               var7.printStackTrace();
            }
         }
      } else {
         MEContainerBase c3111111 = this.getContainer();
         if(c3111111 != null) {
            if(c3111111 instanceof ContainerTraderOpen) {
               ContainerTraderOpen c21 = (ContainerTraderOpen)c3111111;
            } else if(c3111111 instanceof ContainerTraderClose) {
               ContainerTraderClose c22 = (ContainerTraderClose)c3111111;
            }
         }
      }

   }

   public MEContainerBase getContainer() {
      GuiScreen gs = FMLClientHandler.instance().getClient().currentScreen;
      return gs != null && gs instanceof LAGui?((LAGui)gs).gci:null;
   }

   protected void length(int length) {
      this.len = length;
   }
}

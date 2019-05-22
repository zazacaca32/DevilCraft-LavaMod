package net.minecraft.client.addon.tco.tiny;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.client.addon.tco.tiny.Utils.ILANetworkInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.METileEntityBase;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketLAInv;
import net.minecraft.network.packet.Packet;

public class NetworkedIMEI implements ILANetworkInventory {

   boolean isValid = false;
   public METileEntityBase realInv;
   public METileEntityBase machine;
   public Player targetPlayer;
   LAItemStack[] list1;


   public boolean checkValid() {
      return true;
   }

   void addPacket(PacketLAInv p) {
      try {
         ByteArrayInputStream bytes = new ByteArrayInputStream(p.data);
         DataInputStream data = new DataInputStream(bytes);
         this.isValid = data.readBoolean();
         byte items = data.readByte();
         if(this.isValid && items > 0) {
            DataInputStream idata = new DataInputStream(new GZIPInputStream(data));

            for(int x = 0; x < items; ++x) {
               byte slot = idata.readByte();
               boolean flag = idata.readBoolean();
               this.list1[slot] = flag?LAItemStack.loadItemStackFromPacket(idata):null;
            }
         }
      } catch (IOException var9) {
         ;
      }

   }

   public void readPacket(PacketLAInv p) {
      this.addPacket(p);
   }

   public Packet getRequestPacket() throws IOException {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      DataOutputStream data = new DataOutputStream(bytes);

      try {
         data.writeInt(1);
         data.writeUTF("1");
      } catch (IOException var4) {
         ;
      }

      return (new PacketLAInv(bytes.toByteArray())).makePacket();
   }

   public Packet getDataPacketLA() throws IOException {
      LAInternalInventory realCell = this.realInv.provideCell();
      if(realCell == null) {
         return null;
      } else {
         LAItemStack[] ll = realCell.getAvaleableLA();
         ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         DataOutputStream data = new DataOutputStream(bytes);

         try {
            data.writeBoolean(this.isValid = true);
            data.writeByte(ll.length);
            if(ll.length > 0) {
               GZIPOutputStream var9 = new GZIPOutputStream(bytes);
               DataOutputStream var10 = new DataOutputStream(var9);

               for(int x = 0; x < ll.length; ++x) {
                  var10.writeByte(x);
                  if(ll[x] == null) {
                     var10.writeBoolean(false);
                  } else {
                     var10.writeBoolean(true);
                     ll[x].writeToPacket(var10);
                  }
               }

               var9.close();
            }
         } catch (IOException var8) {
            ;
         }

         byte[] var91 = bytes.toByteArray();
         Packet var101 = (new PacketLAInv(Arrays.copyOf(var91, var91.length))).makePacket();
         var101.isChunkDataPacket = false;
         return var101;
      }
   }

   public LAInternalInventory provideCell() {
      return !this.checkValid()?null:(this.realInv != null?this.realInv.provideCell():null);
   }

   @SideOnly(Side.CLIENT)
   public LAItemStack[] getAvailableItemsLA() {
      LAItemStack[] is = new LAItemStack[this.realInv.provideCell().getSize()];
      if(this.list1 != null) {
         is = (LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])Arrays.copyOf(this.list1, this.list1.length)))))))));
      }

      return is;
   }

   public void setMETileBase(METileEntityBase te) {
      this.realInv = te;
      this.list1 = new LAItemStack[te.provideCell().getSize()];
   }

   public LAItemStack getStackInSlot(int var1) {
      return null;
   }

   public void setInventorySlotContents(int invSlot, LAItemStack current) {}

   public METileEntityBase getMETileBase() {
      return null;
   }
}

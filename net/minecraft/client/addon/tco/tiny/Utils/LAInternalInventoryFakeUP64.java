package net.minecraft.client.addon.tco.tiny.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraft.client.addon.tco.tiny.Utils.ILANetworkInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.METileEntityBase;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketLAInv;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;

public class LAInternalInventoryFakeUP64 implements ILANetworkInventory {

   int maxStack = 64;
   int size;
   private METileEntityBase te;
   private LAItemStack[] inv;


   public LAInternalInventoryFakeUP64(METileEntityBase te, int size) {
      this.size = size;
      this.inv = new LAItemStack[size];
      this.te = te;
   }

   public int getSize() {
      return this.size;
   }

   public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
      for(int x = 0; x < this.size; ++x) {
         NBTTagCompound c = new NBTTagCompound();
         if(this.inv[x] != null) {
            this.inv[x].writeToNBT(c);
         }

         par1nbtTagCompound.setCompoundTag("#" + x, c);
      }

   }

   public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
      for(int x = 0; x < this.size; ++x) {
         NBTTagCompound c = par1nbtTagCompound.getCompoundTag("#" + x);
         if(c != null) {
            this.inv[x] = LAItemStack.loadItemStackFromNBT(c);
         }
      }

   }

   public synchronized LAItemStack[] getAvaleableLA() {
      LAItemStack[] is = null;
      if(this.inv != null) {
         is = new LAItemStack[this.size];
         is = (LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])Arrays.copyOf(this.inv, this.inv.length))))))));
      }

      return is;
   }

   public LAItemStack getStackInSlot(int var1) {
      return this.inv[var1];
   }

   public void setInventorySlotContents(int invSlot, LAItemStack current) {
      this.inv[invSlot] = current;
   }

   public Packet getDataPacketLA() {
      LAItemStack[] ll = this.getAvaleableLA();
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      DataOutputStream data = new DataOutputStream(bytes);

      try {
         data.writeBoolean(true);
         data.writeByte(ll.length);
         if(ll.length > 0) {
            GZIPOutputStream var8 = new GZIPOutputStream(bytes);
            DataOutputStream var9 = new DataOutputStream(var8);

            for(int x = 0; x < ll.length; ++x) {
               var9.writeByte(x);
               if(ll[x] == null) {
                  var9.writeBoolean(false);
               } else {
                  var9.writeBoolean(true);
                  ll[x].writeToPacket(var9);
               }
            }

            var8.close();
         }
      } catch (IOException var7) {
         ;
      }

      byte[] var81 = bytes.toByteArray();
      Packet var91 = (new PacketLAInv(Arrays.copyOf(var81, var81.length))).makePacket();
      var91.isChunkDataPacket = false;
      return var91;
   }

   void addPacket(PacketLAInv packetLAInvFakeUP64) {
      try {
         ByteArrayInputStream bytes = new ByteArrayInputStream(packetLAInvFakeUP64.data);
         DataInputStream data = new DataInputStream(bytes);
         data.readBoolean();
         byte items = data.readByte();
         if(items > 0) {
            DataInputStream idata = new DataInputStream(new GZIPInputStream(data));

            for(int x = 0; x < items; ++x) {
               byte slot = idata.readByte();
               boolean flag = idata.readBoolean();
               if(flag) {
                  LAItemStack aeis = LAItemStack.loadItemStackFromPacket(idata);
                  this.inv[slot] = aeis;
               } else {
                  this.inv[slot] = null;
               }
            }
         }
      } catch (IOException var10) {
         ;
      }

   }

   public void readPacket(PacketLAInv packetLAInv) {
      this.addPacket(packetLAInv);
   }

   public METileEntityBase getMETileBase() {
      return this.te;
   }

   public void setMETileBase(METileEntityBase te) {
      this.te = te;
   }
}

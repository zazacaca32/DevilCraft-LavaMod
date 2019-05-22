package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.Utils.DataEncoder;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketLAControllerMonitor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockTraderControllerMonitor extends TileEntity {

   public List listclient = new ArrayList();
   public boolean triggerUpdateContent = false;
   public int conX;
   public int conY;
   public int conZ;
   public HashSet items = new HashSet();
   public EntityPlayer playerOnline;
   private int facing;


   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
      super.readFromNBT(tagCompound);
      this.conX = tagCompound.getInteger("cnX");
      this.conY = tagCompound.getInteger("cnY");
      this.conZ = tagCompound.getInteger("cnZ");
      this.facing = tagCompound.getInteger("facing");
   }

   public void writeToNBT(NBTTagCompound tagCompound) {
      super.writeToNBT(tagCompound);
      tagCompound.setInteger("cnX", this.conX);
      tagCompound.setInteger("cnY", this.conY);
      tagCompound.setInteger("cnZ", this.conZ);
      tagCompound.setInteger("facing", this.facing);
   }

   public void updateEntity() {
      boolean flag = false;
      if(!super.worldObj.isRemote && this.triggerUpdateContent) {
         this.triggerUpdateContent = false;
         if(this.conX == 0 && this.conY == 0 && this.conZ == 0) {
            return;
         }

         TileEntity te = super.worldObj.getBlockTileEntity(this.conX, this.conY, this.conZ);
         if(te != null && te instanceof TileEntityBlockTraderController) {
            this.items.clear();
            ((TileEntityBlockTraderController)te).updateMonitor(this);
         }
      }

   }

   public Packet getRequestPacket() throws IOException {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      DataOutputStream data = new DataOutputStream(bytes);

      try {
         data.writeInt(1);
      } catch (IOException var4) {
         ;
      }

      return (new PacketLAControllerMonitor(bytes.toByteArray())).makePacket();
   }

   public void readPacket(PacketLAControllerMonitor packetLAControllerMonitor) {
      ByteArrayDataInput in = ByteStreams.newDataInput(packetLAControllerMonitor.data);
      byte dataCount = in.readByte();
      this.listclient.clear();

      for(int i = 0; i < dataCount; ++i) {
         try {
            this.listclient.add(((LAItemStack)DataEncoder.decode(in)).getItemStack());
         } catch (IOException var6) {
            ;
         }
      }

   }

   public void placedByGetController(int xCoord, int yCoord, int zCoord) {
      this.conX = xCoord;
      this.conY = yCoord;
      this.conZ = zCoord;
   }

   public void addMapItemTrader(LAItemStack itemStackLowIdDamage) {
      this.items.add(itemStackLowIdDamage);
   }

   public void endMonitorUpdate(List list) {
      ArrayList result = new ArrayList();
      LAItemStack[] itemsl = (LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])this.items.toArray(new LAItemStack[0])))))))));
      LAItemStack[] out = itemsl;
      int byteData = itemsl.length;
      int var121 = 0;

      Iterator var13;
      LAItemStack var15;
      while(var121 < byteData) {
         LAItemStack var131 = out[var121];
         var13 = list.iterator();

         while(true) {
            if(!var13.hasNext()) {
               result.add(var131);
            } else {
               var15 = (LAItemStack)var13.next();
               if(var15 == null || !var15.eq(var131) || var131 == null || var15.getStackSize() < var131.getStackSize()) {
                  continue;
               }
            }

            ++var121;
            break;
         }
      }

      if(!result.isEmpty()) {
         ByteArrayDataOutput var12 = ByteStreams.newDataOutput();

         try {
            var12.writeByte(result.size());
            var13 = result.iterator();

            while(var13.hasNext()) {
               var15 = (LAItemStack)var13.next();
               DataEncoder.encode(var12, var15);
            }
         } catch (IOException var11) {
            ;
         }

         byte[] var131 = var12.toByteArray();
         out = null;
         Packet var1321 = (new PacketLAControllerMonitor(Arrays.copyOf(var131, var131.length))).makePacket();
         var1321.isChunkDataPacket = false;
         if(this.playerOnline != null) {
            PacketDispatcher.sendPacketToPlayer(var1321, (Player)this.playerOnline);
         }
      }

   }

   public int getFacing() {
      return this.facing;
   }

   public void setFcing(int facing) {
      this.facing = facing;
   }

   public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
      int oldfacing = this.facing;
      this.readFromNBT(pkt.customParam1);
      if(this.facing != oldfacing) {
         super.worldObj.markBlockForRenderUpdate(super.xCoord, super.yCoord, super.zCoord);
      }

   }

   public Packet getDescriptionPacket() {
      NBTTagCompound data = new NBTTagCompound();
      this.writeToNBT(data);
      Packet132TileEntityData packet = new Packet132TileEntityData(super.xCoord, super.yCoord, super.zCoord, 0, data);
      return packet;
   }
}

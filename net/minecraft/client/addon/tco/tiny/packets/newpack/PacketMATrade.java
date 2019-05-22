package net.minecraft.client.addon.tco.tiny.packets.newpack;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrade;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class PacketMATrade extends PacketMA {

   private int DestiDPlayer;
   private int x;
   private int y;
   private int z;
   private byte status;
   private byte PlayerOneStatus;
   private byte PlayerTwoStatus;
   private byte PlayerOnecheckStatus;
   private byte PlayerTwocheckStatus;
   private byte time = 0;


   public PacketMATrade(int x, int y, int z, int DestiDPlayer, byte status, byte time, byte PlayerOneStatus, byte PlayerTwoStatus, byte PlayerOnecheckStatus, byte PlayerTwocheckStatus) {
      this.DestiDPlayer = DestiDPlayer;
      this.x = x;
      this.y = y;
      this.z = z;
      this.status = status;
      this.time = time;
      this.PlayerOneStatus = PlayerOneStatus;
      this.PlayerTwoStatus = PlayerTwoStatus;
      this.PlayerOnecheckStatus = PlayerOnecheckStatus;
      this.PlayerTwocheckStatus = PlayerTwocheckStatus;
   }

   public PacketMATrade(int x, int y, int z, byte status) {
      this.DestiDPlayer = 0;
      this.x = x;
      this.y = y;
      this.z = z;
      this.status = status;
      this.PlayerOneStatus = 0;
      this.PlayerTwoStatus = 0;
      this.PlayerOnecheckStatus = 0;
      this.PlayerTwocheckStatus = 0;
   }

   public PacketMATrade() {}

   protected void write(ByteArrayDataOutput out) {
      out.writeInt(this.x);
      out.writeInt(this.y);
      out.writeInt(this.z);
      out.writeInt(this.DestiDPlayer);
      out.writeByte(this.status);
      out.writeByte(this.time);
      out.writeByte(this.PlayerOneStatus);
      out.writeByte(this.PlayerTwoStatus);
      out.writeByte(this.PlayerOnecheckStatus);
      out.writeByte(this.PlayerTwocheckStatus);
   }

   protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException {
      this.x = in.readInt();
      this.y = in.readInt();
      this.z = in.readInt();
      this.DestiDPlayer = in.readInt();
      this.status = in.readByte();
      this.time = in.readByte();
      this.PlayerOneStatus = in.readByte();
      this.PlayerTwoStatus = in.readByte();
      this.PlayerOnecheckStatus = in.readByte();
      this.PlayerTwocheckStatus = in.readByte();
   }

   protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException {
      try {
         TileEntity tile = player.worldObj.getBlockTileEntity(this.x, this.y, this.z);
         if(tile instanceof TileEntityBlockTrade) {
            TileEntityBlockTrade t = (TileEntityBlockTrade)tile;
            if(!side.isClient()) {
               int TileCurrStatus = t.getStatus(player.entityId);
               int TileDestStatus = t.getStatus(t.getDescPlayerID(player.entityId));
               if(t.PlayerOneID != -1 && t.PlayerTwoID != -1) {
                  if(TileCurrStatus != -1 && TileDestStatus != -1) {
                     switch(this.status) {
                     case 1:
                        if(TileDestStatus == 0) {
                           t.setStatus(player.entityId, 1);
                           t.setCheckStatus(player.entityId, 1);
                           t.setCheckStatus(t.getDescPlayerID(player.entityId), 1);
                           t.tradeTimeOut = 0;
                           return;
                        }

                        if(TileDestStatus == 1) {
                           t.setStatus(player.entityId, 1);
                           t.setCheckStatus(player.entityId, 1);
                           t.tradeTimeOut = 0;
                        } else {
                           t.setStatus(player.entityId, 0);
                           t.setStatus(t.getDescPlayerID(player.entityId), 0);
                           t.setCheckStatus(player.entityId, 0);
                           t.setCheckStatus(t.getDescPlayerID(player.entityId), 0);
                           t.tradeTimeOut = 0;
                        }

                        return;
                     case 2:
                        if(TileDestStatus == 1) {
                           t.setStatus(player.entityId, 2);
                           t.setStatus(t.getDescPlayerID(player.entityId), 2);
                           t.setCheckStatus(player.entityId, 2);
                           t.setCheckStatus(t.getDescPlayerID(player.entityId), 2);
                           t.tradeTimeOut = 0;
                        }

                        return;
                     case 10:
                        if(t.getCheckStatus(player.entityId) < 3 && t.getCheckStatus(t.getDescPlayerID(player.entityId)) < 3) {
                           t.setStatus(player.entityId, 0);
                           t.setStatus(t.getDescPlayerID(player.entityId), 0);
                           t.setCheckStatus(player.entityId, 0);
                           t.setCheckStatus(t.getDescPlayerID(player.entityId), 0);
                           t.tradeTimeOut = 0;
                        }

                        return;
                     default:
                        return;
                     }
                  }

                  return;
               }

               return;
            }

            t.destPlayerID = this.DestiDPlayer;
            t.PlayerStatus = this.status;
            t.PlayerOneStatus = this.PlayerOneStatus;
            t.PlayerTwoStatus = this.PlayerTwoStatus;
            t.tradeTimeOut = this.time;
            t.PlayerOnecheckStatus = this.PlayerOnecheckStatus;
            t.PlayerTwocheckStatus = this.PlayerTwocheckStatus;
         }
      } catch (Exception var7) {
         ;
      }

   }

   protected void length(int length) {}
}

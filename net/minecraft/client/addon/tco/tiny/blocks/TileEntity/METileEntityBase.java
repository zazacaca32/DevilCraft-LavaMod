package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventory;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.containers.MEContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

public abstract class METileEntityBase extends BaseTileEntityBlock {

   boolean sendUpdate = false;
   protected byte tickUpdateCount = 0;
   boolean updateContainers = false;


   public void triggerContainerUpdate() {
      this.updateContainers = true;
   }

   public void triggerBlockUpdate() {
      this.sendUpdate = true;
   }

   public final void updateEntity() {
      try {
         super.updateEntity();
         if(!this.isInvalid()) {
            if(this.updateContainers) {
               this.updateContainers = false;
               MinecraftServer var6 = FMLCommonHandler.instance().getMinecraftServerInstance();
               if(var6 != null) {
                  List PlayerList = var6.getConfigurationManager().playerEntityList;
                  if(PlayerList != null) {
                     for(int o = 0; o < PlayerList.size(); ++o) {
                        EntityPlayerMP p = (EntityPlayerMP)PlayerList.get(o);
                        if(p.dimension == super.worldObj.provider.dimensionId && p.openContainer != null && p.openContainer instanceof MEContainerBase) {
                           METileEntityBase tc = ((MEContainerBase)p.openContainer).getTile();
                           if(tc != null && tc == this) {
                              ((MEContainerBase)p.openContainer).triggerUpdate();
                           }
                        }
                     }
                  }
               }

               this.afterContainerUpdate();
            }

            if(this.tickUpdateCount > 0) {
               --this.tickUpdateCount;
            } else if(this.sendUpdate) {
               this.sendUpdate = false;
               PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
               this.tickUpdateCount = (byte)(this.tickUpdateCount + 100);
            }

            this.updateTileEntity();
         }

      } catch (Throwable var61) {
         FMLLog.severe("Exception When Ticking " + this.getClass().getName() + " at " + super.xCoord + ", " + super.yCoord + ", " + super.zCoord, new Object[0]);
         var61.printStackTrace();
         FMLLog.severe("MC might crash now...", new Object[0]);
         throw new RuntimeException(var61);
      }
   }

   public void updateTileEntity() {}

   private void afterContainerUpdate() {}

   public boolean isCommonUseableByPlayer(EntityPlayer v) {
      return false;
   }

   public void clientNotifyBlockOfNeighborChange(int x, int y, int z) {
      TileEntity te = super.worldObj.getBlockTileEntity(x, y, z);
      if(te != null && te instanceof METileEntityBase) {
         ((METileEntityBase)te).updateClientTile();
      }

   }

   public void updateClientTile() {}

   public boolean isUseableByPlayer(EntityPlayer player) {
      return false;
   }

   public boolean isJob() {
      return false;
   }

   public abstract LAInternalInventory provideCell();
}

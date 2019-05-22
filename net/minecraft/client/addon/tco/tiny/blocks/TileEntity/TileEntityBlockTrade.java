package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTrade;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerTrade;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMATrade;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBlockTrade extends BaseTileEntityBlock implements IInventory {

   private ItemStack[] inv = new ItemStack[12];
   public int PlayerOneID = -1;
   public long PlayerOneTimeOut = 0L;
   public byte PlayerOnecheckStatus = 0;
   public int PlayerTwoID = -1;
   public long PlayerTwoTimeOut = 0L;
   public byte PlayerTwocheckStatus = 0;
   public int PlayerStatus = 0;
   public int destPlayerID = 0;
   public int tradeTimeOut = 0;
   long ticksSinceSync = 0L;
   int numUsingPlayers = 0;
   int PlayerOneIDDouble = 0;
   int PlayerTwoIDDouble = 0;
   byte PlayerOneStatusDouble = 0;
   byte PlayerTwoStatusDouble = 0;
   byte PlayerTwotradeTimeOut = 0;
   byte PlayerOneStatuscheckDouble = 0;
   byte PlayerTwoStatuscheckDouble = 0;
   public byte PlayerOneStatus = 0;
   public byte PlayerTwoStatus = 0;


   public Boolean _switch(Integer NumSlot, int u) {
      return Boolean.valueOf(u == 1?(this.PlayerOnecheckStatus == 0?NumSlot.intValue() <= 5:(this.PlayerOnecheckStatus == 1?false:(this.PlayerOnecheckStatus == 2?false:(this.PlayerOnecheckStatus == 3?NumSlot.intValue() > 5:false)))):(u == 2?(this.PlayerTwocheckStatus == 0?NumSlot.intValue() > 5:(this.PlayerTwocheckStatus == 1?false:(this.PlayerTwocheckStatus == 2?false:(this.PlayerTwocheckStatus == 3?NumSlot.intValue() <= 5:false)))):false));
   }

   public void updateEntity() {
      boolean flag = false;
      if(!super.worldObj.isRemote) {
         ;
      }

      long sysTime = System.currentTimeMillis();
      if(!super.worldObj.isRemote && sysTime > this.ticksSinceSync) {
         this.ticksSinceSync = sysTime + 1000L;
         if(this.PlayerOneID > 0 || this.PlayerTwoID > 0) {
            long t = System.currentTimeMillis();
            float f = 5.0F;
            List list = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)super.xCoord - f), (double)((float)super.yCoord - f), (double)((float)super.zCoord - f), (double)((float)(super.xCoord + 1) + f), (double)((float)(super.yCoord + 1) + f), (double)((float)(super.zCoord + 1) + f)));
            Iterator iterator = list.iterator();
            boolean fl = false;

            while(iterator.hasNext()) {
               EntityPlayer entityplayer = (EntityPlayer)iterator.next();
               IInventory iinventory;
               if(entityplayer.openContainer instanceof ContainerTrade && (iinventory = ((ContainerTrade)entityplayer.openContainer).getTileInventory()) == this) {
                  if(this.PlayerOneID == entityplayer.entityId) {
                     this.PlayerOneTimeOut = System.currentTimeMillis() + 20000L;
                     flag = true;
                     fl = true;
                  }

                  if(this.PlayerTwoID == entityplayer.entityId) {
                     this.PlayerTwoTimeOut = System.currentTimeMillis() + 20000L;
                     flag = true;
                     fl = true;
                  }

                  if(fl) {
                     iinventory.onInventoryChanged();
                  }
               }
            }

            if(this.PlayerOneTimeOut < t) {
               this.PlayerOneID = -1;
               this.PlayerOneStatus = 0;
               this.PlayerTwoStatus = 0;
               this.PlayerOnecheckStatus = 0;
               this.PlayerTwocheckStatus = 0;
               flag = true;
            }

            if(this.PlayerTwoTimeOut < t) {
               this.PlayerTwoID = -1;
               this.PlayerOneStatus = 0;
               this.PlayerTwoStatus = 0;
               this.PlayerOnecheckStatus = 0;
               this.PlayerTwocheckStatus = 0;
               flag = true;
            }
         }

         if(this.PlayerOnecheckStatus == 1 && this.PlayerTwocheckStatus == 1) {
            if(this.tradeTimeOut-- < 1) {
               this.tradeTimeOut = 20;
            } else if(this.tradeTimeOut == 0) {
               this.PlayerOnecheckStatus = 0;
               this.PlayerTwocheckStatus = 0;
               this.PlayerOneStatus = 0;
               this.PlayerTwoStatus = 0;
               flag = true;
            }
         } else if(this.PlayerOnecheckStatus == 2 && this.PlayerTwocheckStatus == 2) {
            if(this.tradeTimeOut-- < 1) {
               this.tradeTimeOut = 20;
            } else if(this.tradeTimeOut == 0) {
               this.PlayerOnecheckStatus = 3;
               this.PlayerTwocheckStatus = 3;
               flag = true;
            }
         } else if(this.PlayerOnecheckStatus == 3 && this.PlayerTwocheckStatus == 3) {
            if(this.tradeTimeOut-- < 1) {
               this.tradeTimeOut = 20;
            } else if(this.tradeTimeOut == 0) {
               this.PlayerOnecheckStatus = 0;
               this.PlayerTwocheckStatus = 0;
               this.PlayerOneStatus = 0;
               this.PlayerTwoStatus = 0;
               flag = true;
            }
         }

         if(flag) {
            this.newSendToClient(false);
         }
      }

      super.updateEntity();
   }

   public int getStatus(int PlayerId) {
      return PlayerId == this.PlayerOneID?this.PlayerOneStatus:(PlayerId == this.PlayerTwoID?this.PlayerTwoStatus:-1);
   }

   public void setStatus(int PlayerId, int status) {
      if(PlayerId == this.PlayerOneID) {
         this.PlayerOneStatus = (byte)status;
      }

      if(PlayerId == this.PlayerTwoID) {
         this.PlayerTwoStatus = (byte)status;
      }

   }

   public int getCheckStatus(int PlayerId) {
      return PlayerId == this.PlayerOneID?this.PlayerOnecheckStatus:(PlayerId == this.PlayerTwoID?this.PlayerTwocheckStatus:-1);
   }

   public void setCheckStatus(int PlayerId, int status) {
      if(PlayerId == this.PlayerOneID) {
         this.PlayerOnecheckStatus = (byte)status;
      }

      if(PlayerId == this.PlayerTwoID) {
         this.PlayerTwocheckStatus = (byte)status;
      }

   }

   public int CheckChest(EntityPlayer par5EntityPlayer) {
      return this.PlayerOneID == -1 && this.PlayerTwoID == -1?1:(this.PlayerTwoID != par5EntityPlayer.entityId && this.PlayerOneID == -1?1:(this.PlayerTwoID == -1 && this.PlayerOneID != par5EntityPlayer.entityId?2:(this.PlayerOneID == par5EntityPlayer.entityId?1:(this.PlayerTwoID == par5EntityPlayer.entityId?2:0))));
   }

   public void UpdateClientAndServer() {
      PacketDispatcher.sendPacketToAllAround((double)super.xCoord, (double)super.yCoord, (double)super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
   }

   public int openChest(EntityPlayer player) {
      int u = this.CheckChest(player);
      if(u == 1) {
         this.PlayerOneID = player.entityId;
         if(this.PlayerTwoID > 0) {
            this.sendToClient(true, new Entity[]{super.worldObj.getEntityByID(this.PlayerTwoID), player});
         } else {
            this.sendToClient(true, new Entity[]{player});
         }

         this.UpdateClientAndServer();
         super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.getBlockType().blockID);
      } else if(u == 2) {
         this.PlayerTwoID = player.entityId;
         if(this.PlayerOneID > 0) {
            this.sendToClient(true, new Entity[]{super.worldObj.getEntityByID(this.PlayerOneID), player});
         } else {
            this.sendToClient(true, new Entity[]{player});
         }

         this.UpdateClientAndServer();
         super.worldObj.notifyBlocksOfNeighborChange(super.xCoord, super.yCoord, super.zCoord, this.getBlockType().blockID);
      }

      return u;
   }

   public void closeChest(EntityPlayer par1EntityPlayer) {
      if(this.getBlockType() != null && this.getBlockType() instanceof BlockTrade) {
         if(this.PlayerOneID == par1EntityPlayer.entityId) {
            this.PlayerOneTimeOut = System.currentTimeMillis() + 20000L;
         }

         if(this.PlayerTwoID == par1EntityPlayer.entityId) {
            this.PlayerTwoTimeOut = System.currentTimeMillis() + 20000L;
         }

         this.UpdateClientAndServer();
      }

   }

   public int getDescPlayerID(int PlayerId) {
      return PlayerId == this.PlayerOneID?this.PlayerTwoID:(PlayerId == this.PlayerTwoID?this.PlayerOneID:-1);
   }

   public void sendToClient(boolean force, Entity ... p) {
      int DestEntityid2;
      Entity[] var4;
      int var5;
      int var6;
      Entity pl;
      if(force) {
         var4 = p;
         var5 = p.length;

         for(var6 = 0; var6 < var5; ++var6) {
            pl = var4[var6];

            try {
               if(pl instanceof EntityPlayer) {
                  DestEntityid2 = ((EntityPlayer)pl).entityId;
                  PacketDispatcher.sendPacketToPlayer((new PacketMATrade(super.xCoord, super.yCoord, super.zCoord, this.getDescPlayerID(DestEntityid2), (byte)this.getCheckStatus(DestEntityid2), (byte)this.tradeTimeOut, this.PlayerOneStatus, this.PlayerTwoStatus, this.PlayerOnecheckStatus, this.PlayerTwocheckStatus)).makePacket(), (Player)pl);
               }
            } catch (Exception var10) {
               ;
            }
         }
      } else if(this.PlayerOneIDDouble != this.PlayerOneID || this.PlayerTwoIDDouble != this.PlayerTwoID || this.PlayerOneStatusDouble != this.PlayerOneStatus || this.PlayerTwoStatusDouble != this.PlayerTwoStatus || this.PlayerTwotradeTimeOut != this.tradeTimeOut || this.PlayerOneStatuscheckDouble != this.PlayerOnecheckStatus || this.PlayerTwoStatuscheckDouble != this.PlayerTwocheckStatus) {
         this.PlayerOneIDDouble = this.PlayerOneID;
         this.PlayerTwoIDDouble = this.PlayerTwoID;
         this.PlayerOneStatusDouble = this.PlayerOneStatus;
         this.PlayerTwoStatusDouble = this.PlayerTwoStatus;
         this.PlayerOneStatuscheckDouble = this.PlayerOnecheckStatus;
         this.PlayerTwoStatuscheckDouble = this.PlayerTwocheckStatus;
         var4 = p;
         var5 = p.length;

         for(var6 = 0; var6 < var5; ++var6) {
            pl = var4[var6];

            try {
               if(pl instanceof EntityPlayer) {
                  DestEntityid2 = ((EntityPlayer)pl).entityId;
                  PacketDispatcher.sendPacketToPlayer((new PacketMATrade(super.xCoord, super.yCoord, super.zCoord, this.getDescPlayerID(DestEntityid2), (byte)this.getCheckStatus(DestEntityid2), (byte)this.tradeTimeOut, this.PlayerOneStatus, this.PlayerTwoStatus, this.PlayerOnecheckStatus, this.PlayerTwocheckStatus)).makePacket(), (Player)pl);
               }
            } catch (Exception var9) {
               ;
            }
         }
      }

   }

   public void newSendToClient(boolean force) {
      if(this.PlayerOneID > 0 && this.PlayerTwoID > 0) {
         this.sendToClient(force, new Entity[]{super.worldObj.getEntityByID(this.PlayerOneID), super.worldObj.getEntityByID(this.PlayerTwoID)});
      } else {
         if(this.PlayerOneID > 0) {
            this.sendToClient(force, new Entity[]{super.worldObj.getEntityByID(this.PlayerOneID)});
         }

         if(this.PlayerTwoID > 0) {
            this.sendToClient(force, new Entity[]{super.worldObj.getEntityByID(this.PlayerTwoID)});
         }
      }

   }

   public int getSizeInventory() {
      return this.inv.length;
   }

   public ItemStack getStackInSlot(int slot) {
      return this.inv[slot];
   }

   public void setInventorySlotContents(int slot, ItemStack stack) {
      this.inv[slot] = stack;
      if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

   }

   public ItemStack decrStackSize(int slot, int amt) {
      ItemStack stack = this.getStackInSlot(slot);
      if(stack != null) {
         if(stack.stackSize <= amt) {
            this.setInventorySlotContents(slot, (ItemStack)null);
         } else {
            stack = stack.splitStack(amt);
            if(stack.stackSize == 0) {
               this.setInventorySlotContents(slot, (ItemStack)null);
            }
         }
      }

      return stack;
   }

   public ItemStack getStackInSlotOnClosing(int slot) {
      ItemStack stack = this.getStackInSlot(slot);
      if(stack != null) {
         this.setInventorySlotContents(slot, (ItemStack)null);
      }

      return stack;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer player) {
      return player.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) < 64.0D;
   }

   public void readFromNBT(NBTTagCompound tagCompound) {
      super.readFromNBT(tagCompound);
      this.PlayerOneID = tagCompound.getInteger("poid");
      this.PlayerOneTimeOut = tagCompound.getLong("poto");
      this.PlayerTwoID = tagCompound.getInteger("ptid");
      this.PlayerTwoTimeOut = tagCompound.getLong("ptto");
      this.PlayerOneStatus = tagCompound.getByte("pos");
      this.PlayerTwoStatus = tagCompound.getByte("pts");
      this.PlayerOnecheckStatus = tagCompound.getByte("pocs");
      this.PlayerTwocheckStatus = tagCompound.getByte("ptcs");
      NBTTagList tagList = tagCompound.getTagList("Inventory");

      for(int i = 0; i < tagList.tagCount(); ++i) {
         NBTTagCompound tag = (NBTTagCompound)tagList.tagAt(i);
         Integer slot = Integer.valueOf(tag.getInteger("Slot"));
         if(slot.intValue() >= 0 && slot.intValue() < this.inv.length) {
            this.inv[slot.intValue()] = ItemStack.loadItemStackFromNBT(tag);
         }
      }

   }

   public void writeToNBT(NBTTagCompound tagCompound) {
      super.writeToNBT(tagCompound);
      tagCompound.setInteger("poid", this.PlayerOneID);
      tagCompound.setLong("poto", this.PlayerOneTimeOut);
      tagCompound.setInteger("ptid", this.PlayerTwoID);
      tagCompound.setLong("ptto", this.PlayerTwoTimeOut);
      tagCompound.setByte("pos", this.PlayerOneStatus);
      tagCompound.setByte("pts", this.PlayerTwoStatus);
      tagCompound.setByte("pocs", this.PlayerOnecheckStatus);
      tagCompound.setByte("ptcs", this.PlayerTwocheckStatus);
      NBTTagList itemList = new NBTTagList();

      for(int i = 0; i < this.inv.length; ++i) {
         ItemStack stack = this.inv[i];
         if(stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("Slot", i);
            stack.writeToNBT(tag);
            itemList.appendTag(tag);
         }
      }

      tagCompound.setTag("Inventory", itemList);
   }

   public String getInvName() {
      return "TileEntityBlockTrade";
   }

   public boolean isInvNameLocalized() {
      return false;
   }

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return false;
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public void openChest() {}

   public void closeChest() {}
}

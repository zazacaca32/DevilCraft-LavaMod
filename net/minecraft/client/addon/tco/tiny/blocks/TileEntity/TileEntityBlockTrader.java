package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventoryFakeUP64;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityBlockTrader extends TileEntityBlockTradeBase {

   public short OfferCounts = 0;
   public LAInternalInventoryFakeUP64 cellinvfake;
   public short controllerX;
   public short controllerY;
   public short controllerZ;
   private long ControllerTriggerUpdate;
   public boolean ControllerTriggerUpdateClient = false;
   public boolean triggerStartTrade = false;
   public int CountTriggerOfferContainer;
   private int ddTriggerCount = 1;
   private boolean ddTriggerCountFlag = false;


   public TileEntityBlockTrader() {
      super.orientation = ForgeDirection.NORTH;
      this.cellinvfake = new LAInternalInventoryFakeUP64(this, 4);
   }

   public boolean canUpdate() {
      return false;
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public LAItemStack getItemSale() {
      return this.cellinvfake.getStackInSlot(0);
   }

   public LAItemStack getItem() {
      return this.cellinvfake.getStackInSlot(1);
   }

   public void readFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.cellinvfake.readFromNBT(nbttagcompound);
   }

   public void writeToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
      this.cellinvfake.writeToNBT(nbttagcompound);
   }

   public LAInternalInventory provideCell() {
      return null;
   }

   public boolean isJob() {
      return !super.worldObj.isRemote?this.ControllerTriggerUpdate + 60L > super.worldObj.getTotalWorldTime():this.ControllerTriggerUpdateClient;
   }

   public void UpdateController(TileEntityBlockTraderController tileEntityBlockTraderController) {
      if(tileEntityBlockTraderController.GetTriggerUpdateCountTraders() != this.CountTriggerOfferContainer) {
         this.CountTriggerOfferContainer = tileEntityBlockTraderController.GetTriggerUpdateCountTraders();
      }

      LAItemStack slot0;
      if(this.ddTriggerCountFlag) {
         this.ddTriggerCountFlag = false;
         slot0 = this.cellinvfake.getStackInSlot(0);
         if(slot0 != null) {
            boolean var141 = true;
            List var151 = tileEntityBlockTraderController.cellinv.getAvaleableLAList();
            if(var151 != null) {
               Iterator var161 = var151.iterator();

               while(var161.hasNext()) {
                  LAItemStack var17 = (LAItemStack)var161.next();
                  if(slot0.eq(var17)) {
                     this.OfferCounts = (short)((int)Math.min(32767L, var17.getStackSize()));
                     var141 = false;
                     break;
                  }
               }
            }

            if(var141) {
               this.OfferCounts = 0;
            }
         }
      }

      LAItemStack var14;
      if(this.triggerStartTrade) {
         slot0 = this.cellinvfake.getStackInSlot(0);
         var14 = this.cellinvfake.getStackInSlot(1);
         LAItemStack var15 = this.cellinvfake.getStackInSlot(2);
         if(slot0 != null && var14 != null && var15 != null && !slot0.eq(var14) && var14.eq(var15) && var15.getStackSize() >= var14.getStackSize()) {
            LAItemStack[] var16 = tileEntityBlockTraderController.cellinv.getAvaleableLA();
            boolean var171 = false;
            boolean flag1 = false;

            int index;
            LAItemStack slot3;
            for(index = 0; index < var16.length; ++index) {
               slot3 = var16[index];
               if(slot3 != null) {
                  if(!var171 && slot3.eq(slot0) && slot3.getStackSize() >= slot0.getStackSize()) {
                     var171 = true;
                  }

                  if(!flag1 && slot3.eq(var14)) {
                     flag1 = true;
                  }
               } else {
                  flag1 = true;
               }

               if(var171 && flag1) {
                  break;
               }
            }

            if(var171 && flag1) {
               tileEntityBlockTraderController.cellinv.lock = true;
               slot3 = this.cellinvfake.getStackInSlot(3);
               boolean fl = true;
               boolean flag2 = true;
               if(slot0.eq(slot3)) {
                  ItemStack var18 = Utils.getSharedItemStack(slot0);
                  int slimit = var18.getItem().getItemStackLimit();
                  if(slimit == 1) {
                     fl = false;
                  }

                  flag2 = false;
               }

               if(fl && (index = tileEntityBlockTraderController.cellinv.findItemIndexSlot(slot0)) != -1 && tileEntityBlockTraderController.cellinv.getStackInSlot(index).getStackSize() >= slot0.getStackSize() && var15.getStackSize() >= var14.getStackSize()) {
                  LAItemStack var181 = var14.copy();
                  var15.decStackSize(var181.getStackSize());
                  if(var15.getStackSize() <= 0L) {
                     this.cellinvfake.setInventorySlotContents(2, (LAItemStack)null);
                  }

                  if(tileEntityBlockTraderController.cellinv.addItems(var181) == null) {
                     if(flag2) {
                        this.cellinvfake.setInventorySlotContents(3, tileEntityBlockTraderController.cellinv.extractItemsNoLimit(slot0.copy()));
                     } else {
                        slot3.incStackSize(tileEntityBlockTraderController.cellinv.extractItemsNoLimit(slot0.copy()).getStackSize());
                     }

                     this.triggerContainerUpdate();
                  }
               }

               tileEntityBlockTraderController.cellinv.lock = false;
            }
         }

         this.triggerStartTrade = false;
      }

      if((this.ControllerTriggerUpdate = super.worldObj.getTotalWorldTime()) % 40L == 0L) {
         if(this.controllerX != tileEntityBlockTraderController.xCoord) {
            this.controllerX = (short)tileEntityBlockTraderController.xCoord;
         }

         if(this.controllerY != tileEntityBlockTraderController.yCoord) {
            this.controllerY = (short)tileEntityBlockTraderController.yCoord;
         }

         if(this.controllerZ != tileEntityBlockTraderController.zCoord) {
            this.controllerZ = (short)tileEntityBlockTraderController.zCoord;
         }
      }

      if(tileEntityBlockTraderController.monitortrigger != null && (var14 = this.cellinvfake.getStackInSlot(0)) != null) {
         tileEntityBlockTraderController.monitortrigger.addMapItemTrader(var14);
      }

      this.updateEntity();
   }

   public short TriggerControllerOffer() {
      if(this.ddTriggerCount != this.CountTriggerOfferContainer) {
         this.ddTriggerCount = this.CountTriggerOfferContainer;
         this.ddTriggerCountFlag = true;
      }

      return this.OfferCounts;
   }
}

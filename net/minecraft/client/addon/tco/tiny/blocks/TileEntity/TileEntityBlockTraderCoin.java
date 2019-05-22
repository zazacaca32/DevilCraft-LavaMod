package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tchestplate.donate.api.IOffertTraderCoin;
import net.minecraft.client.addon.tchestplate.donate.api.Manager;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventoryFakeUP64;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityBlockTraderCoin extends TileEntityBlockTradeBase implements IOffertTraderCoin {

   public static final LAItemStack doanteItem = LAItemStack.create(new ItemStack(LavaChestPlate.ItemDonate, 1, 1));
   public LAItemStack lockItem;
   public short OfferCounts = 0;
   public LAInternalInventoryFakeUP64 cellinvfake;
   public short controllerX;
   public short controllerY;
   public short controllerZ;
   public boolean ControllerTriggerUpdateClient = false;
   public boolean triggerStartTrade = false;
   private int CountTriggerOfferContainer;
   private long ControllerTriggerUpdate;
   private int ddTriggerCount = 1;
   private boolean ddTriggerCountFlag = false;
   public EntityPlayer startTraderCoinPlayer;
   private boolean triggerBackControllerItem = false;
   private boolean triggerUpdateGuiItemSale = false;


   public TileEntityBlockTraderCoin() {
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

   public short TriggerControllerOffer() {
      if(this.ddTriggerCount != this.CountTriggerOfferContainer) {
         this.ddTriggerCount = this.CountTriggerOfferContainer;
         this.ddTriggerCountFlag = true;
      }

      return this.OfferCounts;
   }

   public void UpdateController(TileEntityBlockTraderController tileEntityBlockTraderController) {
      if(tileEntityBlockTraderController.GetTriggerUpdateCountTraders() != this.CountTriggerOfferContainer) {
         this.CountTriggerOfferContainer = tileEntityBlockTraderController.GetTriggerUpdateCountTraders();
      }

      if(this.triggerBackControllerItem) {
         this.triggerBackControllerItem = false;
         if(this.lockItem != null) {
            tileEntityBlockTraderController.cellinv.addItems(this.lockItem.copy());
            this.lockItem = null;
         }
      }

      LAItemStack it;
      if(this.ddTriggerCountFlag) {
         this.ddTriggerCountFlag = false;
         it = this.cellinvfake.getStackInSlot(0);
         if(it != null) {
            boolean var151 = true;
            List var161 = tileEntityBlockTraderController.cellinv.getAvaleableLAList();
            if(var161 != null) {
               Iterator var18 = var161.iterator();

               while(var18.hasNext()) {
                  LAItemStack var17 = (LAItemStack)var18.next();
                  if(it.eq(var17)) {
                     this.OfferCounts = (short)((int)Math.min(32767L, var17.getStackSize()));
                     var151 = false;
                     break;
                  }
               }
            }

            if(var151) {
               this.OfferCounts = 0;
            }
         }
      }

      if(this.lockItem == null || this.triggerStartTrade) {
         LAItemStack[] var15 = new LAItemStack[]{this.cellinvfake.getStackInSlot(0), this.cellinvfake.getStackInSlot(1)};
         if(var15[0] != null && var15[1] != null && !var15[0].eq(var15[1])) {
            long var161 = 0L;
            byte var171 = 0;
            if(var15[0].eq(doanteItem)) {
               var161 = var15[0].getStackSize();
               var171 = 1;
            } else if(var15[1].eq(doanteItem)) {
               var161 = var15[1].getStackSize();
            }

            if(var161 >= 10L) {
               LAItemStack[] var16 = tileEntityBlockTraderController.cellinv.getAvaleableLA();
               boolean flag = false;

               LAItemStack slot0_;
               for(int var181 = 0; var181 < var16.length; ++var181) {
                  slot0_ = var16[var181];
                  if(slot0_ != null && !flag && slot0_.eq(var15[var171]) && slot0_.getStackSize() >= var15[var171].getStackSize()) {
                     flag = true;
                  }

                  if(flag) {
                     break;
                  }
               }

               LAItemStack var181;
               if((var181 = this.cellinvfake.getStackInSlot(3)) != null && Utils.getSharedItemStack(var181).getItem().getItemStackLimit() == 1) {
                  flag = false;
               }

               int var1811;
               if(flag && this.lockItem == null && (var1811 = tileEntityBlockTraderController.cellinv.findItemIndexSlot(var15[var171])) != -1 && (slot0_ = tileEntityBlockTraderController.cellinv.getStackInSlot(var1811)).getStackSize() >= var15[var171].getStackSize()) {
                  if(this.startTraderCoinPlayer != null) {
                     IDonateUser var20 = Manager.getDonateUser(this.startTraderCoinPlayer);
                     String var19;
                     if(var20.getCoin() >= 10 && (long)var20.getCoin() >= var161) {
                        LAItemStack var191 = var15[var171].copy();
                        slot0_.decStackSize(var191.getStackSize());
                        if(slot0_.getStackSize() <= 0L) {
                           tileEntityBlockTraderController.cellinv.setInventorySlotContents(var1811, (LAItemStack)null);
                        }

                        this.lockItem = var191;
                        System.out.println(super.Owner + " summ-" + var161 + " item-" + var191.getItemID() + " count-" + var191.getStackSize());
                        var20.setStatus(1);
                        if(!var20.startOffertTraderCoin(super.Owner, (int)var161, this)) {
                           var20.setStatus(10);
                           var20.setStatus(0);
                           tileEntityBlockTraderController.cellinv.addItems(var191);
                           var19 = null;
                        } else {
                           this.lockItem = var191;
                           var191.decStackSize(var191.getStackSize());
                           tileEntityBlockTraderController.cellinv.addItems(var191);
                        }
                     } else {
                        var19 = "null";
                        if(this.startTraderCoinPlayer != null) {
                           var19 = this.startTraderCoinPlayer.getEntityName();
                        }

                        System.out.println(super.Owner + " " + var19 + " не достаточно коинов");
                     }

                     this.startTraderCoinPlayer = null;
                  }

                  this.triggerContainerUpdate();
               }
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

      if(tileEntityBlockTraderController.monitortrigger != null && (it = this.cellinvfake.getStackInSlot(0)) != null) {
         tileEntityBlockTraderController.monitortrigger.addMapItemTrader(it);
      }

      if(this.triggerUpdateGuiItemSale) {
         this.triggerUpdateGuiItemSale = false;
         tileEntityBlockTraderController.updateItemsSaleGui();
      }

      this.updateEntity();
   }

   public void JobOffer(IDonateUser user, int donate, int getfirstDonate) {}

   public void JobOfferTraderCoin(IDonateUser user, String DestName, int donate, int getfirstDonate) {
      user.setStatus(2);
      if(this.lockItem != null) {
         boolean flag1 = false;
         if(donate == 0) {
            boolean fl = true;
            LAItemStack slot3 = this.cellinvfake.getStackInSlot(3);
            if(slot3 != null) {
               ItemStack slot111 = Utils.getSharedItemStack(slot3);
               int count11 = slot111.getItem().getItemStackLimit();
               if(count11 == 1) {
                  fl = false;
               }
            }

            if(fl) {
               LAItemStack[] slot1111 = new LAItemStack[]{this.cellinvfake.getStackInSlot(0), this.cellinvfake.getStackInSlot(1)};
               if(slot1111[0] != null && slot1111[1] != null) {
                  if(!slot1111[0].eq(slot1111[1])) {
                     flag1 = false;
                     long count111 = 0L;
                     byte iSlot = 0;
                     if(slot1111[0].eq(doanteItem)) {
                        count111 = slot1111[0].getStackSize();
                        iSlot = 1;
                     } else if(slot1111[1].eq(doanteItem)) {
                        count111 = slot1111[1].getStackSize();
                     }

                     if(slot3 == null) {
                        this.cellinvfake.setInventorySlotContents(3, this.lockItem.copy());
                        flag1 = true;
                     } else if(slot1111[iSlot].eq(slot3)) {
                        slot3.incStackSize(this.lockItem.getStackSize());
                        flag1 = true;
                     } else {
                        flag1 = true;
                        user.setStatus(16);
                     }

                     if(flag1) {
                        this.lockItem = null;
                        user.setStatus(3);
                        user.setStatus(0);
                        this.triggerUpdateGuiItemSale = true;
                     }
                  } else {
                     user.setStatus(15);
                     user.setStatus(0);
                     flag1 = true;
                  }
               } else {
                  user.setStatus(14);
                  user.setStatus(0);
                  flag1 = true;
               }
            } else {
               user.setStatus(13);
               user.setStatus(0);
               flag1 = true;
            }
         } else {
            user.setStatus(12);
            user.setStatus(0);
            flag1 = true;
         }

         if(flag1) {
            this.triggerBackControllerItem = true;
         }
      } else {
         user.setStatus(11);
         user.setStatus(0);
      }

      this.triggerContainerUpdate();
   }

}

package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.Utils.INetworkNotifiable;
import net.minecraft.client.addon.tco.tiny.Utils.LAInternalInventory;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.TradeNodeManager;
import net.minecraft.client.addon.tco.tiny.Utils.TraderNode;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.METileEntityBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockTraderController extends METileEntityBase implements INetworkNotifiable {

   public String Owner = " ";
   public LAInternalInventory cellinv = new LAInternalInventory(this, 63);
   public TradeNodeManager mng = new TradeNodeManager();
   private int triggerUpdateCountTraders = 0;
   int intitsearch = 0;
   public int triggercount = 0;
   public TileEntityBlockTraderControllerMonitor monitortrigger;


   public int GetTriggerUpdateCountTraders() {
      return this.triggerUpdateCountTraders;
   }

   public void readFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.cellinv.readFromNBT(nbttagcompound);
      this.mng.readFromNBT(nbttagcompound);
      this.Owner = nbttagcompound.getString("own");
   }

   public void writeToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
      this.cellinv.writeToNBT(nbttagcompound);
      this.mng.writeToNBT(nbttagcompound);
      nbttagcompound.setString("own", this.Owner);
   }

   public void placedBy(EntityLiving entityliving) {
      this.Owner = entityliving.getEntityName();
   }

   public void updateTileEntity() {
      if(!super.worldObj.isRemote) {
         if(this.intitsearch < 100) {
            ++this.intitsearch;
            return;
         }

         this.mng.removeTick();
         Iterator var1 = this.mng.get().iterator();

         while(var1.hasNext()) {
            TraderNode n = (TraderNode)var1.next();
            TileEntityBlockTradeBase tile = n.getRef(super.worldObj);
            if(n.isRemove) {
               this.mng.makeRemove(n);
            } else if(tile != null) {
               if(tile.contrX == super.xCoord && tile.contrY == super.yCoord && tile.contrZ == super.zCoord) {
                  tile.UpdateController(this);
               } else {
                  this.mng.makeRemove(n);
               }
            }
         }

         if(this.monitortrigger != null) {
            this.monitortrigger.endMonitorUpdate(this.cellinv.getAvaleableLAList());
            this.monitortrigger = null;
         }

         if(super.worldObj.getTotalWorldTime() % 40L == 0L && this.triggercount != this.mng.getSize()) {
            this.triggercount = this.mng.getSize();
         }
      }

   }

   public void InvChanged(LAInternalInventory inv, int slot, TileEntityBlockTraderController.InvOperation Operation) {
      if(inv == this.cellinv && Operation != TileEntityBlockTraderController.InvOperation.onInventoryChanged && super.worldObj != null) {
         this.triggerContainerUpdate();
         ++this.triggerUpdateCountTraders;
      }

   }

   public void updateItemsSaleGui() {
      ++this.triggerUpdateCountTraders;
      this.triggerContainerUpdate();
   }

   public void notifyExtractItems(LAItemStack removed) {
      this.triggerContainerUpdate();
      ++this.triggerUpdateCountTraders;
   }

   public void notifyAddItems(LAItemStack added) {
      this.triggerContainerUpdate();
      ++this.triggerUpdateCountTraders;
   }

   public void afterContainerUpdate() {}

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public String getName() {
      return "InvLA";
   }

   public void onInventoryChanged() {
      if(this.cellinv != null && Utils.isServer()) {
         this.InvChanged(this.cellinv, -1, TileEntityBlockTraderController.InvOperation.onInventoryChanged);
      }

      super.onInventoryChanged();
   }

   public LAInternalInventory provideCell() {
      return this.cellinv;
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public boolean addTorgApparat(int x, int y, int z) {
      return this.mng.add(x, y, z);
   }

   public void updateMonitor(TileEntityBlockTraderControllerMonitor tileEntityBlockTraderControllerMonitor) {
      this.monitortrigger = tileEntityBlockTraderControllerMonitor;
   }

   public static enum InvOperation {

      decrStackSize("decrStackSize", 0, "decrStackSize", 0, "decrStackSize", 0, "decrStackSize", 0, "decrStackSize", 0),
      setInventorySlotContents("setInventorySlotContents", 1, "setInventorySlotContents", 1, "setInventorySlotContents", 1, "setInventorySlotContents", 1, "setInventorySlotContents", 1),
      onInventoryChanged("onInventoryChanged", 2, "onInventoryChanged", 2, "onInventoryChanged", 2, "onInventoryChanged", 2, "onInventoryChanged", 2),
      load("load", 3, "load", 3, "load", 3, "load", 3, "load", 3);
      private static final TileEntityBlockTraderController.InvOperation[] $VALUES = new TileEntityBlockTraderController.InvOperation[]{decrStackSize, setInventorySlotContents, onInventoryChanged, load};
      private static final TileEntityBlockTraderController.InvOperation[] $VALUES$ = new TileEntityBlockTraderController.InvOperation[]{decrStackSize, setInventorySlotContents, onInventoryChanged, load};
      private static final TileEntityBlockTraderController.InvOperation[] $VALUES$$ = new TileEntityBlockTraderController.InvOperation[]{decrStackSize, setInventorySlotContents, onInventoryChanged, load};
      private static final TileEntityBlockTraderController.InvOperation[] $VALUES$$$ = new TileEntityBlockTraderController.InvOperation[]{decrStackSize, setInventorySlotContents, onInventoryChanged, load};
      // $FF: synthetic field
      private static final TileEntityBlockTraderController.InvOperation[] $VALUES$$$$ = new TileEntityBlockTraderController.InvOperation[]{decrStackSize, setInventorySlotContents, onInventoryChanged, load};


      private InvOperation(String var1, int var2, String var11, int var21, String ds, int dss, String va2r11, int v2ar21, String var11231, int v123ar21) {}

   }
}

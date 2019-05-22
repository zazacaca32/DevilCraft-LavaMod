package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressBow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPressBow extends Container {

   private TileEntityBlockPressBow tileEntity;
   private int dualCookTime = 0;
   private int dualspeedreamaningTime = 0;
   private boolean dualisWorking = false;
   private int dualchance = 0;
   private int confx = 0;


   public ContainerPressBow(InventoryPlayer inventory, TileEntityBlockPressBow tileEntity, EntityPlayer player) {
      this.tileEntity = tileEntity;
      this.bindPlayerInventory(player.inventory);
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.tileEntity.isuse(entityplayer);
   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
      }

   }

   public final ItemStack transferStackInSlot(EntityPlayer p, int idx) {
      return null;
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileEntity.tickPowerTime);
      par1ICrafting.sendProgressBarUpdate(this, 1, this.tileEntity.speedreamaningTime);
      par1ICrafting.sendProgressBarUpdate(this, 2, this.tileEntity.isWorking?1:0);
      par1ICrafting.sendProgressBarUpdate(this, 3, this.tileEntity.percent);
      par1ICrafting.sendProgressBarUpdate(this, 4, this.tileEntity.fx);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualCookTime != this.tileEntity.tickPowerTime) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.tickPowerTime);
         }

         if(this.dualspeedreamaningTime != this.tileEntity.speedreamaningTime) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.speedreamaningTime);
         }

         if(this.dualisWorking != this.tileEntity.isWorking) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.isWorking?1:0);
         }

         if(this.dualchance != this.tileEntity.percent) {
            icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.percent);
         }

         if(this.confx != this.tileEntity.fx) {
            icrafting.sendProgressBarUpdate(this, 4, this.tileEntity.fx);
            this.tileEntity.fx = 0;
         }
      }

      this.dualCookTime = this.tileEntity.tickPowerTime;
      this.dualspeedreamaningTime = this.tileEntity.speedreamaningTime;
      this.dualisWorking = this.tileEntity.isWorking;
      this.dualchance = this.tileEntity.percent;
      this.confx = this.tileEntity.fx;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileEntity.tickPowerTime = par2;
      }

      if(par1 == 1) {
         this.tileEntity.speedreamaningTime = par2;
      }

      if(par1 == 2) {
         this.tileEntity.isWorking = par2 > 0;
      }

      if(par1 == 3) {
         this.tileEntity.percent = (byte)par2;
      }

      if(par1 == 4) {
         this.tileEntity.fx = par2;
      }

   }

   public void onCraftGuiClosed(EntityPlayer par1EntityPlayer) {
      super.onCraftGuiClosed(par1EntityPlayer);
      this.tileEntity.isuse = false;
      this.tileEntity.usePlayer = null;
   }
}

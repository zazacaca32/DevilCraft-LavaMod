package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTurning;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInv;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerTurning extends ContainerBase {

   private TileEntityBlockTurning tileentity;
   private int dualCookTime = 0;
   private int dualBurnTime = 0;
   private int dualCrystalTime = 0;
   private int dualWaterTime = 0;
   private int dualkSpeed = 0;
   private int dualkDisk = 0;


   public ContainerTurning(InventoryPlayer inventoryplayer, TileEntityBlockTurning tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      Slot_R r = null;
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(0);
      SlotInvConsumableIDmeta slot = new SlotInvConsumableIDmeta(SlotInv.Access.I, tileentity, 0, 26, 14, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(1);
      slot = new SlotInvConsumableIDmeta(SlotInv.Access.IO, tileentity, 1, 26, 52, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      SlotInvOutput slot2 = new SlotInvOutput(tileentity, 2, 115, 14);
      this.addSlotToContainer(slot2);
      slot2 = new SlotInvOutput(tileentity, 3, 115, 52);
      this.addSlotToContainer(slot2);
      slot2 = new SlotInvOutput(tileentity, 4, 47, 14);
      this.addSlotToContainer(slot2);
      this.CalcFullInv(84);
   }

   public void addCraftingToCrafters(ICrafting listener) {
      super.addCraftingToCrafters(listener);
      listener.sendProgressBarUpdate(this, 0, this.tileentity.tickCookTime);
      listener.sendProgressBarUpdate(this, 1, this.tileentity.tickWaterTime);
      listener.sendProgressBarUpdate(this, 2, this.tileentity.speedcrystalTime);
      listener.sendProgressBarUpdate(this, 3, this.tileentity.kSpeed);
      listener.sendProgressBarUpdate(this, 4, this.tileentity.kDisk);
      this.getMergeMaxSlotIndex(5);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualCookTime != this.tileentity.tickCookTime) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.tickCookTime);
         }

         if(this.dualBurnTime != this.tileentity.tickWaterTime) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.tickWaterTime);
         }

         if(this.dualCrystalTime != this.tileentity.speedcrystalTime) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.speedcrystalTime);
         }

         if(this.dualkSpeed != this.tileentity.kSpeed) {
            icrafting.sendProgressBarUpdate(this, 3, this.tileentity.kSpeed);
         }

         if(this.dualkDisk != this.tileentity.kDisk) {
            icrafting.sendProgressBarUpdate(this, 4, this.tileentity.kDisk);
         }
      }

      this.dualCookTime = this.tileentity.tickCookTime;
      this.dualBurnTime = this.tileentity.tickWaterTime;
      this.dualCrystalTime = this.tileentity.speedcrystalTime;
      this.dualkSpeed = this.tileentity.kSpeed;
      this.dualkDisk = this.tileentity.kDisk;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.tickCookTime = par2;
      }

      if(par1 == 1) {
         this.tileentity.tickWaterTime = par2;
         this.tileentity.mLiquid.amount = par2;
      }

      if(par1 == 2) {
         this.tileentity.speedcrystalTime = par2;
      }

      if(par1 == 3) {
         this.tileentity.kSpeed = par2;
      }

      if(par1 == 4) {
         this.tileentity.kDisk = par2;
      }

   }

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   public int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}

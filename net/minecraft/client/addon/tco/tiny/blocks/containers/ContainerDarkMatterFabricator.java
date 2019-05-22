package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerDarkMatterFabricator extends ContainerBase {

   TileEntityBlockDarkMatterFabricator tileentity;
   private boolean dualisWorking;
   private short dualcountDarkEnergy;
   private short dualcountDarkEnergyController;


   public ContainerDarkMatterFabricator(InventoryPlayer inventory, TileEntityBlockDarkMatterFabricator tileEntity) {
      super(inventory);
      this.tileentity = tileEntity;
      Slot_R r = null;
      r = (Slot_R)Manager.INSTANCE.Get(this.tileentity.RecipesIndex).get(0);
      SlotInvOutput slot = new SlotInvOutput(this.tileentity, 0, 98, 45);
      this.addSlotToContainer(slot);
      r = (Slot_R)Manager.INSTANCE.Get(this.tileentity.RecipesIndex).get(1);
      SlotInvConsumableIDmeta slot1 = new SlotInvConsumableIDmeta(r.access, this.tileentity, 1, 61, 45, r.getFloatIDandDamage());
      this.addSlotToContainer(slot1);
      this.CalcFullInv(84);
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.tileentity.isUseableByPlayer(entityplayer);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.countDarkEnergy);
      par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.isWorking?1:0);
      par1ICrafting.sendProgressBarUpdate(this, 2, this.tileentity.DarkEnergyController);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualcountDarkEnergy != this.tileentity.countDarkEnergy) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.countDarkEnergy);
         }

         if(this.dualisWorking != this.tileentity.isWorking) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.isWorking?1:0);
         }

         if(this.dualcountDarkEnergyController != this.tileentity.DarkEnergyController) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.DarkEnergyController);
         }
      }

      this.dualcountDarkEnergy = this.tileentity.countDarkEnergy;
      this.dualisWorking = this.tileentity.isWorking;
      this.dualcountDarkEnergyController = this.tileentity.DarkEnergyController;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.countDarkEnergy = (short)par2;
      }

      if(par1 == 1) {
         this.tileentity.isWorking = par2 > 0;
      }

      if(par1 == 2) {
         this.tileentity.DarkEnergyController = (short)par2;
      }

   }

   int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}

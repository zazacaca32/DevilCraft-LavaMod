package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyHarvest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

public class ContainerDarkEnergyHarvest extends Container {

   TileEntityBlockDarkEnergyHarvest tileentity;
   private int dualWorking;
   private short dualcountDarkEnergy;


   public ContainerDarkEnergyHarvest(InventoryPlayer inventory, TileEntityBlockDarkEnergyHarvest tileEntity) {
      this.tileentity = tileEntity;
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.tileentity.isUseableByPlayer(entityplayer);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.countDarkEnergy);
      par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.Working);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualcountDarkEnergy != this.tileentity.countDarkEnergy) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.countDarkEnergy);
         }

         if(this.dualWorking != this.tileentity.Working) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.Working);
         }
      }

      this.dualcountDarkEnergy = this.tileentity.countDarkEnergy;
      this.dualWorking = this.tileentity.Working;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.countDarkEnergy = (short)par2;
      }

      if(par1 == 1) {
         this.tileentity.Working = par2;
      }

   }
}

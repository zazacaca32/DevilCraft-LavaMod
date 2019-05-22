package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInv;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerPetDesk extends ContainerBase {

   private TileEntityBlockPetDesk tileentity;
   private int dualCookTime = 0;
   private int dualBurnTime = 0;
   private int dualCrystalTime = 0;
   private int dualWaterTime = 0;


   public ContainerPetDesk(InventoryPlayer inventoryplayer, TileEntityBlockPetDesk tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      Slot_R r = null;
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(0);
      SlotInvConsumableIDmeta slot = new SlotInvConsumableIDmeta(SlotInv.Access.IO, tileentity, 0, 26, 14, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(1);
      slot = new SlotInvConsumableIDmeta(SlotInv.Access.IO, tileentity, 1, 26, 52, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      SlotInvOutput slot1 = new SlotInvOutput(tileentity, 2, 80, 33);
      this.addSlotToContainer(slot1);
      this.CalcFullInv(84);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.tickCookTime);
      par1ICrafting.sendProgressBarUpdate(this, 2, this.tileentity.speedcrystalTime);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualCookTime != this.tileentity.tickCookTime) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.tickCookTime);
         }

         if(this.dualCrystalTime != this.tileentity.speedcrystalTime) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.speedcrystalTime);
         }
      }

      this.dualCookTime = this.tileentity.tickCookTime;
      this.dualCrystalTime = this.tileentity.speedcrystalTime;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.tickCookTime = par2;
      }

      if(par1 == 2) {
         this.tileentity.speedcrystalTime = par2;
      }

   }

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}

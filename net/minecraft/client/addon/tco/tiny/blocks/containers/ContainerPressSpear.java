package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityPressSpear;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableID;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerPressSpear extends ContainerBase {

   private TileEntityPressSpear tileentity;
   private int dualCookTime = 0;
   private int dualspeedreamaningTime = 0;
   private boolean dualisWorking = false;
   private int dualthisRenderModel = 0;
   private int dualchance = 0;


   public ContainerPressSpear(InventoryPlayer inventoryplayer, TileEntityPressSpear tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      Slot_R r = null;
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(0);
      SlotInvConsumableIDmeta slot = new SlotInvConsumableIDmeta(r.access, tileentity, 0, 54, 35, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(1);
      SlotInvConsumableID slot1 = new SlotInvConsumableID(r.access, tileentity, 1, 112, 35, r.getFloatIDandDamage());
      this.addSlotToContainer(slot1);
      this.CalcFullInv(84);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.tickPowerTime);
      par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.speedreamaningTime);
      par1ICrafting.sendProgressBarUpdate(this, 2, this.tileentity.isWorking?1:0);
      par1ICrafting.sendProgressBarUpdate(this, 3, this.tileentity.thisRenderModel);
      par1ICrafting.sendProgressBarUpdate(this, 4, this.tileentity.chance);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualCookTime != this.tileentity.tickPowerTime) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.tickPowerTime);
         }

         if(this.dualspeedreamaningTime != this.tileentity.speedreamaningTime) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.speedreamaningTime);
         }

         if(this.dualisWorking != this.tileentity.isWorking) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.isWorking?1:0);
         }

         if(this.dualthisRenderModel != this.tileentity.thisRenderModel) {
            icrafting.sendProgressBarUpdate(this, 3, this.tileentity.thisRenderModel);
         }

         if(this.dualchance != this.tileentity.chance) {
            icrafting.sendProgressBarUpdate(this, 4, this.tileentity.chance);
         }
      }

      this.dualCookTime = this.tileentity.tickPowerTime;
      this.dualspeedreamaningTime = this.tileentity.speedreamaningTime;
      this.dualisWorking = this.tileentity.isWorking;
      this.dualthisRenderModel = this.tileentity.thisRenderModel;
      this.dualchance = this.tileentity.chance;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.tickPowerTime = par2;
      }

      if(par1 == 1) {
         this.tileentity.speedreamaningTime = par2;
      }

      if(par1 == 2) {
         TileEntityPressSpear tileentity = this.tileentity;
         boolean isWorking = false;
         if(par2 <= 0) {
            isWorking = false;
         }

         tileentity.isWorking = isWorking;
      }

      if(par1 == 3) {
         this.tileentity.thisRenderModel = (byte)par2;
      }

      if(par1 == 4) {
         this.tileentity.chance = (byte)par2;
      }

   }

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}

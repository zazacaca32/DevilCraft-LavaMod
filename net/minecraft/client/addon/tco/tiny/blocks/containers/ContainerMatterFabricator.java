package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableID;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerMatterFabricator extends ContainerBase {

   private TileEntityBlockMatterFabricator tileentity;
   private int dualprogress_BurnMatter = 0;
   private int dualprogress_getUtilScale = 0;
   private boolean dualisWorking = false;
   private int dualthisRenderModel = 0;
   private int dualUtilModify = 0;
   private boolean dualsunIsUp = false;


   public ContainerMatterFabricator(InventoryPlayer inventoryplayer, TileEntityBlockMatterFabricator tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      Slot_R r = null;

      for(int var71 = 0; var71 < 2; ++var71) {
         for(int j = 0; j < 5; ++j) {
            r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(j + var71 * 5);
            SlotInvConsumableIDmeta slot1 = new SlotInvConsumableIDmeta(r.access, tileentity, j + var71 * 5, 80 + j * 18, 12 + var71 * 18, r.getFloatIDandDamage());
            this.addSlotToContainer(slot1);
         }
      }

      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(10);
      SlotInvConsumableID var7 = new SlotInvConsumableID(r.access, tileentity, 10, 54, 12, r.getFloatIDandDamage());
      this.addSlotToContainer(var7);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(11);
      var7 = new SlotInvConsumableID(r.access, tileentity, 11, 28, 45, r.getFloatIDandDamage());
      this.addSlotToContainer(var7);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(12);
      var7 = new SlotInvConsumableID(r.access, tileentity, 12, 116, 57, r.getFloatIDandDamage());
      this.addSlotToContainer(var7);
      this.CalcFullInv(84);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.progress_BurnMatter);
      par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.progress_getUtilScale);
      par1ICrafting.sendProgressBarUpdate(this, 2, this.tileentity.isWorking?1:0);
      par1ICrafting.sendProgressBarUpdate(this, 3, this.tileentity.sunIsUp?1:0);
      par1ICrafting.sendProgressBarUpdate(this, 4, (int)this.tileentity.UtilModify);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualprogress_BurnMatter != this.tileentity.progress_BurnMatter) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.progress_BurnMatter);
         }

         if(this.dualprogress_getUtilScale != this.tileentity.progress_getUtilScale) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.progress_getUtilScale);
         }

         if(this.dualisWorking != this.tileentity.isWorking) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.isWorking?1:0);
         }

         if(this.dualsunIsUp != this.tileentity.sunIsUp) {
            icrafting.sendProgressBarUpdate(this, 3, this.tileentity.sunIsUp?1:0);
         }

         if((float)this.dualUtilModify != this.tileentity.UtilModify) {
            icrafting.sendProgressBarUpdate(this, 4, (int)this.tileentity.UtilModify);
         }
      }

      this.dualprogress_BurnMatter = this.tileentity.progress_BurnMatter;
      this.dualprogress_getUtilScale = this.tileentity.progress_getUtilScale;
      this.dualisWorking = this.tileentity.isWorking;
      this.dualsunIsUp = this.tileentity.sunIsUp;
      this.dualUtilModify = (int)this.tileentity.UtilModify;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.progress_BurnMatter = (short)par2;
      }

      if(par1 == 1) {
         this.tileentity.progress_getUtilScale = (short)par2;
      }

      boolean var3;
      if(par1 == 2) {
         this.tileentity.isWorking = par2 > 0;
         var3 = this.tileentity.isWorking;
      }

      if(par1 == 3) {
         this.tileentity.sunIsUp = par2 > 0;
         var3 = this.tileentity.sunIsUp;
      }

      if(par1 == 4) {
         this.tileentity.UtilModify = (float)par2;
      }

   }

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex < 10?10:SlotIndex + 1;
   }
}

package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerCompressionMatter extends ContainerBase {

   private TileEntityBlockCompressionMatter tileentity;
   private int dualprogress_getUtilScale = 0;


   public ContainerCompressionMatter(InventoryPlayer inventoryplayer, TileEntityBlockCompressionMatter tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      Slot_R r = null;

      SlotInvConsumableIDmeta slot;
      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 6; ++j) {
            r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(j + i * 6);
            slot = new SlotInvConsumableIDmeta(r.access, tileentity, j + i * 6, 35 + j * 18, 15 + i * 18, r.getFloatIDandDamage());
            this.addSlotToContainer(slot);
         }
      }

      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(18);
      slot = new SlotInvConsumableIDmeta(r.access, tileentity, 18, 151, 15, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(19);
      slot = new SlotInvConsumableIDmeta(r.access, tileentity, 19, 151, 51, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      this.CalcFullInv(84);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.progress_getUtilScale);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualprogress_getUtilScale != this.tileentity.progress_getUtilScale) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.progress_getUtilScale);
         }
      }

      this.dualprogress_getUtilScale = this.tileentity.progress_getUtilScale;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.progress_getUtilScale = (short)par2;
      }

   }

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   int getMergeMaxSlotIndex(int SlotIndex) {
      this.tileentity.setTimeL();
      return SlotIndex < 18?18:SlotIndex + 1;
   }
}

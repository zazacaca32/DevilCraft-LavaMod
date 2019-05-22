package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerLavaAnvil extends ContainerBase {

   public TileEntityBlockLavaAnvil tileentity;
   private boolean dualisWorking;
   private short dualcountDarkEnergy;
   private short dualcountDarkEnergyController;


   public ContainerLavaAnvil(InventoryPlayer inventory, TileEntityBlockLavaAnvil tileEntity, IDonateUser iDonateUser) {
      super(inventory);
      this.tileentity = tileEntity;
      Slot_R r = null;
      r = (Slot_R)Manager.INSTANCE.Get(this.tileentity.RecipesIndex).get(0);
      SlotInvConsumableIDmeta slot = new SlotInvConsumableIDmeta(r.access, this.tileentity, 0, 80, 35, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      this.CalcFullInv(89);
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.tileentity.isUseableByPlayer(entityplayer);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting var2 = (ICrafting)super.crafters.get(i);
      }

   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {}

   int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}

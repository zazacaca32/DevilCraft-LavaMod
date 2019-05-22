package net.minecraft.client.addon.tco.tiny.blocks.containers;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.addon.tco.tiny.devplay.fxgaddon.SlotGetExperience;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGetExperience extends Container {

   public TileGetExperience tileEntity;


   public ContainerGetExperience(TileGetExperience tileEntity, EntityPlayer player) {
      this.tileEntity = tileEntity;
      this.addSlotToContainer(new SlotGetExperience(tileEntity, 0, 8, 34));
      this.bindPlayerInventory(player.inventory);
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.tileEntity.isUseableByPlayer(entityplayer);
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
}

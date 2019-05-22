package net.minecraft.client.addon.tco.tiny.blocks.containers;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExPult;
import net.minecraft.client.addon.tco.tiny.devplay.fxgaddon.SlotGetExPult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGetExPult extends Container {

   public TileGetExPult tileEntity;


   public ContainerGetExPult(TileGetExPult tileEntity, EntityPlayer player) {
      boolean flag = false;
      this.tileEntity = tileEntity;
      tileEntity.player = player;
      if(player.getEntityName().equals(this.tileEntity.Owner)) {
         flag = true;

         for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
               this.addSlotToContainer(new Slot(tileEntity, i + j * 3, 115 + j * 18, 15 + i * 18));
            }
         }
      } else {
         this.addSlotToContainer(new Slot(tileEntity, 9, 62, 37));
      }

      this.addSlotToContainer(new SlotGetExPult(flag, tileEntity, 10, 26, 37));
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

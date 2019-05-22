package net.minecraft.client.addon.tco.tiny.blocks.containers;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrade;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotTrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrade extends Container {

   protected TileEntityBlockTrade tileEntity;


   public ContainerTrade(IInventory inventoryPlayer, IInventory te) {
      this.tileEntity = (TileEntityBlockTrade)te;
      InventoryPlayer iplayer = (InventoryPlayer)inventoryPlayer;
      int u = this.tileEntity.openChest(iplayer.player);
      int i;
      int j;
      if(u == 1) {
         for(i = 0; i < 2; ++i) {
            for(j = 0; j < 3; ++j) {
               this.addSlotToContainer(new SlotTrade(this.tileEntity, j + i * 3, 98 + j * 18, 13 + i * 18, u));
            }
         }

         for(i = 0; i < 2; ++i) {
            for(j = 0; j < 3; ++j) {
               this.addSlotToContainer(new SlotTrade(this.tileEntity, 6 + j + i * 3, 26 + j * 18, 13 + i * 18, u));
            }
         }
      } else if(u == 2) {
         for(i = 0; i < 2; ++i) {
            for(j = 0; j < 3; ++j) {
               this.addSlotToContainer(new SlotTrade(this.tileEntity, j + i * 3, 26 + j * 18, 13 + i * 18, u));
            }
         }

         for(i = 0; i < 2; ++i) {
            for(j = 0; j < 3; ++j) {
               this.addSlotToContainer(new SlotTrade(this.tileEntity, 6 + j + i * 3, 98 + j * 18, 13 + i * 18, u));
            }
         }
      }

      this.bindPlayerInventory(iplayer);
   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.tileEntity.isUseableByPlayer(player);
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

   public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
      return null;
   }

   public void onCraftGuiClosed(EntityPlayer par1EntityPlayer) {
      this.tileEntity.closeChest(par1EntityPlayer);
   }

   public IInventory getTileInventory() {
      return this.tileEntity;
   }
}

package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.IOException;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotDisabledControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotPlayerHotBar;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotPlayerInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerTraderControllerMonitor extends Container {

   public TileEntityBlockTraderControllerMonitor tileEntity;


   public ContainerTraderControllerMonitor(InventoryPlayer inventory, TileEntityBlockTraderControllerMonitor tileEntity) {
      this.tileEntity = tileEntity;
      int off = 0;

      for(int var6 = 0; var6 < 7; ++var6) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new SlotDisabledControllerMonitor(this, off++, 8 + j * 18, var6 * 18 + 8 + 18));
         }
      }

      if(Utils.isClient()) {
         try {
            PacketDispatcher.sendPacketToServer(this.tileEntity.getRequestPacket());
         } catch (IOException var61) {
            var61.printStackTrace();
         }
      }

   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new SlotPlayerInv(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 166 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new SlotPlayerHotBar(inventoryPlayer, i, 8 + i * 18, 224));
      }

   }

   public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
      return null;
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.tileEntity.isUseableByPlayer(entityplayer);
   }

   public ItemStack decrStackSize(int par1) {
      return null;
   }

   public ItemStack getStackInSlot(int slotIndex) {
      try {
         if(this.tileEntity.listclient.size() > slotIndex) {
            return (ItemStack)this.tileEntity.listclient.get(slotIndex);
         }
      } catch (Exception var3) {
         ;
      }

      return null;
   }

   public void setInventorySlotContents(int slotIndex, ItemStack par1ItemStack) {}

   public void onInventoryChanged() {}

   public void update() {}
}

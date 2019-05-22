package net.minecraft.client.addon.tco.tiny;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.addon.tco.tiny.ConfigHandler;
import net.minecraft.client.addon.tco.tiny.TileEntityTiny;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTiny extends Container {

   protected TileEntityTiny tileEntity;


   public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
      Slot slot;
      if(par3 != 4 && par1 != -999 && (slot = (Slot)super.inventorySlots.get(par1)) != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         int il;
         int i;
         ItemStack var12;
         if(par1 > 53) {
            if(par2 == 1) {
               il = itemstack1.stackTagCompound.getInteger("shopcount");
               if(il > itemstack1.stackSize) {
                  int var11;
                  if(par3 == 1) {
                     if(itemstack1.stackSize < il - 10) {
                        var11 = itemstack1.stackSize += 10;
                     } else {
                        var11 = il;
                     }
                  } else {
                     var11 = ++itemstack1.stackSize;
                  }

                  itemstack1.stackSize = var11;
               }
            } else if(par2 == 0) {
               slot.putStack((ItemStack)null);
            }
         } else {
            Integer var111 = Integer.valueOf(-1);

            for(i = 54; i < 63; ++i) {
               var12 = ((Slot)super.inventorySlots.get(i)).getStack();
               if(!((Slot)super.inventorySlots.get(i)).getHasStack()) {
                  if(var111.intValue() == -1) {
                     var111 = Integer.valueOf(i);
                  }
               } else if(itemstack1.itemID == var12.itemID && itemstack1.getItemDamage() == var12.getItemDamage() && ItemStack.areItemStackTagsEqual(itemstack1, var12)) {
                  var111 = Integer.valueOf(-1);
                  break;
               }
            }

            if(var111.intValue() != -1) {
               this.putStackInSlot(var111.intValue(), slot.getStack().copy());
            }
         }

         il = 0;

         for(i = 54; i < 63; ++i) {
            var12 = ((Slot)super.inventorySlots.get(i)).getStack();
            if(((Slot)super.inventorySlots.get(i)).getHasStack()) {
               il += var12.stackTagCompound.getInteger("price") * var12.stackSize;
            }
         }

         Tiny.sum = il;
      }

      return null;
   }

   public ContainerTiny(TileEntityTiny te) {
      Tiny.sum = 0;
      (this.tileEntity = te).openChest();
      int yy = 0;

      int arr;
      int i;
      for(arr = 0; arr < 6; ++arr) {
         for(i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(this.tileEntity, yy, 8 + i * 18, 24 + arr * 18));
            ++yy;
         }
      }

      for(arr = 0; arr < 9; ++arr) {
         this.addSlotToContainer(new Slot(this.tileEntity, yy, 8 + arr * 18, 136));
         ++yy;
      }

      if(FMLCommonHandler.instance().getSide().isServer()) {
         ItemStack[] var5 = ConfigHandler.getItems();

         for(i = 0; i < var5.length; ++i) {
            this.getSlot(i).putStack(var5[i]);
         }

         for(i = 54; i < 63; ++i) {
            this.getSlot(i).putStack((ItemStack)null);
         }
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.tileEntity.isUseableByPlayer(player);
   }

   public void onCraftGuiClosed(EntityPlayer entityplayer) {
      super.onCraftGuiClosed(entityplayer);
      this.tileEntity.closeChest();
   }

   public ItemStack transferStackInSlot(EntityPlayer player, int s) {
      Object itemstack = null;
      return (ItemStack)itemstack;
   }
}

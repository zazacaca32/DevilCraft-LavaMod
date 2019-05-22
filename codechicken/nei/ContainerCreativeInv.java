package codechicken.nei;

import codechicken.nei.ExtendedCreativeInv;
import codechicken.nei.SlotBlockArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotArmor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerCreativeInv extends Container {

   public ContainerCreativeInv(EntityPlayer player, ExtendedCreativeInv extraInv) {
      InventoryPlayer invPlayer = player.inventory;

      int armourslot;
      int col;
      for(armourslot = 0; armourslot < 6; ++armourslot) {
         for(col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(extraInv, col + armourslot * 9, 8 + col * 18, 5 + armourslot * 18));
         }
      }

      for(armourslot = 0; armourslot < 3; ++armourslot) {
         for(col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(invPlayer, col + armourslot * 9 + 9, 8 + col * 18, 118 + armourslot * 18));
         }
      }

      for(armourslot = 0; armourslot < 9; ++armourslot) {
         this.addSlotToContainer(new Slot(invPlayer, armourslot, 8 + armourslot * 18, 176));
      }

      this.addSlotToContainer(new SlotBlockArmor((ContainerPlayer)player.inventoryContainer, invPlayer, invPlayer.getSizeInventory() - 1, -15, 23, 0));

      for(armourslot = 1; armourslot < 4; ++armourslot) {
         this.addSlotToContainer(new SlotArmor((ContainerPlayer)player.inventoryContainer, invPlayer, invPlayer.getSizeInventory() - 1 - armourslot, -15, 23 + armourslot * 18, armourslot));
      }

   }

   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
      ItemStack transferredStack = null;
      Slot slot = (Slot)super.inventorySlots.get(slotIndex);
      if(slot != null && slot.getHasStack()) {
         ItemStack stack = slot.getStack();
         transferredStack = stack.copy();
         if(stack.getItem() instanceof ItemArmor) {
            ItemArmor armor = (ItemArmor)stack.getItem();
            if(!this.getSlot(90 + armor.armorType).getHasStack()) {
               this.getSlot(90 + armor.armorType).putStack(transferredStack);
               slot.putStack((ItemStack)null);
               return transferredStack;
            }
         }

         if(slotIndex < 54) {
            if(!this.mergeItemStack(stack, 54, 90, true)) {
               return null;
            }
         } else if(!this.mergeItemStack(stack, 0, 54, false)) {
            return null;
         }

         if(stack.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }
      }

      return transferredStack;
   }

   public boolean canInteractWith(EntityPlayer var1) {
      return true;
   }
}

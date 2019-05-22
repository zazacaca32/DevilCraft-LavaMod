package codechicken.core.inventory;

import codechicken.core.inventory.SlotHandleClicks;
import codechicken.core.packet.PacketCustom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerExtended extends Container implements ICrafting {

   public LinkedList playerCrafters = new LinkedList();


   public ContainerExtended() {
      super.crafters.add(this);
   }

   public void addCraftingToCrafters(ICrafting icrafting) {
      if(icrafting instanceof EntityPlayerMP) {
         this.playerCrafters.add((EntityPlayerMP)icrafting);
         this.sendContainerAndContentsToPlayer(this, this.getInventory(), Arrays.asList(new EntityPlayerMP[]{(EntityPlayerMP)icrafting}));
         this.detectAndSendChanges();
      } else {
         super.addCraftingToCrafters(icrafting);
      }

   }

   public void removeCraftingFromCrafters(ICrafting icrafting) {
      if(icrafting instanceof EntityPlayerMP) {
         this.playerCrafters.remove(icrafting);
      } else {
         super.removeCraftingFromCrafters(icrafting);
      }

   }

   public void sendContainerAndContentsToPlayer(Container container, List list) {
      this.sendContainerAndContentsToPlayer(container, list, this.playerCrafters);
   }

   public void sendContainerAndContentsToPlayer(Container container, List list, List playerCrafters) {
      LinkedList largeStacks = new LinkedList();

      int i;
      ItemStack stack;
      for(i = 0; i < list.size(); ++i) {
         stack = (ItemStack)list.get(i);
         if(stack != null && stack.stackSize > 127) {
            list.set(i, (Object)null);
            largeStacks.add(stack);
         } else {
            largeStacks.add((Object)null);
         }
      }

      Iterator var8 = playerCrafters.iterator();

      while(var8.hasNext()) {
         EntityPlayerMP var7 = (EntityPlayerMP)var8.next();
         var7.sendContainerAndContentsToPlayer(container, list);
      }

      for(i = 0; i < largeStacks.size(); ++i) {
         stack = (ItemStack)largeStacks.get(i);
         if(stack != null) {
            this.sendLargeStack(stack, i, playerCrafters);
         }
      }

   }

   public void sendLargeStack(ItemStack stack, int slot, List players) {}

   public void sendProgressBarUpdate(Container container, int i, int j) {
      Iterator var5 = this.playerCrafters.iterator();

      while(var5.hasNext()) {
         EntityPlayerMP player = (EntityPlayerMP)var5.next();
         player.sendProgressBarUpdate(container, i, j);
      }

   }

   public void sendSlotContents(Container container, int slot, ItemStack stack) {
      if(stack != null && stack.stackSize > 127) {
         this.sendLargeStack(stack, slot, this.playerCrafters);
      } else {
         Iterator var5 = this.playerCrafters.iterator();

         while(var5.hasNext()) {
            EntityPlayerMP player = (EntityPlayerMP)var5.next();
            player.sendSlotContents(container, slot, stack);
         }
      }

   }

   public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer player) {
      if(par1 >= 0 && par1 < super.inventorySlots.size()) {
         Slot slot = this.getSlot(par1);
         if(slot instanceof SlotHandleClicks) {
            return ((SlotHandleClicks)slot).slotClick(this, player, par2, par3);
         }
      }

      return super.slotClick(par1, par2, par3, player);
   }

   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
      ItemStack transferredStack = null;
      Slot slot = (Slot)super.inventorySlots.get(slotIndex);
      if(slot != null && slot.getHasStack()) {
         ItemStack stack = slot.getStack();
         transferredStack = stack.copy();
         if(!this.doMergeStackAreas(slotIndex, stack)) {
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

   public boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverse) {
      boolean merged = false;
      int slotIndex = reverse?endIndex - 1:startIndex;
      if(stack == null) {
         return false;
      } else {
         Slot slot;
         if(stack.isStackable()) {
            for(; stack.stackSize > 0; slotIndex += reverse?-1:1) {
               if(reverse) {
                  if(slotIndex < startIndex) {
                     break;
                  }
               } else if(slotIndex >= endIndex) {
                  break;
               }

               slot = (Slot)super.inventorySlots.get(slotIndex);
               ItemStack maxStackSize = slot.getStack();
               if(maxStackSize != null && maxStackSize.itemID == stack.itemID && (!stack.getHasSubtypes() || stack.getItemDamage() == maxStackSize.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, maxStackSize)) {
                  int totalStackSize = maxStackSize.stackSize + stack.stackSize;
                  int maxStackSize1 = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());
                  if(totalStackSize <= maxStackSize1) {
                     stack.stackSize = 0;
                     maxStackSize.stackSize = totalStackSize;
                     slot.onSlotChanged();
                     merged = true;
                  } else if(maxStackSize.stackSize < maxStackSize1) {
                     stack.stackSize -= maxStackSize1 - maxStackSize.stackSize;
                     maxStackSize.stackSize = maxStackSize1;
                     slot.onSlotChanged();
                     merged = true;
                  }
               }
            }
         }

         if(stack.stackSize > 0) {
            for(slotIndex = reverse?endIndex - 1:startIndex; stack.stackSize > 0; slotIndex += reverse?-1:1) {
               if(reverse) {
                  if(slotIndex < startIndex) {
                     break;
                  }
               } else if(slotIndex >= endIndex) {
                  break;
               }

               slot = (Slot)super.inventorySlots.get(slotIndex);
               if(!slot.getHasStack() && slot.isItemValid(stack)) {
                  int maxStackSize2 = Math.min(stack.getMaxStackSize(), slot.getSlotStackLimit());
                  if(stack.stackSize <= maxStackSize2) {
                     slot.putStack(stack.copy());
                     slot.onSlotChanged();
                     stack.stackSize = 0;
                     merged = true;
                  } else {
                     slot.putStack(stack.splitStack(maxStackSize2));
                     slot.onSlotChanged();
                     merged = true;
                  }
               }
            }
         }

         return merged;
      }
   }

   public boolean doMergeStackAreas(int slotIndex, ItemStack stack) {
      return false;
   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
      this.bindPlayerInventory(inventoryPlayer, 8, 84);
   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer, int x, int y) {
      int slot;
      for(slot = 0; slot < 3; ++slot) {
         for(int col = 0; col < 9; ++col) {
            this.addSlotToContainer(new Slot(inventoryPlayer, col + slot * 9 + 9, x + col * 18, y + slot * 18));
         }
      }

      for(slot = 0; slot < 9; ++slot) {
         this.addSlotToContainer(new Slot(inventoryPlayer, slot, x + slot * 18, y + 58));
      }

   }

   public boolean canInteractWith(EntityPlayer var1) {
      return true;
   }

   public void sendContainerPacket(PacketCustom packet) {
      Iterator var3 = this.playerCrafters.iterator();

      while(var3.hasNext()) {
         EntityPlayerMP player = (EntityPlayerMP)var3.next();
         packet.sendToPlayer(player);
      }

   }

   public void handleOutputPacket(PacketCustom packet) {}

   public void handleInputPacket(PacketCustom packet) {}

   public void handleGuiChange(int ID, int value) {}

   public void sendProgressBarUpdate(int barID, int value) {
      Iterator var4 = super.crafters.iterator();

      while(var4.hasNext()) {
         ICrafting crafting = (ICrafting)var4.next();
         crafting.sendProgressBarUpdate(this, barID, value);
      }

   }
}

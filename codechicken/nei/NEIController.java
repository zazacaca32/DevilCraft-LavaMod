package codechicken.nei;

import codechicken.nei.FastTransferManger;
import codechicken.nei.InterActionMap;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.api.API;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.IInfiniteItemHandler;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.forge.IContainerSlotClickHandler;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class NEIController implements IContainerSlotClickHandler {

   ItemStack firstheld;
   public static GuiContainerManager manager;
   public static FastTransferManger fastTransferManager;
   public static boolean deleteMode;
   private static int pickedUpFromSlot;
   private static IInfiniteItemHandler heldStackInfinite;
   private static int selectedItem;


   public static void load() {
      GuiContainerManager.addSlotClickHandler(new NEIController());
   }

   public static void load(GuiContainer gui) {
      manager = (GuiContainerManager) gui.manager;
      deleteMode = false;
      GuiInfo.clearGuiHandlers();
      fastTransferManager = null;
      if(NEIClientConfig.isEnabled()) {
         fastTransferManager = new FastTransferManger();
         if(gui instanceof INEIGuiHandler) {
            API.registerNEIGuiHandler((INEIGuiHandler)gui);
         }

      }
   }

   public void beforeSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier) {
      this.firstheld = NEIClientUtils.getHeldItem();
   }

   public boolean handleSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier, boolean eventconsumed) {
      if(!eventconsumed && NEIClientConfig.isEnabled()) {
         ItemStack held;
         if(deleteMode && slotIndex >= 0 && slot != null) {
            if(NEIClientUtils.shiftKey() && button == 0) {
               held = slot.getStack();
               if(held != null) {
                  NEIClientUtils.deleteItemsOfType(held);
               }
            } else if(button == 1) {
               NEIClientUtils.decreaseSlotStack(slot.slotNumber);
            } else {
               NEIClientUtils.deleteSlotStack(slot.slotNumber);
            }

            return true;
         } else if(button == 1 && slot instanceof SlotCrafting) {
            for(int var8 = 0; var8 < 64; ++var8) {
               gui.handleMouseClick(slot, slotIndex, button, 0);
            }

            return true;
         } else if(slotIndex >= 0 && NEIClientUtils.shiftKey() && NEIClientUtils.getHeldItem() != null && !slot.getHasStack()) {
            held = NEIClientUtils.getHeldItem();
            gui.handleMouseClick(slot, slotIndex, button, 0);
            if(slot.isItemValid(held) && !ItemInfo.fastTransferExemptions.contains(slot.getClass())) {
               fastTransferManager.performMassTransfer(gui, pickedUpFromSlot, slotIndex, held);
            }

            return true;
         } else if(NEIClientUtils.controlKey() && slot != null && slot.getStack() != null && NEIClientConfig.isActionPermissable("item") && slot.isItemValid(slot.getStack())) {
            NEIClientUtils.cheatItem(slot.getStack(), button, 1);
            return true;
         } else if(slotIndex == -999 && NEIClientUtils.shiftKey() && button == 0) {
            fastTransferManager.throwAll(gui, pickedUpFromSlot);
            return true;
         } else {
            return false;
         }
      } else {
         return eventconsumed;
      }
   }

   public void afterSlotClick(GuiContainer gui, int slotIndex, int button, Slot slot, int modifier) {
      ItemStack nowHeld = NEIClientUtils.getHeldItem();
      if(this.firstheld != nowHeld) {
         pickedUpFromSlot = slotIndex;
      }

      if(NEIClientConfig.isActionPermissable(InterActionMap.ITEM) && NEIClientConfig.hasSMPCounterPart()) {
         if(heldStackInfinite != null && slot != null && slot.inventory == NEIClientUtils.mc().thePlayer.inventory) {
            ItemStack handler = slot.getStack();
            if(handler != null) {
               heldStackInfinite.onPlaceInfinite(handler);
            }

            NEIClientUtils.setSlotContents(slotIndex, handler, true);
         }

         if(this.firstheld != nowHeld) {
            heldStackInfinite = null;
         }

         if(this.firstheld != nowHeld && nowHeld != null) {
            Iterator var8 = ItemInfo.infiniteHandlers.iterator();

            while(var8.hasNext()) {
               IInfiniteItemHandler handler1 = (IInfiniteItemHandler)var8.next();
               if(handler1.canHandleItem(nowHeld) && handler1.isItemInfinite(nowHeld)) {
                  handler1.onPickup(nowHeld);
                  NEIClientUtils.setSlotContents(-999, nowHeld, true);
                  heldStackInfinite = handler1;
                  break;
               }
            }
         }
      }

   }

   public static void updateUnlimitedItems(InventoryPlayer inventory) {
      if(NEIClientConfig.isActionPermissable(InterActionMap.ITEM) && NEIClientConfig.hasSMPCounterPart()) {
         LinkedList beforeStacks = new LinkedList();

         int i;
         for(i = 0; i < inventory.getSizeInventory(); ++i) {
            beforeStacks.add(NEIServerUtils.copyStack(inventory.getStackInSlot(i)));
         }

         ItemStack newstack;
         for(i = 0; i < inventory.getSizeInventory(); ++i) {
            newstack = inventory.getStackInSlot(i);
            if(newstack != null) {
               Iterator var5 = ItemInfo.infiniteHandlers.iterator();

               while(var5.hasNext()) {
                  IInfiniteItemHandler handler = (IInfiniteItemHandler)var5.next();
                  if(handler.canHandleItem(newstack) && handler.isItemInfinite(newstack)) {
                     handler.replenishInfiniteStack(inventory, i);
                  }
               }
            }
         }

         for(i = 0; i < inventory.getSizeInventory(); ++i) {
            newstack = inventory.getStackInSlot(i);
            if(!NEIServerUtils.areStacksIdentical((ItemStack)beforeStacks.get(i), newstack)) {
               inventory.setInventorySlotContents(i, (ItemStack)beforeStacks.get(i));
               NEIClientUtils.setSlotContents(i, newstack, false);
            }
         }

      }
   }

   public static boolean mouseScrolled(int i) {
      Point mousePos = manager.getMousePosition();
      Slot mouseover = manager.window.getSlotAtPosition(mousePos.x, mousePos.y);
      return mouseover != null && mouseover.getHasStack();
   }

   public static void processCreativeCycling(InventoryPlayer inventory) {
      if(NEIClientConfig.invCreativeMode() && NEIClientUtils.controlKey() && selectedItem != inventory.currentItem) {
         if(inventory.currentItem != selectedItem + 1 && (inventory.currentItem != 0 || selectedItem != 8)) {
            if(inventory.currentItem == selectedItem - 1 || inventory.currentItem == 8 && selectedItem == 0) {
               NEICPH.sendCreativeScroll(-1);
               inventory.currentItem = selectedItem;
            }
         } else {
            NEICPH.sendCreativeScroll(1);
            inventory.currentItem = selectedItem;
         }
      }

      selectedItem = inventory.currentItem;
   }
}

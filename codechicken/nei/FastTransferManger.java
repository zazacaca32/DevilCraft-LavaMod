package codechicken.nei;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class FastTransferManger {

   public LinkedList slotZones = new LinkedList();
   public HashMap slotZoneMap = new HashMap();


   private void generateSlotMap(Container container, ItemStack stack) {
      stack = stack.copy();
      stack.stackSize = 1;

      for(int slotNo = 0; slotNo < container.inventorySlots.size(); ++slotNo) {
         if(!this.slotZoneMap.containsKey(Integer.valueOf(slotNo)) && container.getSlot(slotNo).isItemValid(stack)) {
            HashSet connectedSlots = new HashSet();
            this.findConnectedSlots(container, slotNo, connectedSlots);
            LinkedList zoneSlots = new LinkedList(connectedSlots);
            Collections.sort(zoneSlots, new FastTransferManger.SlotPositionComparator(container));
            this.slotZones.add(zoneSlots);
            Iterator var7 = zoneSlots.iterator();

            while(var7.hasNext()) {
               int i = ((Integer)var7.next()).intValue();
               this.slotZoneMap.put(Integer.valueOf(i), Integer.valueOf(this.slotZones.size() - 1));
            }
         }
      }

   }

   private void findConnectedSlots(Container container, int slotNo, HashSet connectedSlots) {
      connectedSlots.add(Integer.valueOf(slotNo));
      Slot slot = container.getSlot(slotNo);
      boolean threshold = true;

      for(int i = 0; i < container.inventorySlots.size(); ++i) {
         if(!connectedSlots.contains(Integer.valueOf(i))) {
            Slot slot1 = container.getSlot(i);
            if(Math.abs(slot.xDisplayPosition - slot1.xDisplayPosition) <= 18 && Math.abs(slot.yDisplayPosition - slot1.yDisplayPosition) <= 18) {
               this.findConnectedSlots(container, i, connectedSlots);
            }
         }
      }

   }

   public static int findSlotWithItem(Container container, ItemStack teststack) {
      for(int slotNo = 0; slotNo < container.inventorySlots.size(); ++slotNo) {
         ItemStack stack = container.getSlot(slotNo).getStack();
         if(stack != null && NEIServerUtils.areStacksSameType(stack, teststack)) {
            return slotNo;
         }
      }

      return -1;
   }

   public static void clearSlots(Container container) {
      for(int slotNo = 0; slotNo < container.inventorySlots.size(); ++slotNo) {
         ((Slot)container.inventorySlots.get(slotNo)).putStack((ItemStack)null);
      }

   }

   public void performMassTransfer(GuiContainer window, int fromSlot, int toSlot, ItemStack heldStack) {
      this.generateSlotMap(window.inventorySlots, heldStack);
      Integer fromZone = (Integer)this.slotZoneMap.get(Integer.valueOf(fromSlot));
      Integer toZone = (Integer)this.slotZoneMap.get(Integer.valueOf(toSlot));
      if(fromZone != null && toZone != null && fromZone != toZone) {
         if(NEIClientUtils.getHeldItem() == null || NEIServerUtils.areStacksSameType(heldStack, NEIClientUtils.getHeldItem())) {
            if(this.fillZoneWithHeldItem(window, toZone.intValue())) {
               Iterator var8 = ((LinkedList)this.slotZones.get(fromZone.intValue())).iterator();

               while(var8.hasNext()) {
                  int transferFrom = ((Integer)var8.next()).intValue();
                  ItemStack transferStack = window.inventorySlots.getSlot(transferFrom).getStack();
                  if(NEIServerUtils.areStacksSameType(heldStack, transferStack)) {
                     clickSlot(window, transferFrom);
                     if(!this.fillZoneWithHeldItem(window, toZone.intValue())) {
                        clickSlot(window, transferFrom);
                        return;
                     }
                  }
               }

            }
         }
      }
   }

   public int findShiftClickDestinationSlot(Container container, int fromSlot) {
      LinkedList save = this.saveContainer(container);
      Slot slot = container.getSlot(fromSlot);
      ItemStack stack = slot.getStack();
      if(stack == null) {
         return -1;
      } else {
         stack.stackSize = 1;
         slot.putStack(stack.copy());
         LinkedList compareBefore = this.saveContainer(container);
         container.slotClick(fromSlot, 0, 1, NEIClientUtils.mc().thePlayer);
         LinkedList compareAfter = this.saveContainer(container);

         try {
            for(int i = 0; i < compareBefore.size(); ++i) {
               if(i != fromSlot) {
                  ItemStack before = (ItemStack)compareBefore.get(i);
                  ItemStack after = (ItemStack)compareAfter.get(i);
                  if(!NEIServerUtils.areStacksIdentical(before, after) && after != null && (before == null && NEIServerUtils.areStacksSameType(stack, after) || NEIServerUtils.areStacksSameType(stack, after) && after.stackSize - before.stackSize > 0)) {
                     int var12 = i;
                     return var12;
                  }
               }
            }
         } finally {
            this.restoreContainer(container, save);
         }

         return -1;
      }
   }

   public LinkedList saveContainer(Container container) {
      LinkedList stacks = new LinkedList();

      for(int i = 0; i < container.inventorySlots.size(); ++i) {
         stacks.add(NEIServerUtils.copyStack(container.getSlot(i).getStack()));
      }

      return stacks;
   }

   public void restoreContainer(Container container, LinkedList items) {
      for(int i = 0; i < container.inventorySlots.size(); ++i) {
         container.getSlot(i).putStack((ItemStack)items.get(i));
      }

      container.slotClick(-999, 0, 0, NEIClientUtils.mc().thePlayer);
   }

   public void transferItem(GuiContainer window, int fromSlot) {
      int toSlot = this.findShiftClickDestinationSlot(window.inventorySlots, fromSlot);
      if(toSlot != -1) {
         Slot from = window.inventorySlots.getSlot(fromSlot);
         if(from.isItemValid(from.getStack())) {
            this.moveOneItem(window, fromSlot, toSlot);
         } else {
            this.moveOutputSet(window, fromSlot, toSlot);
         }

      }
   }

   public void moveOutputSet(GuiContainer window, int fromSlot, int toSlot) {
      if(NEIClientUtils.getHeldItem() == null) {
         clickSlot(window, fromSlot);
         if(NEIClientUtils.getHeldItem() != null) {
            clickSlot(window, toSlot);
         }
      }
   }

   public void moveOneItem(GuiContainer window, int fromSlot, int toSlot) {
      clickSlot(window, fromSlot);
      clickSlot(window, toSlot, 1);
      clickSlot(window, fromSlot);
   }

   public void retrieveItem(GuiContainer window, int toSlot) {
      Slot slot = window.inventorySlots.getSlot(toSlot);
      ItemStack slotStack = slot.getStack();
      if(slotStack != null && slotStack.stackSize != slot.getSlotStackLimit() && slotStack.stackSize != slotStack.getMaxStackSize()) {
         this.generateSlotMap(window.inventorySlots, slotStack);
         Integer destZone = (Integer)this.slotZoneMap.get(Integer.valueOf(toSlot));
         if(destZone != null) {
            int firstZoneSlot = this.findShiftClickDestinationSlot(window.inventorySlots, toSlot);
            int firstZone = -1;
            if(firstZoneSlot != -1) {
               Integer zone = (Integer)this.slotZoneMap.get(Integer.valueOf(firstZoneSlot));
               if(zone != null) {
                  firstZone = zone.intValue();
                  if(this.retrieveItemFromZone(window, firstZone, toSlot)) {
                     return;
                  }
               }
            }

            for(int var9 = 0; var9 < this.slotZones.size(); ++var9) {
               if(var9 != destZone.intValue() && var9 != firstZone && this.retrieveItemFromZone(window, var9, toSlot)) {
                  return;
               }
            }

            this.retrieveItemFromZone(window, destZone.intValue(), toSlot);
         }
      }
   }

   private boolean retrieveItemFromZone(GuiContainer window, int zone, int toSlot) {
      ItemStack stack = window.inventorySlots.getSlot(toSlot).getStack();
      Iterator var6 = ((LinkedList)this.slotZones.get(zone)).iterator();

      int i;
      Slot slot;
      ItemStack stack1;
      while(var6.hasNext()) {
         i = ((Integer)var6.next()).intValue();
         if(i != toSlot) {
            slot = window.inventorySlots.getSlot(i);
            stack1 = slot.getStack();
            if(NEIServerUtils.areStacksSameType(stack, stack1) && stack1.stackSize != slot.getSlotStackLimit() && stack1.stackSize != stack1.getMaxStackSize()) {
               this.moveOneItem(window, i, toSlot);
               return true;
            }
         }
      }

      var6 = ((LinkedList)this.slotZones.get(zone)).iterator();

      while(var6.hasNext()) {
         i = ((Integer)var6.next()).intValue();
         if(i != toSlot) {
            slot = window.inventorySlots.getSlot(i);
            stack1 = slot.getStack();
            if(NEIServerUtils.areStacksSameType(stack, stack1)) {
               this.moveOneItem(window, i, toSlot);
               return true;
            }
         }
      }

      return false;
   }

   public static void clickSlot(GuiContainer window, int slotIndex) {
      clickSlot(window, slotIndex, 0);
   }

   public static void clickSlot(GuiContainer window, int slotIndex, int button) {
      clickSlot(window, slotIndex, button, 0);
   }

   public static void clickSlot(GuiContainer window, int slotIndex, int button, int modifier) {
      Container container = window.inventorySlots;
      Slot slot = null;
      if(slotIndex >= 0 && slotIndex < container.inventorySlots.size()) {
         slot = container.getSlot(slotIndex);
      }

      window.handleMouseClick(slot, slotIndex, button, modifier);
   }

   private boolean fillZoneWithHeldItem(GuiContainer window, int zoneIndex) {
      Iterator var4 = ((LinkedList)this.slotZones.get(zoneIndex)).iterator();

      int transferTo;
      ItemStack held;
      ItemStack inToSlot;
      while(var4.hasNext()) {
         transferTo = ((Integer)var4.next()).intValue();
         held = NEIClientUtils.getHeldItem();
         if(held == null) {
            break;
         }

         inToSlot = window.inventorySlots.getSlot(transferTo).getStack();
         if(NEIServerUtils.areStacksSameType(inToSlot, held)) {
            clickSlot(window, transferTo);
         }
      }

      var4 = ((LinkedList)this.slotZones.get(zoneIndex)).iterator();

      while(var4.hasNext()) {
         transferTo = ((Integer)var4.next()).intValue();
         held = NEIClientUtils.getHeldItem();
         if(held == null) {
            break;
         }

         inToSlot = window.inventorySlots.getSlot(transferTo).getStack();
         if(inToSlot == null) {
            clickSlot(window, transferTo);
         }
      }

      return NEIClientUtils.getHeldItem() == null;
   }

   public void throwAll(GuiContainer window, int pickedUpFromSlot) {
      ItemStack held = NEIClientUtils.getHeldItem();
      if(held != null) {
         clickSlot(window, -999);
         this.generateSlotMap(window.inventorySlots, held);
         Iterator var5 = ((LinkedList)this.slotZones.get(((Integer)this.slotZoneMap.get(Integer.valueOf(pickedUpFromSlot))).intValue())).iterator();

         while(var5.hasNext()) {
            int slotIndex = ((Integer)var5.next()).intValue();
            Slot slot = window.inventorySlots.getSlot(slotIndex);
            if(NEIServerUtils.areStacksSameType(held, slot.getStack())) {
               clickSlot(window, slotIndex);
               clickSlot(window, -999);
            }
         }

      }
   }

   public static class SlotPositionComparator implements Comparator {

      Container container;


      public SlotPositionComparator(Container c) {
         this.container = c;
      }

      public int compare(Integer arg0, Integer arg1) {
         Slot slot1 = this.container.getSlot(arg0.intValue());
         Slot slot2 = this.container.getSlot(arg1.intValue());
         return slot2.yDisplayPosition != slot1.yDisplayPosition?slot1.yDisplayPosition - slot2.yDisplayPosition:slot1.xDisplayPosition - slot2.xDisplayPosition;
      }

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}
   }
}

package codechicken.nei;

import codechicken.core.inventory.InventoryRange;
import codechicken.core.inventory.InventoryUtils;
import codechicken.nei.FastTransferManger;
import codechicken.nei.ItemList;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.IInfiniteItemHandler;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.asm.NEIModContainer;
import codechicken.packager.Packager;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

public class NEIClientUtils extends NEIServerUtils {

   public static Minecraft mc() {
      return Minecraft.getMinecraft();
   }

   public static void reportException(Exception exception) {
      try {
         exception.printStackTrace();
         String exception1 = "nei " + (new SimpleDateFormat("d-M-y")).format(new Date()) + " at " + (new SimpleDateFormat("H.m.s.S")).format(new Date()) + ".txt";
         File file = new File(Minecraft.getMinecraftDir(), exception1);
         PrintWriter printwriter = new PrintWriter(new FileWriter(file));
         printwriter.print("[code]NEI Version: " + ((Packager)NEIModContainer.class.getAnnotation(Packager.class)).getVersion() + "\n");
         exception.printStackTrace(printwriter);
         printwriter.println("[/code]");
         printwriter.close();
         addChatMessage("Error written to " + exception1);
      } catch (Exception var4) {
         System.out.println("Error during safeReportException:");
         var4.printStackTrace();
      }

   }

   public static void addChatMessage(String s) {
      if(mc().ingameGUI != null) {
         mc().ingameGUI.getChatGUI().printChatMessage(s);
      }

   }

   public static void deleteHeldItem() {
      deleteSlotStack(-999);
   }

   public static void dropHeldItem() {
      mc().playerController.windowClick(((GuiContainer)mc().currentScreen).inventorySlots.windowId, -999, 0, 0, mc().thePlayer);
   }

   public static void deleteSlotStack(int slotNumber) {
      setSlotContents(slotNumber, (ItemStack)null, true);
   }

   public static void decreaseSlotStack(int slotNumber) {
      ItemStack stack = slotNumber == -999?getHeldItem():mc().thePlayer.openContainer.getSlot(slotNumber).getStack();
      if(stack != null) {
         if(stack.stackSize == 1) {
            deleteSlotStack(slotNumber);
         } else {
            stack = stack.copy();
            --stack.stackSize;
            setSlotContents(slotNumber, stack, true);
         }

      }
   }

   public static void deleteEverything() {
      NEICPH.sendDeleteAllItems();
   }

   public static void deleteItemsOfType(ItemStack itemstack) {
      Container c = getGuiContainer().inventorySlots;

      for(int i = 0; i < c.inventorySlots.size(); ++i) {
         Slot slot = c.getSlot(i);
         if(slot != null) {
            ItemStack itemstack1 = slot.getStack();
            if(itemstack1 != null && itemstack1.itemID == itemstack.itemID && itemstack1.getItemDamage() == itemstack.getItemDamage()) {
               setSlotContents(i, (ItemStack)null, true);
               slot.putStack((ItemStack)null);
            }
         }
      }

   }

   public static ItemStack getHeldItem() {
      return mc().thePlayer.inventory.getItemStack();
   }

   public static void setSlotContents(int slot, ItemStack item, boolean containerInv) {
      NEICPH.sendSetSlot(slot, item, containerInv);
      if(slot == -999) {
         mc().thePlayer.inventory.setItemStack(item);
      }

   }

   public static void cheatItem(ItemStack typeStack, int button, int mode) {
      if(NEIClientConfig.isActionPermissable("item")) {
         if(mode == -1 && button == 0 && shiftKey()) {
            Iterator var4 = ItemInfo.infiniteHandlers.iterator();

            while(var4.hasNext()) {
               IInfiniteItemHandler amount1 = (IInfiniteItemHandler)var4.next();
               if(amount1.canHandleItem(typeStack)) {
                  ItemStack stack = amount1.getInfiniteItem(typeStack);
                  if(stack != null) {
                     giveStack(stack, stack.stackSize, true);
                     return;
                  }
               }
            }

            cheatItem(typeStack, button, 0);
         } else if(button == 1) {
            giveStack(typeStack, 1);
         } else if(mode == 1 && typeStack.stackSize < typeStack.getMaxStackSize()) {
            giveStack(typeStack, typeStack.getMaxStackSize() - typeStack.stackSize);
         } else {
            int amount = NEIClientConfig.getItemQuantity();
            if(amount == 0) {
               amount = typeStack.getMaxStackSize();
            }

            giveStack(typeStack, amount);
         }

      }
   }

   public static void giveStack(ItemStack itemstack) {
      giveStack(itemstack, itemstack.stackSize);
   }

   public static void giveStack(ItemStack itemstack, int i) {
      giveStack(itemstack, i, false);
   }

   public static void giveStack(ItemStack itemstack, int i, boolean infinite) {
      ItemStack itemstack1 = copyStack(itemstack, i);
      int qty2;
      if(NEIClientConfig.hasSMPCounterPart()) {
         ItemStack given = copyStack(itemstack1, 1);
         if(!infinite && !canItemFitInInventory(mc().thePlayer, itemstack1) && mc().currentScreen instanceof GuiContainer) {
            GuiContainer qty = getGuiContainer();
            int increment = given.getMaxStackSize();

            int given1;
            int qty1;
            for(given1 = 0; given1 < itemstack1.stackSize; given1 += qty1) {
               qty1 = Math.min(itemstack1.stackSize - given1, increment);
               int slotNo = -1;
               Iterator current = GuiInfo.guiHandlers.iterator();

               while(current.hasNext()) {
                  INEIGuiHandler slot = (INEIGuiHandler)current.next();
                  slotNo = slot.getItemSpawnSlot(qty, given);
                  if(slotNo >= 0) {
                     break;
                  }
               }

               if(slotNo == -1) {
                  break;
               }

               Slot slot1 = qty.inventorySlots.getSlot(slotNo);
               int current1 = slot1.getHasStack()?slot1.getStack().stackSize:0;
               qty1 = Math.min(qty1, slot1.getSlotStackLimit() - current1);
               ItemStack newStack = copyStack(given, qty1 + current1);
               slot1.putStack(newStack);
               setSlotContents(slotNo, newStack, true);
            }

            NEICPH.sendSpawnItem(copyStack(given, given1), infinite, false);
         } else {
            NEICPH.sendSpawnItem(itemstack1, infinite, true);
         }
      } else {
         for(int given2 = 0; given2 < itemstack1.stackSize; given2 += qty2) {
            qty2 = Math.min(itemstack1.stackSize - given2, itemstack1.getMaxStackSize());
            sendCommand(NEIClientConfig.getStringSetting("command.give"), new Object[]{mc().thePlayer.username, Integer.valueOf(itemstack1.itemID), Integer.valueOf(qty2), Integer.valueOf(itemstack1.getItemDamage())});
         }
      }

   }

   public static void updateUnlimitedItems() {
      ItemStack itemstack = getHeldItem();
      if(itemstack != null && itemstack.stackSize > 64) {
         itemstack.stackSize = 1;
      }

      ItemStack[] aitemstack = mc().thePlayer.inventory.mainInventory;

      for(int slot = 0; slot < aitemstack.length; ++slot) {
         ItemStack itemstack1 = aitemstack[slot];
         if(itemstack1 != null) {
            if(itemstack1.stackSize < 0 || itemstack1.stackSize > 64) {
               itemstack1.stackSize = 111;
            }

            if(itemstack1.getItemDamage() > -32000 && itemstack1.getItemDamage() < -30000) {
               itemstack1.setItemDamage(-32000);
            }
         }
      }

   }

   public static boolean isValidItem(ItemStack test) {
      Iterator var2 = ItemList.items.iterator();

      while(var2.hasNext()) {
         ItemStack stack = (ItemStack)var2.next();
         if(areStacksIdentical(stack, test)) {
            return true;
         }
      }

      return false;
   }

   public static boolean canItemFitInInventory(EntityPlayer player, ItemStack itemstack) {
      return InventoryUtils.getInsertableQuantity(new InventoryRange(player.inventory, 0, 36), itemstack) > 0;
   }

   public static boolean shiftKey() {
      return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
   }

   public static boolean controlKey() {
      return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
   }

   public static int getCreativeMode() {
      return NEIClientConfig.invCreativeMode()?2:(mc().playerController.isInCreativeMode()?1:0);
   }

   public static void cycleCreativeMode() {
      int mode = getCreativeMode();
      if(NEIClientConfig.hasSMPCounterPart()) {
         NEICPH.sendCycleCreativeMode();
      } else {
         sendCommand(NEIClientConfig.getStringSetting("command.creative"), new Object[]{Integer.valueOf(mode ^ 1), mc().thePlayer.username});
      }

   }

   public static long getTime() {
      return mc().theWorld.getWorldInfo().getWorldTime();
   }

   public static void setTime(long l) {
      mc().theWorld.getWorldInfo().setWorldTime(l);
   }

   public static void setHourForward(int hour) {
      long day = getTime() / 24000L * 24000L;
      long newTime = day + 24000L + (long)(hour * 1000);
      if(NEIClientConfig.hasSMPCounterPart()) {
         NEICPH.sendSetTime(hour);
      } else {
         sendCommand(NEIClientConfig.getStringSetting("command.time"), new Object[]{Long.valueOf(newTime)});
      }

   }

   public static void sendCommand(String command, Object ... args) {
      NumberFormat numberformat = NumberFormat.getIntegerInstance();
      numberformat.setGroupingUsed(false);
      MessageFormat messageformat = new MessageFormat(command);

      for(int i = 0; i < args.length; ++i) {
         if(args[i] instanceof Integer || args[i] instanceof Long) {
            messageformat.setFormatByArgumentIndex(i, numberformat);
         }
      }

      mc().thePlayer.sendChatMessage(messageformat.format(args));
   }

   public static boolean isRaining() {
      return mc().theWorld.getWorldInfo().isRaining();
   }

   public static void toggleRaining() {
      if(NEIClientConfig.hasSMPCounterPart()) {
         NEICPH.sendToggleRain();
      } else {
         sendCommand(NEIClientConfig.getStringSetting("command.rain"), new Object[]{Integer.valueOf(isRaining()?0:1)});
      }

   }

   public static void healPlayer() {
      if(NEIClientConfig.hasSMPCounterPart()) {
         NEICPH.sendHeal();
      } else {
         sendCommand(NEIClientConfig.getStringSetting("command.heal"), new Object[]{mc().thePlayer.username});
      }

   }

   public static void toggleMagnetMode() {
      if(NEIClientConfig.hasSMPCounterPart()) {
         NEICPH.sendToggleMagnetMode();
      }

   }

   public static ArrayList concatIntegersToRanges(List damages) {
      ArrayList ranges = new ArrayList();
      if(damages.size() == 0) {
         return ranges;
      } else {
         Collections.sort(damages);
         int start = -1;
         int next = 0;
         Iterator var5 = damages.iterator();

         while(var5.hasNext()) {
            Integer i = (Integer)var5.next();
            if(start == -1) {
               start = next = i.intValue();
            } else if(next + 1 != i.intValue()) {
               ranges.add(new int[]{start, next});
               start = next = i.intValue();
            } else {
               next = i.intValue();
            }
         }

         ranges.add(new int[]{start, next});
         return ranges;
      }
   }

   public static ArrayList addIntegersToRanges(List ranges, List damages) {
      Iterator var3 = ranges.iterator();

      while(var3.hasNext()) {
         int[] range = (int[])var3.next();

         for(int integer = range[0]; integer <= range[1]; ++integer) {
            damages.add(Integer.valueOf(integer));
         }
      }

      return concatIntegersToRanges(damages);
   }

   public static void dumpIDs() {
      try {
         boolean e = NEIClientConfig.getBooleanSetting("ID dump.blockIDs");
         boolean items = NEIClientConfig.getBooleanSetting("ID dump.itemIDs");
         boolean unusedblocks = NEIClientConfig.getBooleanSetting("ID dump.unused blockIDs");
         boolean unuseditems = NEIClientConfig.getBooleanSetting("ID dump.unused itemIDs");
         String s = "IDMap dump " + (new SimpleDateFormat("d-M-y")).format(new Date()) + " at " + (new SimpleDateFormat("H.m.s.S")).format(new Date()) + ".txt";
         File writeTo = new File(Minecraft.getMinecraftDir(), s);
         if(!writeTo.exists()) {
            writeTo.createNewFile();
         }

         PrintWriter writer = new PrintWriter(writeTo);

         for(int i = 1; i < Item.itemsList.length; ++i) {
            String name;
            if(i < Block.blocksList.length && Block.blocksList[i] != null && Block.blocksList[i].blockID != 0) {
               if(e) {
                  Block var11 = Block.blocksList[i];
                  name = var11.getUnlocalizedName();
                  if(name == null) {
                     name = var11.getClass().getCanonicalName();
                  }

                  writer.println("Block. Name: " + name + ". ID: " + i);
               }
            } else if(Item.itemsList[i] != null) {
               if(items) {
                  Item item = Item.itemsList[i];
                  name = item.getUnlocalizedName();
                  if(name == null) {
                     name = item.getClass().getCanonicalName();
                  }

                  writer.println("Item. Name: " + name + ". ID: " + i);
               }
            } else if(i < Block.blocksList.length) {
               if(unusedblocks) {
                  writer.println("Block. Unused ID: " + i);
               }
            } else if(unuseditems) {
               writer.println("Item. Unused ID: " + i);
            }
         }

         writer.close();
         addChatMessage("Dumped IDMap to " + s);
      } catch (Exception var10) {
         reportException(var10);
      }

   }

   public static boolean safeKeyDown(int keyCode) {
      try {
         return Keyboard.isKeyDown(keyCode);
      } catch (IndexOutOfBoundsException var2) {
         return false;
      }
   }

   public static void setItemQuantity(int i) {
      NEIClientConfig.setItemQuantity(i);
      LayoutManager.quantity.setText(Integer.toString(i));
   }

   public static GuiContainer getGuiContainer() {
      return mc().currentScreen instanceof GuiContainer?(GuiContainer)mc().currentScreen:null;
   }

   public static void overlayScreen(GuiScreen gui) {
      if(mc().currentScreen instanceof GuiContainer) {
         FastTransferManger.clickSlot(getGuiContainer(), -999);
      }

      mc().currentScreen = null;
      mc().displayGuiScreen(gui);
   }

   public static boolean altKey() {
      return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
   }
}

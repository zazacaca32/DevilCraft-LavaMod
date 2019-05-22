package codechicken.nei;

import codechicken.core.CommonUtils;
import codechicken.core.ServerUtils;
import codechicken.core.inventory.InventoryUtils;
import codechicken.nei.AllowedPropertyMap;
import codechicken.nei.NEISPH;
import codechicken.nei.NEIServerConfig;
import codechicken.nei.PlayerSave;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class NEIServerUtils {

   public static boolean isRaining(World world) {
      return world.getWorldInfo().isRaining();
   }

   public static void toggleRaining(World world, boolean notify) {
      boolean raining = !world.isRaining();
      if(!raining) {
         ((WorldServer)world).provider.resetRainAndThunder();
      } else {
         world.toggleRain();
      }

      if(notify) {
         ServerUtils.sendChatToAll("Дождь " + (raining?"включен.":"выключен."));
      }

   }

   public static void healPlayer(EntityPlayer player) {
      player.heal(20);
      player.getFoodStats().addStats(20, 1.0F);
      player.extinguish();
   }

   public static long getTime(World world) {
      return world.getWorldInfo().getWorldTime();
   }

   public static void setTime(long l, World world) {
      world.getWorldInfo().setWorldTime(l);
   }

   public static void setSlotContents(EntityPlayer player, int slot, ItemStack item, boolean containerInv) {
      if(slot == -999) {
         player.inventory.setItemStack(item);
      } else if(containerInv) {
         player.openContainer.putStackInSlot(slot, item);
      } else {
         player.inventory.setInventorySlotContents(slot, item);
      }

   }

   public static void deleteAllItems(EntityPlayerMP player) {
      for(int i = 0; i < player.openContainer.inventorySlots.size(); ++i) {
         ((Slot)player.openContainer.inventorySlots.get(i)).putStack((ItemStack)null);
      }

      player.sendContainerAndContentsToPlayer(player.openContainer, player.openContainer.getInventory());
   }

   public static void setHourForward(World world, int hour, boolean notify) {
      long day = getTime(world) / 24000L * 24000L;
      long newTime = day + 24000L + (long)(hour * 1000);
      setTime(newTime, world);
      if(notify) {
         ServerUtils.sendChatToAll("День " + getTime(world) / 24000L + ". " + hour + ":00");
      }

   }

   public static void advanceDisabledTimes(World world) {
      int dim = CommonUtils.getDimension(world);
      int hour = (int)(getTime(world) % 24000L) / 1000;
      int newhour = hour;

      while(true) {
         int zone = newhour / 6;

         try {
            if(!NEIServerConfig.isPropertyDisabled(dim, (String)AllowedPropertyMap.idToNameMap.get(Integer.valueOf(zone)))) {
               break;
            }

            newhour = (zone + 1) % 4 * 6;
         } catch (NumberFormatException var7) {
            String s = "Время: " + getTime(world) + ", Час: " + hour + ", Нов.Час: " + newhour + ", Зона: " + zone + ", Карта: " + AllowedPropertyMap.idToNameMap.toString();
            ServerUtils.mc().logSevere(s);
            throw var7;
         }
      }

      if(newhour != hour) {
         setHourForward(world, newhour, false);
      }

   }

   public static boolean canItemFitInInventory(EntityPlayer player, ItemStack itemstack) {
      int i;
      for(i = 0; i < player.inventory.getSizeInventory() - 4; ++i) {
         if(player.inventory.getStackInSlot(i) == null) {
            return true;
         }
      }

      if(!itemstack.isItemDamaged()) {
         if(itemstack.getMaxStackSize() == 1) {
            return false;
         }

         for(i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack invstack = player.inventory.getStackInSlot(i);
            if(invstack != null && invstack.itemID == itemstack.itemID && invstack.isStackable() && invstack.stackSize < invstack.getMaxStackSize() && invstack.stackSize < player.inventory.getInventoryStackLimit() && (!invstack.getHasSubtypes() || invstack.getItemDamage() == itemstack.getItemDamage())) {
               return true;
            }
         }
      }

      return false;
   }

   public static int getSlotForStack(Container c, int firstSlot, int lastSlot, ItemStack item) {
      int slotIndex;
      Slot slot;
      for(slotIndex = firstSlot; slotIndex < lastSlot; ++slotIndex) {
         slot = c.getSlot(slotIndex);
         if(slot.getHasStack() && item.isStackable()) {
            int filled = slot.getStack().stackSize;
            if(filled < slot.getSlotStackLimit() && filled < item.getMaxStackSize() && areStacksSameType(slot.getStack(), item)) {
               return slotIndex;
            }
         }
      }

      for(slotIndex = firstSlot; slotIndex < lastSlot; ++slotIndex) {
         slot = c.getSlot(slotIndex);
         if(!slot.getHasStack()) {
            return slotIndex;
         }
      }

      return -1;
   }

   public static int getSlotForStack(IInventory inv, int firstSlot, int lastSlot, ItemStack item) {
      int slotIndex;
      for(slotIndex = firstSlot; slotIndex < lastSlot; ++slotIndex) {
         ItemStack slotStack = inv.getStackInSlot(slotIndex);
         if(slotStack != null && item.isStackable()) {
            int filled = slotStack.stackSize;
            if(filled < inv.getInventoryStackLimit() && filled < item.getMaxStackSize() && areStacksSameType(slotStack, item)) {
               return slotIndex;
            }
         }
      }

      for(slotIndex = firstSlot; slotIndex < lastSlot; ++slotIndex) {
         if(inv.getStackInSlot(slotIndex) == null) {
            return slotIndex;
         }
      }

      return -1;
   }

   public static void sendNotice(String s, String permission) {
      sendNotice(s, permission, -1);
   }

   public static void sendNotice(String s, String permission, int colour) {
      if(NEIServerConfig.canPlayerUseFeature("CONSOLE", permission)) {
         Logger.getLogger("Minecraft").info(s.replaceAll("§.", ""));
      }

      Iterator var4 = ServerUtils.mc().getConfigurationManager().playerEntityList.iterator();

      while(var4.hasNext()) {
         EntityPlayerMP player = (EntityPlayerMP)var4.next();
         if(NEIServerConfig.canPlayerUseFeature(player.username, permission)) {
            ServerUtils.sendChatTo(player, s);
         }
      }

   }

   public static boolean areStacksSameType(ItemStack stack1, ItemStack stack2) {
      return InventoryUtils.canStack(stack1, stack2);
   }

   public static boolean areStacksSameTypeCrafting(ItemStack stack1, ItemStack stack2) {
      return stack1 != null && stack2 != null?stack1.itemID == stack2.itemID && (stack1.getItemDamage() == stack2.getItemDamage() || stack1.getItemDamage() == -1 || stack2.getItemDamage() == -1 || stack1.getItem().isDamageable()):false;
   }

   public static int compareStacks(ItemStack stack1, ItemStack stack2) {
      return stack1 == stack2?0:(stack1 != null && stack2 != null?(stack1.itemID != stack2.itemID?stack1.itemID - stack2.itemID:(stack1.stackSize != stack2.stackSize?stack1.stackSize - stack2.stackSize:stack1.getItemDamage() - stack2.getItemDamage())):(stack1 == null?-1:1));
   }

   public static boolean areStacksIdentical(ItemStack stack1, ItemStack stack2) {
      return compareStacks(stack1, stack2) == 0;
   }

   public static void givePlayerItem(EntityPlayerMP player, ItemStack stack, boolean infinite, LinkedList name, boolean doGive) {
      if(stack.getItem() == null) {
         player.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat("§fНет такого пункта."));
      } else {
         StringBuilder namebuilder = new StringBuilder();
         boolean first = true;

         String itemname;
         for(Iterator given = name.iterator(); given.hasNext(); first = false) {
            itemname = (String)given.next();
            if(!first) {
               namebuilder.append(" ");
            }

            namebuilder.append(itemname.trim());
         }

         itemname = namebuilder.toString();
         int given1 = 0;
         if(!doGive) {
            given1 = stack.stackSize;
         } else if(infinite) {
            player.inventory.addItemStackToInventory(stack);
         } else {
            int increment = stack.getMaxStackSize();

            int qty;
            for(given1 = 0; given1 < stack.stackSize; given1 += qty) {
               qty = Math.min(stack.stackSize - given1, increment);
               int slotNo = getSlotForStack((IInventory)player.inventory, 0, 36, stack);
               if(slotNo == -1) {
                  break;
               }

               ItemStack slotStack = player.inventory.getStackInSlot(slotNo);
               int current = slotStack != null?slotStack.stackSize:0;
               qty = Math.min(qty, player.inventory.getInventoryStackLimit() - current);
               player.inventory.setInventorySlotContents(slotNo, copyStack(stack, qty + current));
            }
         }

         if(infinite) {
            sendNotice("Выдано " + player.username + " infinite " + "§f" + itemname, "notify-item");
         } else {
            sendNotice("Выдано " + player.username + " " + given1 + " из " + "§f" + itemname, "notify-item");
         }

         player.openContainer.detectAndSendChanges();
      }
   }

   public static ItemStack copyStack(ItemStack itemstack, int i) {
      if(itemstack == null) {
         return null;
      } else {
         itemstack.stackSize += i;
         return itemstack.splitStack(i);
      }
   }

   public static ItemStack copyStack(ItemStack itemstack) {
      return itemstack == null?null:copyStack(itemstack, itemstack.stackSize);
   }

   public static boolean isMagnetMode(EntityPlayerMP player) {
      return NEIServerConfig.forPlayer(player.username).getMagnetMode();
   }

   public static void toggleMagnetMode(EntityPlayerMP player) {
      PlayerSave playerSave = NEIServerConfig.forPlayer(player.username);
      playerSave.setMagnetMode(!playerSave.getMagnetMode());
      NEISPH.sendMagnetModeTo(player, playerSave.getMagnetMode());
   }

   public static int getCreativeMode(EntityPlayerMP player) {
      return NEIServerConfig.forPlayer(player.username).getCreativeInv()?2:(player.theItemInWorldManager.isCreative()?1:0);
   }

   public static void toggleCreativeMode(EntityPlayerMP player) {
      int mode = (getCreativeMode(player) + 1) % 3;
      player.theItemInWorldManager.setGameType(mode == 0?EnumGameType.SURVIVAL:EnumGameType.CREATIVE);
      NEIServerConfig.forPlayer(player.username).setCreativeInv(mode == 2);
      NEISPH.sendCreativeModeTo(player, mode);
   }

   public static void cycleCreativeInv(EntityPlayerMP player, int steps) {
      InventoryPlayer inventory = player.inventory;
      ItemStack[][] slots = new ItemStack[10][9];
      PlayerSave playerSave = NEIServerConfig.forPlayer(player.username);

      int newslots;
      for(newslots = 0; newslots < 9; ++newslots) {
         slots[9][newslots] = inventory.mainInventory[newslots];
      }

      int row;
      for(newslots = 0; newslots < 3; ++newslots) {
         for(row = 0; row < 9; ++row) {
            slots[newslots + 6][row] = inventory.mainInventory[(newslots + 1) * 9 + row];
         }
      }

      for(newslots = 0; newslots < 6; ++newslots) {
         for(row = 0; row < 9; ++row) {
            slots[newslots][row] = playerSave.creativeInv[newslots * 9 + row];
         }
      }

      ItemStack[][] var8 = new ItemStack[10][];

      for(row = 0; row < 10; ++row) {
         var8[(row + steps + 10) % 10] = slots[row];
      }

      for(row = 0; row < 9; ++row) {
         inventory.mainInventory[row] = var8[9][row];
      }

      int col;
      for(row = 0; row < 3; ++row) {
         for(col = 0; col < 9; ++col) {
            inventory.mainInventory[(row + 1) * 9 + col] = var8[row + 6][col];
         }
      }

      for(row = 0; row < 6; ++row) {
         for(col = 0; col < 9; ++col) {
            playerSave.creativeInv[row * 9 + col] = var8[row][col];
         }
      }

      playerSave.setDirty();
   }

   public static List getEnchantments(ItemStack itemstack) {
      ArrayList arraylist = new ArrayList();
      if(itemstack != null) {
         NBTTagList nbttaglist = itemstack.getEnchantmentTagList();
         if(nbttaglist != null) {
            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               short word0 = ((NBTTagCompound)nbttaglist.tagAt(i)).getShort("id");
               short word1 = ((NBTTagCompound)nbttaglist.tagAt(i)).getShort("lvl");
               int[] ai = new int[]{word0, word1};
               arraylist.add(ai);
            }
         }
      }

      return arraylist;
   }

   public static boolean stackHasEnchantment(ItemStack itemstack, int e) {
      List allenchantments = getEnchantments(itemstack);
      Iterator var4 = allenchantments.iterator();

      while(var4.hasNext()) {
         int[] ai = (int[])var4.next();
         if(ai[0] == e) {
            return true;
         }
      }

      return false;
   }

   public static int getEnchantmentLevel(ItemStack itemstack, int e) {
      List allenchantments = getEnchantments(itemstack);
      Iterator var4 = allenchantments.iterator();

      while(var4.hasNext()) {
         int[] ai = (int[])var4.next();
         if(ai[0] == e) {
            return ai[1];
         }
      }

      return -1;
   }

   public static boolean doesEnchantmentConflict(List enchantments, Enchantment e) {
      Iterator var3 = enchantments.iterator();

      while(var3.hasNext()) {
         int[] ai = (int[])var3.next();
         if(!e.canApplyTogether(Enchantment.enchantmentsList[ai[0]])) {
            return true;
         }
      }

      return false;
   }
}

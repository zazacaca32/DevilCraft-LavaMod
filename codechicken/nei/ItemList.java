package codechicken.nei;

import codechicken.core.CommonUtils;
import codechicken.core.inventory.ItemKey;
import codechicken.nei.DropDownFile;
import codechicken.nei.ItemPanel;
import codechicken.nei.ItemPanelStack;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.SubSetRangeTag;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.forge.GuiContainerManager;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemList {

   public static ArrayList items = new ArrayList();
   private static boolean matching = false;
   private static boolean loading = false;
   private static boolean research = false;
   private static boolean reload = false;
   private static HashSet erroredIDs = new HashSet();
   private static HashSet stackTraces = new HashSet();


   public static ItemList.ItemMatcher getSearchMatcher() {
      String matchstring = NEIClientConfig.getSearchExpression().toLowerCase();
      if(matchstring.startsWith("@") && matchstring.length() > 1) {
         LinkedList pattern1 = new LinkedList();

         try {
            String e = matchstring.substring(1);
            Iterator var4 = DropDownFile.dropDownInstance.allTags().iterator();

            while(var4.hasNext()) {
               SubSetRangeTag tag = (SubSetRangeTag)var4.next();
               if(CommonUtils.filterText(tag.qualifiedname).toLowerCase().contains(e)) {
                  pattern1.add(tag);
               }
            }

            if(pattern1.isEmpty()) {
               return new ItemList.NothingItemMatcher((ItemList.NothingItemMatcher)null);
            } else {
               return new ItemList.SubsetItemMatcher(pattern1);
            }
         } catch (PatternSyntaxException var5) {
            return new ItemList.NothingItemMatcher((ItemList.NothingItemMatcher)null);
         }
      } else {
         matchstring = matchstring.replace(".", "");
         matchstring = matchstring.replace("?", ".");
         matchstring = matchstring.replace("*", ".+?");

         Pattern pattern;
         try {
            pattern = Pattern.compile(matchstring);
         } catch (PatternSyntaxException var6) {
            return new ItemList.EverythingItemMatcher((ItemList.EverythingItemMatcher)null);
         }

         return (ItemList.ItemMatcher)(pattern != null && !pattern.toString().equals("")?new ItemList.PatternItemMatcher(pattern):new ItemList.EverythingItemMatcher((ItemList.EverythingItemMatcher)null));
      }
   }

   public static boolean itemMatchesSearch(ItemStack item) {
      return getSearchMatcher().matches(item);
   }

   public static boolean isMatching() {
      return matching;
   }

   public static void updateSearch() {
      if(matching) {
         research = true;
      } else {
         (new ItemList.ThreadMatchSearch()).start();
      }

   }

   public static void loadItems() {
      if(loading) {
         reload = true;
      } else {
         (new ItemList.ThreadLoadItems()).start();
      }

   }

   private interface ItemMatcher {

      boolean matches(ItemStack var1);
   }

   public static class TimeoutException extends RuntimeException {

      public final int itemID;


      public TimeoutException(String msg, int lastItem) {
         super(msg);
         this.itemID = lastItem;
      }
   }

   private static class EverythingItemMatcher implements ItemList.ItemMatcher {

      private EverythingItemMatcher() {}

      public boolean matches(ItemStack item) {
         return true;
      }

      // $FF: synthetic method
      EverythingItemMatcher(ItemList.EverythingItemMatcher var1) {
         this();
      }
   }

   public interface IItemCounter {

      int getItem();

      Thread getThread();
   }

   private static class PatternItemMatcher implements ItemList.ItemMatcher {

      Pattern pattern;


      public PatternItemMatcher(Pattern pattern) {
         this.pattern = pattern;
      }

      public boolean matches(ItemStack item) {
         return this.pattern.matcher(CommonUtils.filterText(GuiContainerManager.concatenatedDisplayName(item, true).toLowerCase())).find();
      }
   }

   public static class ThreadLoadMonitor extends Thread {

      ItemList.IItemCounter loadingThread;


      public ThreadLoadMonitor(ItemList.IItemCounter handle) {
         super("NEI Load Monitor");
         this.loadingThread = handle;
      }

      public void run() {
         int lastItem = 0;
         long lastTime = System.currentTimeMillis();

         while(this.loadingThread.getThread().isAlive()) {
            if(lastItem != this.loadingThread.getItem()) {
               lastTime = System.currentTimeMillis();
               lastItem = this.loadingThread.getItem();
            } else if(System.currentTimeMillis() - lastTime > 2000L && lastItem >= 0) {
               this.loadingThread.getThread().stop(new ItemList.TimeoutException("Took to long to advance item", lastItem));
            }

            try {
               Thread.sleep(2000L);
            } catch (InterruptedException var5) {
               ;
            }
         }

      }
   }

   private static class SubsetItemMatcher implements ItemList.ItemMatcher {

      final List tags;


      public SubsetItemMatcher(List tags) {
         this.tags = tags;
      }

      public boolean matches(ItemStack item) {
         Iterator var3 = this.tags.iterator();

         while(var3.hasNext()) {
            SubSetRangeTag tag = (SubSetRangeTag)var3.next();
            if(tag.isItemInRange(item.itemID, item.getItemDamage())) {
               return true;
            }
         }

         return false;
      }
   }

   public static class ThreadLoadItems extends Thread implements ItemList.IItemCounter {

      private int itemID = 0;


      public ThreadLoadItems() {
         super("NEI Item Loading Thread");
         ItemList.loading = true;
      }

      public int getItem() {
         return this.itemID;
      }

      public Thread getThread() {
         return this;
      }

      public void run() {
         (new ItemList.ThreadLoadMonitor(this)).start();

         label132:
         while(ItemList.loading) {
            try {
               ArrayList e = new ArrayList();
               ArrayList sublist = new ArrayList();

               for(this.itemID = 0; this.itemID < Item.itemsList.length; ++this.itemID) {
                  if(ItemList.reload) {
                     ItemList.reload = false;
                     continue label132;
                  }

                  Item dropDownInstance = Item.itemsList[this.itemID];
                  if(dropDownInstance != null && !ItemInfo.isHidden(dropDownInstance.itemID)) {
                     sublist.clear();
                     dropDownInstance.getSubItems(this.itemID, (CreativeTabs)null, sublist);
                     ArrayList stack = ItemInfo.getItemDamageVariants(dropDownInstance.itemID);
                     if(sublist.size() > 0) {
                        ArrayList skipDamage0 = new ArrayList();
                        Iterator damageIconSet = sublist.iterator();

                        while(damageIconSet.hasNext()) {
                           ItemStack datalist = (ItemStack)damageIconSet.next();
                           if(datalist.hasTagCompound()) {
                              datalist = datalist.copy();
                              e.add(datalist);
                           } else {
                              skipDamage0.add(Integer.valueOf(datalist.getItemDamage()));
                           }
                        }

                        if(stack == ItemInfo.defaultDamageRange) {
                           stack = NEIClientUtils.concatIntegersToRanges(skipDamage0);
                        } else {
                           stack = NEIClientUtils.addIntegersToRanges(stack, skipDamage0);
                        }
                     }

                     boolean var19 = false;
                     ArrayList var21 = ItemInfo.getItemCompounds(this.itemID);
                     if(var21 != null && var21.size() > 0 && NEIClientConfig.isActionPermissable("nbt")) {
                        var19 = true;
                        Iterator damagerange = var21.iterator();

                        while(damagerange.hasNext()) {
                           ItemStack var22 = (ItemStack)damagerange.next();
                           var22 = var22.copy();
                           e.add(var22);
                        }
                     }

                     HashSet var23 = new HashSet();
                     Iterator var9 = stack.iterator();

                     while(var9.hasNext()) {
                        int[] var24 = (int[])var9.next();

                        for(int damage = var24[0]; damage <= var24[1]; ++damage) {
                           ItemStack itemstack = new ItemStack(dropDownInstance, 1, damage);

                           String stackTrace;
                           try {
                              Icon t = dropDownInstance.getIconIndex(itemstack);
                              String var25 = GuiContainerManager.concatenatedDisplayName(itemstack, false);
                              stackTrace = var25 + "@" + (t == null?0:t.hashCode());
                              if(!var23.contains(stackTrace)) {
                                 var23.add(stackTrace);
                                 if(damage != 0 || !var19) {
                                    e.add(itemstack);
                                 }
                              }
                           } catch (Throwable var15) {
                              StringWriter sw = new StringWriter();
                              var15.printStackTrace(new PrintWriter(sw));
                              stackTrace = itemstack + sw.toString();
                              if(!ItemList.stackTraces.contains(stackTrace)) {
                                 System.err.println("NEI: Omitting #" + this.itemID + ":" + damage + " " + dropDownInstance.getClass().getSimpleName());
                                 var15.printStackTrace();
                                 ItemList.stackTraces.add(stackTrace);
                              }
                           }
                        }
                     }
                  }
               }

               this.itemID = -1;
               DropDownFile var17 = DropDownFile.dropDownInstance;
               var17.resetHashes();
               Iterator var20 = e.iterator();

               while(var20.hasNext()) {
                  ItemStack var18 = (ItemStack)var20.next();
                  if(ItemList.reload) {
                     ItemList.reload = false;
                     continue label132;
                  }

                  var17.addItemIfInRange(var18.itemID, var18.getItemDamage(), var18.stackTagCompound);
               }

               var17.updateState();
               ItemList.items = e;
               if(ItemList.reload) {
                  ItemList.reload = false;
               } else {
                  ItemList.loading = false;
               }
            } catch (ItemList.TimeoutException var16) {
               System.err.println("Removing itemID: " + var16.itemID + " from list.");
               ItemList.erroredIDs.add(Integer.valueOf(var16.itemID));
            }
         }

         ItemList.updateSearch();
      }
   }

   public static class ThreadMatchSearch extends Thread implements ItemList.IItemCounter {

      private int itemID;


      public ThreadMatchSearch() {
         super("NEI Item Searching Thread");
         ItemList.matching = true;
      }

      public int getItem() {
         return this.itemID;
      }

      public Thread getThread() {
         return this;
      }

      public void run() {
         label46:
         while(ItemList.matching) {
            try {
               ArrayList e = new ArrayList();
               ItemList.ItemMatcher matcher = ItemList.getSearchMatcher();
               Iterator var4 = ItemList.items.iterator();

               while(var4.hasNext()) {
                  ItemStack item = (ItemStack)var4.next();
                  this.itemID = item.itemID;
                  if(ItemList.research) {
                     ItemList.research = false;
                     continue label46;
                  }

                  if(item.hasTagCompound()) {
                     if(NEIClientConfig.vishash.isItemHidden(item.itemID, item.stackTagCompound)) {
                        continue;
                     }
                  } else if(NEIClientConfig.vishash.isItemHidden(item.itemID, item.getItemDamage())) {
                     continue;
                  }

                  if(NEIClientConfig.canGetItem(new ItemKey(item)) && matcher.matches(item)) {
                     e.add(new ItemPanelStack(item));
                  }
               }

               ItemPanel.visibleitems = e;
            } catch (ItemList.TimeoutException var5) {
               System.err.println("Removing itemID: " + var5.itemID + " from list.");
               var5.printStackTrace();
               ItemList.erroredIDs.add(Integer.valueOf(var5.itemID));
               ItemList.loadItems();
            }

            ItemList.matching = false;
         }

      }
   }

   private static class NothingItemMatcher implements ItemList.ItemMatcher {

      private NothingItemMatcher() {}

      public boolean matches(ItemStack item) {
         return false;
      }

      // $FF: synthetic method
      NothingItemMatcher(ItemList.NothingItemMatcher var1) {
         this();
      }
   }
}

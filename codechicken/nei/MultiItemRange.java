package codechicken.nei;

import codechicken.core.ReflectionManager;
import codechicken.nei.ItemRange;
import codechicken.nei.ItemVisibilityHash;
import codechicken.nei.NEIClientUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MultiItemRange {

   public ArrayList ranges = new ArrayList();
   public byte state;
   protected int lastslotclicked = -1;
   protected long lastslotclicktime;


   public boolean isItemInRange(int itemid, int damage) {
      Iterator var4 = this.ranges.iterator();

      while(var4.hasNext()) {
         ItemRange range = (ItemRange)var4.next();
         if(range.isItemInRange(itemid, damage)) {
            return true;
         }
      }

      return false;
   }

   public String toString() {
      StringBuilder rangestring = new StringBuilder();
      boolean addcomma = false;

      ItemRange range;
      for(Iterator var4 = this.ranges.iterator(); var4.hasNext(); rangestring.append(range.toString())) {
         range = (ItemRange)var4.next();
         if(addcomma) {
            rangestring.append(',');
         } else {
            addcomma = true;
         }
      }

      return rangestring.toString();
   }

   public MultiItemRange(String rangestring) {
      String[] separatedpairs = rangestring.split(",");
      String[] var6 = separatedpairs;
      int var5 = separatedpairs.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         String pairstring = var6[var4];
         this.ranges.add(new ItemRange(pairstring));
      }

   }

   public MultiItemRange() {}

   public MultiItemRange add(ItemRange range) {
      this.ranges.add(range);
      return this;
   }

   public MultiItemRange add(Collection ranges) {
      Iterator var3 = ranges.iterator();

      while(var3.hasNext()) {
         Object o = var3.next();

         try {
            ReflectionManager.callMethod(this.getClass(), this, "add", new Object[]{o});
         } catch (Exception var5) {
            NEIClientUtils.reportException(var5);
         }
      }

      return this;
   }

   public MultiItemRange add(MultiItemRange range) {
      return this.add((Collection)range.ranges);
   }

   public MultiItemRange add(int itemID) {
      return this.add(new ItemRange(itemID));
   }

   public MultiItemRange add(int itemID, int damageStart, int damageEnd) {
      return this.add(new ItemRange(itemID, damageStart, damageEnd));
   }

   public MultiItemRange add(int itemIDFirst, int itemIDLast) {
      return this.add(new ItemRange(itemIDFirst, itemIDLast));
   }

   public MultiItemRange add(Item item, int damageStart, int damageEnd) {
      return this.add(item.itemID, damageStart, damageEnd);
   }

   public MultiItemRange add(Block block, int damageStart, int damageEnd) {
      return this.add(block.blockID, damageStart, damageEnd);
   }

   public MultiItemRange add(Item item) {
      return this.add(item.itemID);
   }

   public MultiItemRange add(Block block) {
      return this.add(block.blockID);
   }

   public MultiItemRange add(ItemStack item) {
      return item.getItem().isDamageable()?this.add(item.itemID):this.add(item.itemID, item.getItemDamage(), item.getItemDamage());
   }

   public int getNumSlots() {
      int slots = 0;

      ItemRange range;
      for(Iterator var3 = this.ranges.iterator(); var3.hasNext(); slots += range.encompasseditems.size()) {
         range = (ItemRange)var3.next();
      }

      return slots;
   }

   public void slotClicked(int slot, int button, boolean doubleclick) {
      int i = 0;
      Iterator var6 = this.ranges.iterator();

      while(var6.hasNext()) {
         ItemRange range = (ItemRange)var6.next();
         if(i + range.encompasseditems.size() <= slot) {
            i += range.encompasseditems.size();
         } else {
            for(int item = 0; item < range.encompasseditems.size(); ++item) {
               if(slot == i) {
                  range.onClick(item, button, doubleclick);
                  return;
               }

               ++i;
            }
         }
      }

   }

   public void hideAllItems() {
      Iterator var2 = this.ranges.iterator();

      while(var2.hasNext()) {
         ItemRange range = (ItemRange)var2.next();
         range.hideAllItems();
      }

   }

   public void showAllItems() {
      Iterator var2 = this.ranges.iterator();

      while(var2.hasNext()) {
         ItemRange range = (ItemRange)var2.next();
         range.showAllItems();
      }

   }

   public int getWidth() {
      return 18;
   }

   public void resetHashes() {
      Iterator var2 = this.ranges.iterator();

      while(var2.hasNext()) {
         ItemRange range = (ItemRange)var2.next();
         range.resetHashes();
      }

   }

   public void updateState(ItemVisibilityHash vis) {
      boolean allshown = false;
      boolean allhidden = false;
      Iterator var5 = this.ranges.iterator();

      while(var5.hasNext()) {
         ItemRange range = (ItemRange)var5.next();
         if(range.encompasseditems.size() != 0) {
            range.updateState(vis);
            byte rstate = range.state;
            if(rstate == 1) {
               this.state = 1;
               return;
            }

            if(rstate == 0) {
               if(allshown) {
                  this.state = 1;
                  return;
               }

               allhidden = true;
            } else {
               if(allhidden) {
                  this.state = 1;
                  return;
               }

               allshown = true;
            }
         }
      }

      if(allshown) {
         this.state = 2;
      } else {
         this.state = 0;
      }

   }

   public void addItemIfInRange(int item, int damage, NBTTagCompound compound) {
      Iterator var5 = this.ranges.iterator();

      while(var5.hasNext()) {
         ItemRange range = (ItemRange)var5.next();
         if(range.addItemIfInRange(item, damage, compound)) {
            break;
         }
      }

   }
}

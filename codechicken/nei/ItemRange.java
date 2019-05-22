package codechicken.nei;

import codechicken.core.inventory.ItemKey;
import codechicken.nei.DropDownFile;
import codechicken.nei.ItemList;
import codechicken.nei.ItemVisibilityHash;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.nbt.NBTTagCompound;

public class ItemRange {

   public int firstID;
   public int firstDamage = -1;
   public int lastID;
   public int lastDamage = -1;
   public byte state = 0;
   public HashSet encompassedhash = new HashSet();
   public ArrayList encompasseditems = new ArrayList();


   public ItemRange(int itemID) {
      this.firstID = itemID;
      this.firstDamage = -1;
      this.lastID = itemID;
      this.lastDamage = -1;
   }

   public ItemRange(int itemID, int damageStart, int damageEnd) {
      this.firstID = itemID;
      this.firstDamage = damageStart;
      this.lastID = itemID;
      this.lastDamage = damageEnd;
   }

   public ItemRange(int itemIDFirst, int itemIDLast) {
      this.firstID = itemIDFirst;
      this.firstDamage = -1;
      this.lastID = itemIDLast;
      this.lastDamage = -1;
   }

   public boolean isItemInRange(int id, int damage) {
      return id >= this.firstID && id <= this.lastID && (this.firstDamage == -1 || damage >= this.firstDamage && damage <= this.lastDamage);
   }

   public String toString() {
      return this.firstID == this.lastID?(this.firstDamage == -1?"[" + this.firstID + "]":(this.firstDamage == this.lastDamage?"[" + this.firstID + ":" + this.firstDamage + "]":"[" + this.firstID + ":" + this.firstDamage + "-" + this.lastDamage + "]")):"[" + this.firstID + "-" + this.lastID + "]";
   }

   public ItemRange(String rangestring) {
      rangestring = rangestring.replace(" ", "");
      rangestring = rangestring.replace("\t", "");
      rangestring = rangestring.substring(1, rangestring.length() - 1);
      String[] damagesplit = rangestring.split(":");
      String[] rangesplit;
      if(damagesplit.length == 2) {
         rangesplit = damagesplit[1].split("-");
         this.firstID = Integer.parseInt(damagesplit[0]);
         this.lastID = this.firstID;
         this.firstDamage = Integer.parseInt(rangesplit[0]);
         if(rangesplit.length == 2) {
            this.lastDamage = Integer.parseInt(rangesplit[1]);
         } else {
            this.lastDamage = this.firstDamage;
         }
      } else {
         rangesplit = damagesplit[0].split("-");
         this.firstID = Integer.parseInt(rangesplit[0]);
         if(rangesplit.length == 2) {
            this.lastID = Integer.parseInt(rangesplit[1]);
         } else {
            this.lastID = this.firstID;
         }
      }

   }

   public synchronized void updateState(ItemVisibilityHash vis) {
      boolean allhidden = false;
      boolean allshown = false;
      Iterator var5 = this.encompasseditems.iterator();

      while(var5.hasNext()) {
         ItemKey item = (ItemKey)var5.next();
         if(vis.isItemHidden(item)) {
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

      if(allshown) {
         this.state = 2;
      } else {
         this.state = 0;
      }

   }

   public synchronized void resetHashes() {
      this.encompassedhash.clear();
      this.encompasseditems.clear();
   }

   public synchronized boolean addItemIfInRange(int item, int damage, NBTTagCompound compound) {
      if(this.isItemInRange(item, damage)) {
         ItemKey hash = new ItemKey(item, damage, compound);
         if(this.encompassedhash.add(hash)) {
            this.encompasseditems.add(hash);
            return true;
         }
      }

      return false;
   }

   public void onClick(int itemno, int button, boolean doubleclick) {
      ItemVisibilityHash vis = NEIClientConfig.vishash;
      ItemKey item = (ItemKey)this.encompasseditems.get(itemno);
      if(NEIClientUtils.controlKey()) {
         NEIClientUtils.cheatItem(item.item, button, 0);
      } else {
         if(button == 0) {
            if(doubleclick) {
               DropDownFile.dropDownInstance.hideAllItems();
            }

            vis.unhideItem(item);
         } else if(button == 1) {
            vis.hideItem(item);
         }

         DropDownFile.dropDownInstance.updateState();
         ItemList.updateSearch();
         NEIClientConfig.vishash.save();
      }
   }

   public synchronized void hideAllItems() {
      ItemVisibilityHash vis = NEIClientConfig.vishash;
      Iterator var3 = this.encompasseditems.iterator();

      while(var3.hasNext()) {
         ItemKey item = (ItemKey)var3.next();
         vis.hideItem(item);
      }

   }

   public synchronized void showAllItems() {
      ItemVisibilityHash vis = NEIClientConfig.vishash;
      Iterator var3 = this.encompasseditems.iterator();

      while(var3.hasNext()) {
         ItemKey item = (ItemKey)var3.next();
         vis.unhideItem(item);
      }

   }

   public ArrayList toIDList() {
      ArrayList list = new ArrayList();

      for(int i = this.firstID; i <= this.lastID; ++i) {
         list.add(Integer.valueOf(i));
      }

      return list;
   }
}

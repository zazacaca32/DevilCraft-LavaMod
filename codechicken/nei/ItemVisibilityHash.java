package codechicken.nei;

import codechicken.core.inventory.ItemKey;
import codechicken.nei.DropDownFile;
import codechicken.nei.ItemList;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemVisibilityHash {

   private static boolean[] statesSaved = new boolean[7];
   public TreeMap hiddenitems;


   public ItemVisibilityHash() {
      try {
         this.loadFromCompound(this.getCurrentSaveCompound());
      } catch (Exception var2) {
         NEIClientUtils.reportException(var2);
      }

   }

   public NBTTagCompound getCurrentSaveCompound() {
      NBTTagCompound hashSave = NEIClientConfig.saveCompound.getCompoundTag("vis");
      NEIClientConfig.saveCompound.setTag("vis", hashSave);
      NBTTagCompound currentSave = hashSave.getCompoundTag("current");
      hashSave.setCompoundTag("current", currentSave);
      return currentSave;
   }

   public void hideItem(int item) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item));
      if(info == null) {
         info = new ItemVisibilityHash.IDInfo();
         this.hiddenitems.put(Integer.valueOf(item), info);
      }

      info.damages.add(Integer.valueOf(-1));
   }

   public void hideItem(int item, int damage) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item));
      if(info == null) {
         info = new ItemVisibilityHash.IDInfo();
         this.hiddenitems.put(Integer.valueOf(item), info);
      }

      info.damages.add(Integer.valueOf(damage));
   }

   public void hideItem(int item, NBTTagCompound stackTagCompound) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item));
      if(info == null) {
         info = new ItemVisibilityHash.IDInfo();
         this.hiddenitems.put(Integer.valueOf(item), info);
      }

      if(!info.compounds.contains(stackTagCompound)) {
         info.compounds.add(stackTagCompound);
      }

   }

   public void hideItem(ItemKey item) {
      if(item.item.hasTagCompound()) {
         this.hideItem(item.item.itemID, item.item.getTagCompound());
      } else {
         this.hideItem(item.item.itemID, item.item.getItemDamage());
      }

   }

   public void unhideItem(int item) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item));
      if(info != null) {
         this.hiddenitems.remove(Integer.valueOf(item));
      }
   }

   public void unhideItem(int item, int damage) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item));
      if(info != null) {
         if(damage == -1) {
            this.hiddenitems.remove(Integer.valueOf(item));
         } else {
            info.damages.remove(Integer.valueOf(damage));
         }

      }
   }

   public void unhideItem(int item, NBTTagCompound stackTagCompound) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item));
      if(info != null) {
         info.compounds.remove(stackTagCompound);
      }
   }

   public void unhideItem(ItemKey item) {
      if(item.item.hasTagCompound()) {
         this.unhideItem(item.item.itemID, item.item.getTagCompound());
      } else {
         this.unhideItem(item.item.itemID, item.item.getItemDamage());
      }

   }

   public boolean isItemHidden(int itemID, int damage) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(itemID));
      return info == null?false:info.damages.contains(Integer.valueOf(damage)) || info.damages.contains(Integer.valueOf(-1));
   }

   public boolean isItemHidden(int item) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item));
      return info == null?false:info.damages.contains(Short.valueOf((short)-1));
   }

   public boolean isItemHidden(int itemID, NBTTagCompound stackTagCompound) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(itemID));
      return info == null?false:info.compounds.contains(stackTagCompound);
   }

   public boolean isItemHidden(ItemKey item) {
      ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(item.item.itemID));
      return info == null?false:(!info.damages.contains(Integer.valueOf(item.item.getItemDamage())) && !info.damages.contains(Integer.valueOf(-1))?(item.item.hasTagCompound()?info.compounds.contains(item.item.getTagCompound()):false):true);
   }

   private void loadFromCompound(NBTTagCompound readTag) {
      this.hiddenitems = new TreeMap();
      Iterator var3 = readTag.getTags().iterator();

      while(var3.hasNext()) {
         Object obj = var3.next();
         int itemID;
         ItemVisibilityHash.IDInfo info;
         int i;
         if(obj instanceof NBTTagList) {
            NBTTagList var8 = (NBTTagList)obj;
            itemID = Integer.parseInt(var8.getName().substring(1));
            info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(itemID));
            if(info == null) {
               info = new ItemVisibilityHash.IDInfo();
               this.hiddenitems.put(Integer.valueOf(itemID), info);
            }

            for(i = 0; i < var8.tagCount(); ++i) {
               info.compounds.add((NBTTagCompound)var8.tagAt(i));
            }
         } else if(obj instanceof NBTTagByteArray) {
            NBTTagByteArray damagearray = (NBTTagByteArray)obj;
            itemID = Integer.parseInt(damagearray.getName().substring(1));
            info = (ItemVisibilityHash.IDInfo)this.hiddenitems.get(Integer.valueOf(itemID));
            if(info == null) {
               info = new ItemVisibilityHash.IDInfo();
               this.hiddenitems.put(Integer.valueOf(itemID), info);
            }

            for(i = 0; i < damagearray.byteArray.length / 2; ++i) {
               info.damages.add(Integer.valueOf((damagearray.byteArray[i * 2] << 8) + damagearray.byteArray[i * 2 + 1]));
            }
         }
      }

   }

   public void save() {
      try {
         NBTTagCompound e = NEIClientConfig.saveCompound.getCompoundTag("vis");
         NEIClientConfig.saveCompound.setTag("vis", e);
         e.setCompoundTag("current", this.constructSaveCompound());
         NEIClientConfig.saveConfig();
      } catch (Exception var2) {
         NEIClientUtils.reportException(var2);
      }

   }

   private NBTTagCompound constructSaveCompound() {
      NBTTagCompound savecompound = new NBTTagCompound();
      Iterator var3 = this.hiddenitems.entrySet().iterator();

      while(var3.hasNext()) {
         Entry itemEntry = (Entry)var3.next();
         int id = ((Integer)itemEntry.getKey()).intValue();
         ItemVisibilityHash.IDInfo info = (ItemVisibilityHash.IDInfo)itemEntry.getValue();
         if(info.compounds.size() > 0) {
            NBTTagList damagearray = new NBTTagList();
            Iterator damage = info.compounds.iterator();

            while(damage.hasNext()) {
               NBTTagCompound i = (NBTTagCompound)damage.next();
               damagearray.appendTag(i);
            }

            savecompound.setTag("c" + id, damagearray);
         }

         if(info.damages.size() > 0) {
            byte[] var10 = new byte[info.damages.size() * 2];
            int var11 = 0;

            for(Iterator var9 = info.damages.iterator(); var9.hasNext(); ++var11) {
               int var12 = ((Integer)var9.next()).intValue();
               var10[var11 * 2] = (byte)(var12 >> 8);
               var10[var11 * 2 + 1] = (byte)var12;
            }

            savecompound.setByteArray("d" + id, var10);
         }
      }

      return savecompound;
   }

   public static void loadStates() {
      NBTTagCompound hashSave = NEIClientConfig.saveCompound.getCompoundTag("vis");
      NEIClientConfig.saveCompound.setTag("vis", hashSave);

      for(int i = 0; i < 7; ++i) {
         NBTTagCompound statesave = hashSave.getCompoundTag("save" + i);
         if(statesave.getTags().size() > 0) {
            statesSaved[i] = true;
         }
      }

   }

   public void loadState(int i) {
      NBTTagCompound hashSave = NEIClientConfig.saveCompound.getCompoundTag("vis");
      NEIClientConfig.saveCompound.setTag("vis", hashSave);
      this.loadFromCompound(hashSave.getCompoundTag("save" + i));
      DropDownFile.dropDownInstance.updateState();
      ItemList.updateSearch();
      NEIClientConfig.vishash.save();
   }

   public void saveState(int i) {
      NBTTagCompound hashSave = NEIClientConfig.saveCompound.getCompoundTag("vis");
      NEIClientConfig.saveCompound.setTag("vis", hashSave);
      NBTTagCompound saveCompound = this.getCurrentSaveCompound();
      saveCompound.setBoolean("saved", true);
      hashSave.setCompoundTag("save" + i, saveCompound);
      statesSaved[i] = true;
      NEIClientConfig.saveConfig();
   }

   public void clearState(int i) {
      NBTTagCompound hashSave = NEIClientConfig.saveCompound.getCompoundTag("vis");
      NEIClientConfig.saveCompound.setTag("vis", hashSave);
      hashSave.setCompoundTag("save" + i, new NBTTagCompound("save" + i));
      statesSaved[i] = false;
      NEIClientConfig.saveConfig();
   }

   public static boolean isStateSaved(int i) {
      return statesSaved[i];
   }

   public static class IDInfo {

      public TreeSet damages = new TreeSet();
      public ArrayList compounds = new ArrayList();


   }
}

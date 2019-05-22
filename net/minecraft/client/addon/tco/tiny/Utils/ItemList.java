package net.minecraft.client.addon.tco.tiny.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import net.minecraft.client.addon.tco.tiny.Utils.IItemList;
import net.minecraft.client.addon.tco.tiny.Utils.ItemListIterator;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;

public final class ItemList implements IItemList {

   private final TreeMap records = new TreeMap();
   private int currentPriority = Integer.MIN_VALUE;
   int iteration = Integer.MIN_VALUE;
   public Throwable stacktrace;


   public void setCurrentPriority(int priority) {
      this.currentPriority = priority;
   }

   public synchronized void add(LAItemStack option) {
      if(option != null) {
         LAItemStack st = (LAItemStack)this.records.get(option);
         option.priority = this.currentPriority;
         if(st != null) {
            st.add(option);
         } else {
            LAItemStack opt = option.copy();
            this.records.put(opt, opt);
         }
      }

   }

   public synchronized void addStorage(LAItemStack option) {
      if(option != null) {
         LAItemStack st = (LAItemStack)this.records.get(option);
         option.priority = this.currentPriority;
         if(st != null) {
            st.incStackSize(option.getStackSize());
         } else {
            LAItemStack opt = option.copy();
            this.records.put(opt, opt);
         }
      }

   }

   public synchronized void addCrafting(LAItemStack option) {
      if(option != null) {
         LAItemStack st = (LAItemStack)this.records.get(option);
         option.priority = this.currentPriority;
         if(st != null) {
            st.setCraftable(true);
         } else {
            LAItemStack beef = option.copy();
            beef.setStackSize(0L);
            beef.setCraftable(true);
            this.records.put(beef, beef);
         }
      }

   }

   public synchronized void addRequestable(LAItemStack option) {
      if(option != null) {
         LAItemStack st = (LAItemStack)this.records.get(option);
         option.priority = this.currentPriority;
         if(st != null) {
            st.setCountRequestable(st.getCountRequestable() + option.getCountRequestable());
         } else {
            LAItemStack beef = option.copy();
            beef.setStackSize(0L);
            beef.setCraftable(false);
            beef.setCountRequestable(beef.getCountRequestable());
            this.records.put(beef, beef);
         }
      }

   }

   public synchronized List getItems() {
      ArrayList out = new ArrayList();
      Iterator i = this.iterator();

      while(i.hasNext()) {
         out.add(((LAItemStack)i.next()).getItemStack());
      }

      return out;
   }

   public synchronized LAItemStack getFirstItem() {
      new ArrayList();
      Iterator i = this.iterator();
      boolean x = false;
      return i.hasNext()?(LAItemStack)i.next():null;
   }

   public synchronized List getSharedItems() {
      ArrayList out = new ArrayList();
      Iterator i = this.iterator();

      while(i.hasNext()) {
         out.add(Utils.getSharedItemStack((LAItemStack)i.next()));
      }

      return out;
   }

   public synchronized void resetStatus() {}

   public synchronized void clean() {
      try {
         throw new RuntimeException("Marking Location.");
      } catch (Throwable var4) {
         ++this.iteration;
         this.stacktrace = var4;
         Iterator i = this.iterator();

         while(i.hasNext()) {
            LAItemStack AEI = (LAItemStack)i.next();
            if(!AEI.isMeaninful()) {
               i.remove();
            }
         }

      }
   }

   public synchronized Iterator iterator() {
      return new ItemListIterator(this, this.records.values().iterator());
   }

   public synchronized LAItemStack findItem(LAItemStack i) {
      if(i == null) {
         return null;
      } else {
         LAItemStack is = (LAItemStack)this.records.get(i);
         return is != null?is:null;
      }
   }

   public synchronized int size() {
      return this.records.values().size();
   }
}

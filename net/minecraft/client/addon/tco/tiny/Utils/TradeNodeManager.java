package net.minecraft.client.addon.tco.tiny.Utils;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import net.minecraft.client.addon.tco.tiny.Utils.TraderNode;
import net.minecraft.nbt.NBTTagCompound;

public class TradeNodeManager {

   CopyOnWriteArraySet traders = new CopyOnWriteArraySet();
   ConcurrentLinkedQueue removed = new ConcurrentLinkedQueue();


   public boolean add(int x, int y, int z) {
      if(this.traders.size() > 254) {
         return false;
      } else if(y > 254) {
         return false;
      } else {
         TraderNode tn = new TraderNode(x, y, z);
         if(this.traders.add(tn)) {
            tn.init = true;
            this.traders.add(tn);
            return true;
         } else {
            return false;
         }
      }
   }

   public void readFromNBT(NBTTagCompound nbttagcompound) {
      this.traders.clear();
      boolean size = false;
      short var5;
      if(nbttagcompound.hasKey("tl")) {
         var5 = (short)nbttagcompound.getByte("tl");
         nbttagcompound.removeTag("tl");
      } else {
         var5 = nbttagcompound.getShort("tll");
      }

      for(int x = 0; x < var5; ++x) {
         NBTTagCompound c = nbttagcompound.getCompoundTag("*" + x);
         if(c != null) {
            this.traders.add(TraderNode.loadNodeFromNBT(c));
         }
      }

   }

   public void writeToNBT(NBTTagCompound nbttagcompound) {
      int si = this.traders.size();
      if(si > 255) {
         throw new IllegalArgumentException("Big byte TradenodeManager");
      } else {
         Iterator it = this.traders.iterator();
         int count = 0;
         nbttagcompound.setShort("tll", (short)si);

         while(it.hasNext()) {
            NBTTagCompound c = new NBTTagCompound();
            ((TraderNode)it.next()).writeToNBT(c);
            nbttagcompound.setCompoundTag("*" + count, c);
            ++count;
         }

      }
   }

   public void removeTick() {
      boolean flag = false;

      for(byte t = 0; !this.removed.isEmpty() && t < 10; ++t) {
         TraderNode tn = (TraderNode)this.removed.poll();
         if(tn == null) {
            t = 11;
         }
      }

   }

   public Set get() {
      return this.traders;
   }

   public int getSize() {
      return this.traders.size();
   }

   public void makeRemove(TraderNode n) {
      this.removed.add(n);
   }

   public void makeRemove(int par2, int par3, int par4) {
      this.removed.add(new TraderNode(par2, par3, par4));
   }
}

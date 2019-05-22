package net.minecraft.client.addon.tco.tiny.Utils;

import cpw.mods.fml.common.FMLLog;
import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.Utils.ItemList;

public class ItemListIterator implements Iterator {

   int iteration;
   ItemList parent;
   Iterator child;


   ItemListIterator(ItemList i, Iterator c) {
      this.parent = i;
      this.iteration = this.parent.iteration;
      this.child = c;
   }

   public boolean hasNext() {
      if(this.parent.iteration != this.iteration) {
         FMLLog.severe("Something bad just happened?", (Object[])(new Object[0]));
         this.parent.stacktrace.printStackTrace();

         try {
            throw new RuntimeException("Will crash?");
         } catch (Throwable var2) {
            var2.printStackTrace();
         }
      }

      return this.child.hasNext();
   }

   public Object next() {
      if(this.parent.iteration != this.iteration) {
         FMLLog.severe("Something bad just happened?", (Object[])(new Object[0]));
         this.parent.stacktrace.printStackTrace();

         try {
            throw new RuntimeException("Will crash?");
         } catch (Throwable var2) {
            var2.printStackTrace();
         }
      }

      return this.child.next();
   }

   public void remove() {
      if(this.parent.iteration != this.iteration) {
         FMLLog.severe("Something bad just happened?", (Object[])(new Object[0]));
         this.parent.stacktrace.printStackTrace();

         try {
            throw new RuntimeException("Will crash?");
         } catch (Throwable var2) {
            var2.printStackTrace();
         }
      }

      this.child.remove();
   }
}

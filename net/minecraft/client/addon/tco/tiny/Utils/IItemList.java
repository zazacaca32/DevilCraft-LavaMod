package net.minecraft.client.addon.tco.tiny.Utils;

import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;

public interface IItemList extends Iterable {

   void addStorage(LAItemStack var1);

   void addCrafting(LAItemStack var1);

   void addRequestable(LAItemStack var1);

   void add(LAItemStack var1);

   LAItemStack getFirstItem();

   List getItems();

   LAItemStack findItem(LAItemStack var1);

   int size();

   Iterator iterator();

   void setCurrentPriority(int var1);
}

package codechicken.nei;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemHash implements Comparable {

   public short item;
   public short damage;
   public NBTTagCompound moreinfo;


   public ItemHash(int itemID, int itemDamage, NBTTagCompound compound) {
      this.item = (short)itemID;
      this.damage = (short)itemDamage;
      this.moreinfo = compound;
   }

   public ItemHash(ItemStack stack) {
      this.item = (short)stack.itemID;
      this.damage = (short)stack.getItemDamage();
      this.moreinfo = stack.stackTagCompound;
   }

   public ItemHash(int itemID) {
      this(itemID, -1);
   }

   public ItemHash(int itemID, int itemDamage) {
      this(itemID, itemDamage, (NBTTagCompound)null);
   }

   public boolean equals(Object obj) {
      if(!(obj instanceof ItemHash)) {
         return false;
      } else {
         ItemHash hash = (ItemHash)obj;
         return hash.item == this.item && (hash.damage == this.damage || hash.damage == -1 || this.damage == -1) && (this.moreinfo == hash.moreinfo || this.moreinfo != null && this.moreinfo.equals(hash.moreinfo));
      }
   }

   public int hashCode() {
      return this.item;
   }

   public int compareTo(ItemHash o) {
      return o.item != this.item?Integer.valueOf(this.item).compareTo(Integer.valueOf(o.item)):(o.damage != this.damage?Integer.valueOf(this.damage).compareTo(Integer.valueOf(o.damage)):0);
   }

   public ItemStack toStack() {
      ItemStack stack = new ItemStack(this.item, 1, this.damage);
      stack.stackTagCompound = this.moreinfo;
      return stack;
   }

@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	return 0;
}
}

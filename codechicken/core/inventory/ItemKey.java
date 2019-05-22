package codechicken.core.inventory;

import codechicken.core.inventory.InventoryUtils;
import com.google.common.base.Objects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemKey implements Comparable {

   public ItemStack item;
   private int hashcode;


   public ItemKey(ItemStack k) {
      this.hashcode = 0;
      this.item = k;
   }

   public ItemKey(int id, int damage) {
      this(new ItemStack(id, 1, damage));
   }

   public ItemKey(int id, int damage, NBTTagCompound compound) {
      this(id, damage);
      this.item.setTagCompound(compound);
   }

   public boolean equals(Object obj) {
      if(!(obj instanceof ItemKey)) {
         return false;
      } else {
         ItemKey k = (ItemKey)obj;
         return this.item.itemID == k.item.itemID && InventoryUtils.actualDamage(this.item) == InventoryUtils.actualDamage(k.item) && Objects.equal(this.item.stackTagCompound, k.item.stackTagCompound);
      }
   }

   public int hashCode() {
      return this.hashcode != 0?this.hashcode:(this.hashcode = Objects.hashCode(new Object[]{Integer.valueOf(this.item.itemID), Integer.valueOf(InventoryUtils.actualDamage(this.item)), this.item.stackTagCompound}));
   }

   public int compareInt(int a, int b) {
      return a == b?0:(a < b?-1:1);
   }

   public int compareTo(ItemKey o) {
      return this.item.itemID != o.item.itemID?this.compareInt(this.item.itemID, o.item.itemID):(InventoryUtils.actualDamage(this.item) != InventoryUtils.actualDamage(o.item)?this.compareInt(InventoryUtils.actualDamage(this.item), InventoryUtils.actualDamage(o.item)):0);
   }

@Override
public int compareTo(Object arg0) {
	// TODO Auto-generated method stub
	return 0;
}
}

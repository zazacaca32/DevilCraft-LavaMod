package net.minecraft.client.addon.tco.tiny.Utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DarkItem {

   public Slot slot;
   public short id;
   public short damage;
   public int count;
   public ItemStack itemstack;


   public static ItemStack[] readToStackMinecraft(DarkItem[] itemstack) {
      ItemStack[] ret = new ItemStack[itemstack.length];

      for(int i = 0; i < ret.length; ++i) {
         ret[i].itemID = itemstack[i].id;
         ret[i].setItemDamage(itemstack[i].damage);
         ret[i].stackSize = 1;
      }

      return ret;
   }

   public static DarkItem[] readToStacDarkItem(ItemStack[] itemstack) {
      DarkItem[] ret = new DarkItem[itemstack.length];

      for(int i = 0; i < ret.length; ++i) {
         ret[i].id = (short)itemstack[i].itemID;
         ret[i].damage = (short)itemstack[i].getItemDamage();
         ret[i].count = 1;
      }

      return ret;
   }

   public DarkItem(short id, short damage, int count) {
      this.id = id;
      this.damage = damage;
      this.count = count;
   }

   public DarkItem() {}

   public DarkItem(int i, int j, int size) {}

   public void write(ByteArrayDataOutput out) {
      out.writeShort(this.id);
      out.writeShort(this.damage);
      out.writeInt(this.count);
   }

   public void read(ByteArrayDataInput in) {
      this.id = in.readShort();
      this.damage = in.readShort();
      this.count = in.readInt();
      this.itemstack = new ItemStack(this.id, 1, this.damage);
   }

   public boolean getHasStack() {
      return this.itemstack != null;
   }
}

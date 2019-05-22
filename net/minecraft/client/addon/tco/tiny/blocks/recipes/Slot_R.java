package net.minecraft.client.addon.tco.tiny.blocks.recipes;

import com.google.common.primitives.Floats;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Stacks;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInv;
import net.minecraft.item.ItemStack;

public class Slot_R {

   public SlotInv.Access access;
   public int slotIndex;
   public List staks;


   public Slot_R(int slotIndex, SlotInv.Access access) {
      this.slotIndex = slotIndex;
      this.access = access;
      this.staks = new ArrayList();
   }

   public void addStack(Stacks staks) {
      this.staks.add((Stacks)staks.clone());
   }

   public boolean EqualSlotAsItemID(ItemStack stack) {
      int id = stack.itemID;
      Iterator var3 = this.staks.iterator();

      Stacks it;
      do {
         if(!var3.hasNext()) {
            return false;
         }

         it = (Stacks)var3.next();
      } while(id != it.stack.itemID);

      return true;
   }

   public boolean EqualSlotAsItemIDandDamage(ItemStack stack) {
      Iterator var2 = this.staks.iterator();

      Stacks it;
      do {
         if(!var2.hasNext()) {
            return false;
         }

         it = (Stacks)var2.next();
      } while(Stacks.makeIDandDamage(stack) != it.stack_f);

      return true;
   }

   public boolean EqualSlotAsItem(ItemStack stack) {
      Iterator var2 = this.staks.iterator();

      Stacks it;
      do {
         if(!var2.hasNext()) {
            return false;
         }

         it = (Stacks)var2.next();
      } while(!this.equalStack(it.stack, stack));

      return true;
   }

   private boolean equalStack(ItemStack itemstack, ItemStack itemstack1) {
      return itemstack.isItemEqual(itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1);
   }

   public int getTimeBurnID(ItemStack itemStack) {
      int id = itemStack.itemID;
      Iterator var3 = this.staks.iterator();

      Stacks it;
      do {
         if(!var3.hasNext()) {
            return -1;
         }

         it = (Stacks)var3.next();
      } while(id != it.stack.itemID);

      return ((Integer)it.data[0]).intValue();
   }

   public int getMetaArm(ItemStack itemStack) {
      int id = itemStack.itemID;
      Iterator var3 = this.staks.iterator();

      Stacks it;
      do {
         if(!var3.hasNext()) {
            return 0;
         }

         it = (Stacks)var3.next();
      } while(id != it.stack.itemID);

      return ((Integer)it.data[1]).intValue();
   }

   public int getMetaArmSub(ItemStack itemStack) {
      int id = itemStack.itemID;
      Iterator var3 = this.staks.iterator();

      Stacks it;
      do {
         if(!var3.hasNext()) {
            return 0;
         }

         it = (Stacks)var3.next();
      } while(id != it.stack.itemID);

      return ((Integer)it.data[2]).intValue();
   }

   public float[] getFloatIDandDamage() {
      ArrayList f = new ArrayList();
      Iterator var2 = this.staks.iterator();

      while(var2.hasNext()) {
         Stacks it = (Stacks)var2.next();
         f.add(Float.valueOf(Stacks.makeIDandDamage(it.stack)));
      }

      f.trimToSize();
      return Floats.toArray(f);
   }

   public float getFloatIDandDamage(ItemStack stack) {
      Iterator var2 = this.staks.iterator();

      Stacks it;
      do {
         if(!var2.hasNext()) {
            return 0.0F;
         }

         it = (Stacks)var2.next();
      } while(!this.equalStack(it.stack, stack));

      return it.stack_f;
   }

   public ItemStack getSlotAsCraftItem(ItemStack stack) {
      Iterator var2 = this.staks.iterator();

      Stacks it;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         it = (Stacks)var2.next();
      } while(!this.equalStack(it.stack, stack));

      return it.craft;
   }

   public ItemStack getSlotItem(int i) {
      return this.staks.size() > i?((Stacks)this.staks.get(i)).stack:null;
   }

   public Object[] getSlotData(int i) {
      return this.staks.size() > i?((Stacks)this.staks.get(i)).data:null;
   }

   public Object[] getSlotAsDataItem(ItemStack stack) {
      Iterator var2 = this.staks.iterator();

      Stacks it;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         it = (Stacks)var2.next();
      } while(!this.equalStack(it.stack, stack));

      return it.data;
   }

   public Object[] getSlotAsDataItemID(ItemStack stack) {
      int id = stack.itemID;
      Iterator var3 = this.staks.iterator();

      Stacks it;
      do {
         if(!var3.hasNext()) {
            return null;
         }

         it = (Stacks)var3.next();
      } while(id != it.stack.itemID);

      return it.data;
   }
}

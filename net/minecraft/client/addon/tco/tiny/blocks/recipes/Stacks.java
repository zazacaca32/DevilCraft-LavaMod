package net.minecraft.client.addon.tco.tiny.blocks.recipes;

import net.minecraft.item.ItemStack;

public class Stacks implements Cloneable {

   Object[] data;
   ItemStack stack;
   ItemStack craft;
   float stack_f;
   float craft_f;


   public Stacks(ItemStack stack) {
      this.stack = stack;
      this.stack_f = makeIDandDamage(stack);
   }

   public Stacks(ItemStack stack, Object ... data) {
      this.stack = stack;
      this.stack_f = makeIDandDamage(stack);
      this.data = data;
   }

   public Stacks(ItemStack stack, ItemStack craft, Object ... data) {
      this.stack = stack;
      this.stack_f = makeIDandDamage(stack);
      this.craft = craft;
      this.craft_f = makeIDandDamage(craft);
      this.data = data;
   }

   public static float makeIDandDamage(ItemStack stack) {
      float k = (float)stack.getItemDamage();
      k = k > 100.0F?(k /= 1000.0F):(k > 10.0F?(k /= 100.0F):(k /= 10.0F));
      return Float.valueOf((float)stack.itemID).floatValue() + k;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError("невозможно!");
      }
   }
}

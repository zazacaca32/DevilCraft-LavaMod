package codechicken.nei;

import codechicken.nei.NEIClientUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class PositionedStack {

   public int relx;
   public int rely;
   public ItemStack[] items;
   public ItemStack item;


   public PositionedStack(Object object, int x, int y) {
      if(object instanceof ItemStack) {
         this.items = new ItemStack[]{(ItemStack)object};
      } else if(object instanceof ItemStack[]) {
         this.items = (ItemStack[])object;
      } else {
         if(!(object instanceof List)) {
            throw new ClassCastException("not an ItemStack or ItemStack[]");
         }

         this.items = (ItemStack[])((List)object).toArray(new ItemStack[0]);
      }

      this.generatePermutations();
      if(this.items.length == 0) {
         System.out.println("No items in recipe");
         this.items = new ItemStack[]{new ItemStack(Block.fire)};
      }

      this.setPermutationToRender(0);
      this.relx = x;
      this.rely = y;
   }

   private void generatePermutations() {
      ArrayList stacks = new ArrayList();
      ItemStack[] var5 = this.items;
      int var4 = this.items.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         ItemStack item = var5[var3];
         if(item != null && item.getItem() != null) {
            ItemStack permutation = item.copy();
            stacks.add(permutation);
            if(item.getItemDamage() == 32767) {
               permutation.setItemDamage(0);

               for(int damage = 1; damage <= 15; ++damage) {
                  permutation = item.copy();
                  permutation.setItemDamage(damage);
                  if(NEIClientUtils.isValidItem(permutation)) {
                     stacks.add(permutation);
                  }
               }
            }
         }
      }

      this.items = (ItemStack[])stacks.toArray(new ItemStack[0]);
   }

   public void setMaxSize(int i) {
      ItemStack[] var5 = this.items;
      int var4 = this.items.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         ItemStack item = var5[var3];
         if(item.stackSize > i) {
            item.stackSize = i;
         }
      }

   }

   public PositionedStack copy() {
      return new PositionedStack(this.items, this.relx, this.rely);
   }

   public void setPermutationToRender(int index) {
      this.item = this.items[index].copy();
      if(this.item.getItemDamage() == -1) {
         this.item.setItemDamage(0);
      }

   }
}

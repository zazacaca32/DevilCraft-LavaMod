package codechicken.nei.api;

import java.util.HashSet;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TaggedInventoryArea {

   public HashSet slots;
   public String tag;
   private IInventory inventory;
   private Container container;


   public TaggedInventoryArea(InventoryPlayer invPlayer) {
      this("InventoryPlayer", 0, 39, (Container)null);
      this.inventory = invPlayer;
   }

   public TaggedInventoryArea(String name, int first, int last, Container container) {
      this.slots = new HashSet();
      this.container = container;
      this.tag = name;

      for(int i = first; i <= last; ++i) {
         this.slots.add(Integer.valueOf(i));
      }

   }

   public ItemStack getStackInSlot(int i) {
      return this.inventory != null?this.inventory.getStackInSlot(i):this.container.getSlot(i).getStack();
   }

   public boolean isContainer() {
      return this.inventory == null;
   }
}

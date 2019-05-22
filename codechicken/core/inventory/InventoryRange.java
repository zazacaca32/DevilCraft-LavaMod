package codechicken.core.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public class InventoryRange {
   public IInventory inv;
   public int side;
   public ISidedInventory sidedInv;
   public int[] slots;


   public InventoryRange(IInventory inv, int side) {
      this.inv = inv;
      this.side = side;
      if(inv instanceof ISidedInventory) {
         this.sidedInv = (ISidedInventory)inv;
         this.slots = this.sidedInv.getAccessibleSlotsFromSide(side);
      } else if(inv instanceof net.minecraftforge.common.ISidedInventory) {
    
        
   

         for(int i1 = 0; i1 < this.slots.length; ++i1) {
       
         }
      } else {
         this.slots = new int[inv.getSizeInventory()];

         for(int var7 = 0; var7 < this.slots.length; this.slots[var7] = var7++) {
            ;
         }
      }

   }

   public InventoryRange(IInventory inv) {
      this(inv, 0);
   }

   public InventoryRange(IInventory inv, int fslot, int lslot) {
      this.inv = inv;
      this.slots = new int[lslot - fslot];

      for(int i = 0; i < this.slots.length; ++i) {
         this.slots[i] = fslot + i;
      }

   }

   public InventoryRange(IInventory inv, InventoryRange access) {
      this.inv = inv;
      this.slots = access.slots;
      this.side = access.side;
      if(inv instanceof ISidedInventory) {
         this.sidedInv = (ISidedInventory)inv;
      }

   }

   public boolean canInsertItem(int slot, ItemStack item) {
      return this.sidedInv == null?this.inv.isStackValidForSlot(slot, item):this.sidedInv.canInsertItem(slot, item, this.side);
   }

   public boolean canExtractItem(int slot, ItemStack item) {
      return this.sidedInv == null?this.inv.isStackValidForSlot(slot, item):this.sidedInv.canExtractItem(slot, item, this.side);
   }

   public int lastSlot() {
      int last = 0;
      int[] var5 = this.slots;
      int var4 = this.slots.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         int slot = var5[var3];
         if(slot > last) {
            last = slot;
         }
      }

      return last;
   }
}

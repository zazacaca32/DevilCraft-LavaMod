package codechicken.core.inventory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class MappedInventoryAccess implements IInventory {

   public static final MappedInventoryAccess.InventoryAccessor fullAccess = new MappedInventoryAccess.InventoryAccessor() {
      public boolean canAccessSlot(int slot) {
         return true;
      }
   };
   private ArrayList slotMap = new ArrayList();
   private IInventory inv;
   private ArrayList accessors = new ArrayList();


   public MappedInventoryAccess(IInventory inv, MappedInventoryAccess.InventoryAccessor ... accessors) {
      this.inv = inv;
      MappedInventoryAccess.InventoryAccessor[] var6 = accessors;
      int var5 = accessors.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         MappedInventoryAccess.InventoryAccessor a = var6[var4];
         this.accessors.add(a);
      }

      this.reset();
   }

   public void reset() {
      this.slotMap.clear();
      int i = 0;

      while(i < this.inv.getSizeInventory()) {
         Iterator var3 = this.accessors.iterator();

         while(true) {
            if(!var3.hasNext()) {
               this.slotMap.add(Integer.valueOf(i));
            } else {
               MappedInventoryAccess.InventoryAccessor a = (MappedInventoryAccess.InventoryAccessor)var3.next();
               if(a.canAccessSlot(i)) {
                  continue;
               }
            }

            ++i;
            break;
         }
      }

   }

   public int getSizeInventory() {
      return this.slotMap.size();
   }

   public ItemStack getStackInSlot(int slot) {
      return this.inv.getStackInSlot(((Integer)this.slotMap.get(slot)).intValue());
   }

   public ItemStack decrStackSize(int slot, int amount) {
      return this.inv.decrStackSize(((Integer)this.slotMap.get(slot)).intValue(), amount);
   }

   public ItemStack getStackInSlotOnClosing(int slot) {
      return this.inv.getStackInSlotOnClosing(((Integer)this.slotMap.get(slot)).intValue());
   }

   public void setInventorySlotContents(int slot, ItemStack stack) {
      this.inv.setInventorySlotContents(((Integer)this.slotMap.get(slot)).intValue(), stack);
   }

   public String getInvName() {
      return this.inv.getInvName();
   }

   public int getInventoryStackLimit() {
      return this.inv.getInventoryStackLimit();
   }

   public void onInventoryChanged() {
      this.inv.onInventoryChanged();
   }

   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.inv.isUseableByPlayer(player);
   }

   public void openChest() {
      this.inv.openChest();
   }

   public void closeChest() {
      this.inv.closeChest();
   }

   public void addAccessor(MappedInventoryAccess.InventoryAccessor accessor) {
      this.accessors.add(accessor);
      this.reset();
   }

   public boolean isStackValidForSlot(int slot, ItemStack stack) {
      return this.inv.isStackValidForSlot(((Integer)this.slotMap.get(slot)).intValue(), stack);
   }

   public boolean isInvNameLocalized() {
      return true;
   }

   public List accessors() {
      return this.accessors;
   }

   public interface InventoryAccessor {

      boolean canAccessSlot(int var1);
   }
}

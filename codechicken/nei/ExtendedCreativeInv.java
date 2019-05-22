package codechicken.nei;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.PlayerSave;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ExtendedCreativeInv implements IInventory {

   PlayerSave playerSave;
   Side side;


   public ExtendedCreativeInv(PlayerSave playerSave, Side side) {
      this.playerSave = playerSave;
      this.side = side;
   }

   public int getSizeInventory() {
      return 54;
   }

   public ItemStack getStackInSlot(int slot) {
      return this.side.isClient()?NEIClientConfig.creativeInv[slot]:this.playerSave.creativeInv[slot];
   }

   public ItemStack decrStackSize(int slot, int size) {
      ItemStack item = this.getStackInSlot(slot);
      if(item != null) {
         if(item.stackSize <= size) {
            this.setInventorySlotContents(slot, (ItemStack)null);
            this.onInventoryChanged();
            return item;
         } else {
            ItemStack itemstack1 = item.splitStack(size);
            if(item.stackSize == 0) {
               this.setInventorySlotContents(slot, (ItemStack)null);
            }

            this.onInventoryChanged();
            return itemstack1;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int slot) {
      synchronized(this) {
         ItemStack stack = this.getStackInSlot(slot);
         this.setInventorySlotContents(slot, (ItemStack)null);
         return stack;
      }
   }

   public void setInventorySlotContents(int slot, ItemStack stack) {
      if(this.side.isClient()) {
         NEIClientConfig.creativeInv[slot] = stack;
      } else {
         this.playerSave.creativeInv[slot] = stack;
      }

      this.onInventoryChanged();
   }

   public String getInvName() {
      return "Extended Creative";
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void onInventoryChanged() {
      if(this.side.isServer()) {
         this.playerSave.setCreativeDirty();
      }

   }

   public boolean isUseableByPlayer(EntityPlayer var1) {
      return true;
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int slot, ItemStack stack) {
      return true;
   }

   public boolean isInvNameLocalized() {
      return true;
   }
}

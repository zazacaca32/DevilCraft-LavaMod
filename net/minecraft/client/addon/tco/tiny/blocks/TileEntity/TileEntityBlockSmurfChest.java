package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockSmurfChest extends TileEntityBlockBase {

   private int tick;
   int[] output = new int[]{1};


   public TileEntityBlockSmurfChest() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[2];
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return false;
   }

   String getInventoryName() {
      return "container.Smurf";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public int[] getAccessibleSlotsFromSide(int side) {
      return this.output;
   }

   public boolean canInsertItem(int slot, ItemStack itemstack, int side) {
      return false;
   }

   public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
      return slot == 1;
   }
}

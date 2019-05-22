package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public abstract class ATEBA extends BaseTileEntityBlock implements IInventory {

   public ItemStack[] ItemStacks;
   private String InvStringName;


   public int getSizeInventory() {
      return this.ItemStacks.length;
   }

   public ItemStack getStackInSlot(int i) {
      return this.ItemStacks[i];
   }

   public ItemStack decrStackSize(int i, int j) {
      return null;
   }

   public ItemStack getStackInSlotOnClosing(int i) {
      return null;
   }

   public void setInventorySlotContents(int i, ItemStack itemstack) {}

   abstract String getInventoryName();

   public String getInvName() {
      return this.isInvNameLocalized()?this.InvStringName:this.getInventoryName();
   }

   public boolean isInvNameLocalized() {
      return this.InvStringName != null && this.InvStringName.length() > 0;
   }

   public void setCustomName(String par1Str) {
      this.InvStringName = par1Str;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void openChest() {}

   public void closeChest() {}
}

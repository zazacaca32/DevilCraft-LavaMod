package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class TileEntityBlockBase extends BaseTileEntityBlock implements IInventory {

   public ItemStack[] ItemStacks;
   private String InvStringName;


   public int getSizeInventory() {
      return this.ItemStacks.length;
   }

   public ItemStack getStackInSlot(int i) {
      return this.ItemStacks[i];
   }

   public ItemStack decrStackSize(int i, int j) {
      if(this.ItemStacks[i] != null) {
         ItemStack itemstack;
         if(this.ItemStacks[i].stackSize <= j) {
            itemstack = this.ItemStacks[i];
            this.ItemStacks[i] = null;
            return itemstack;
         } else {
            itemstack = this.ItemStacks[i].splitStack(j);
            if(this.ItemStacks[i].stackSize == 0) {
               this.ItemStacks[i] = null;
            }

            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int i) {
      if(this.ItemStacks[i] != null) {
         ItemStack itemstack = this.ItemStacks[i];
         this.ItemStacks[i] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int i, ItemStack itemstack) {
      if(i >= 0 && i < this.ItemStacks.length) {
         this.ItemStacks[i] = itemstack;
      }

      if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
         itemstack.stackSize = this.getInventoryStackLimit();
      }

   }

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

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
      NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
      this.ItemStacks = new ItemStack[this.getSizeInventory()];

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
         byte b0 = nbttagcompound1.getByte("Slot");
         if(b0 >= 0 && b0 < this.ItemStacks.length) {
            this.ItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
         }
      }

      if(par1NBTTagCompound.hasKey("CustomName")) {
         this.InvStringName = par1NBTTagCompound.getString("CustomName");
      }

   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.ItemStacks.length; ++i) {
         if(this.ItemStacks[i] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.ItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
         }
      }

      par1NBTTagCompound.setTag("Items", nbttaglist);
      if(this.isInvNameLocalized()) {
         par1NBTTagCompound.setString("CustomName", this.InvStringName);
      }

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

package net.minecraft.client.addon.tco.tiny;

import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTiny extends TileEntity implements IInventory {

   private int ticksSinceSync;
   public float prevLidAngle;
   public float lidAngle;
   private int numUsingPlayers;
   private byte facing;
   private boolean inventoryTouched;
   private ItemStack[] inv = new ItemStack[63];


   public TileEntityTiny getType() {
      return this;
   }

   public ItemStack[] getTopItemStacks() {
      return this.inv;
   }

   public byte getFacing() {
      return this.facing;
   }

   public void setFacing(byte chestFacing) {
      this.facing = chestFacing;
   }

   public TileEntityTiny updateFromMetadata(TileEntityTiny l) {
      if(super.worldObj != null && super.worldObj.isRemote) {
         super.worldObj.setBlockTileEntity(super.xCoord, super.yCoord, super.zCoord, l);
         return (TileEntityTiny)super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord);
      } else {
         return this;
      }
   }

   public void readFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.facing = nbttagcompound.getByte("facing");
   }

   public void writeToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
      nbttagcompound.setByte("facing", this.facing);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj == null || super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) == this && entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public void updateEntity() {
      super.updateEntity();
      if(++this.ticksSinceSync % 80 == 0) {
         super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, Tiny.blockTiny.blockID, 3, this.numUsingPlayers << 3 & 248 | this.facing & 7);
         if(this.inventoryTouched) {
            this.inventoryTouched = false;
         }
      }

      this.prevLidAngle = this.lidAngle;
      float f = 0.1F;
      double d3;
      if(this.numUsingPlayers > 0 && this.lidAngle == 0.0F) {
         double var8 = (double)super.xCoord + 0.5D;
         d3 = (double)super.zCoord + 0.5D;
         super.worldObj.playSoundEffect(var8, (double)super.yCoord + 0.5D, d3, "random.chestopen", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
      }

      if(this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F) {
         float var81 = this.lidAngle;
         this.lidAngle = this.numUsingPlayers > 0?(this.lidAngle += 0.1F):(this.lidAngle -= 0.1F);
         if(this.lidAngle > 1.0F) {
            this.lidAngle = 1.0F;
         }

         float f3 = 0.5F;
         if(this.lidAngle < 0.5F && var81 >= f3) {
            d3 = (double)super.xCoord + 0.5D;
            double d4 = (double)super.zCoord + 0.5D;
            super.worldObj.playSoundEffect(d3, (double)super.yCoord + 0.5D, d4, "random.chestclosed", 0.5F, super.worldObj.rand.nextFloat() * 0.1F + 0.9F);
         }

         if(this.lidAngle < 0.0F) {
            this.lidAngle = 0.0F;
         }
      }

   }

   public boolean receiveClientEvent(int i, int j) {
      if(i == 1) {
         this.numUsingPlayers = j;
      } else if(i == 2) {
         this.facing = (byte)j;
      } else if(i == 3) {
         this.facing = (byte)(j & 7);
         this.numUsingPlayers = (j & 248) >> 3;
      }

      return true;
   }

   public void openChest() {
      if(super.worldObj != null) {
         ++this.numUsingPlayers;
         super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, Tiny.blockTiny.blockID, 1, this.numUsingPlayers);
      }

   }

   public void closeChest() {
      Tiny.sum = 0;
      if(super.worldObj != null) {
         for(int i = 54; i < 63; ++i) {
            this.getStackInSlotOnClosing(i);
         }

         --this.numUsingPlayers;
         super.worldObj.addBlockEvent(super.xCoord, super.yCoord, super.zCoord, Tiny.blockTiny.blockID, 1, this.numUsingPlayers);
      }

   }

   public int getSizeInventory() {
      return this.inv.length;
   }

   public ItemStack getStackInSlot(int slot) {
      return this.inv[slot];
   }

   public void setInventorySlotContents(int slot, ItemStack stack) {
      this.inv[slot] = stack;
      if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

      this.onInventoryChanged();
   }

   public ItemStack decrStackSize(int slot, int amt) {
      return null;
   }

   public ItemStack getStackInSlotOnClosing(int slot) {
      ItemStack stack = this.getStackInSlot(slot);
      if(stack != null) {
         this.setInventorySlotContents(slot, (ItemStack)null);
      }

      return stack;
   }

   public String getInvName() {
      return "tco.lavashop";
   }

   public boolean isInvNameLocalized() {
      return false;
   }

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return false;
   }
}

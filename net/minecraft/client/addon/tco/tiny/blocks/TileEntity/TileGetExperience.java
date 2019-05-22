package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileGetExperience extends TileEntity implements ISidedInventory {

   public boolean powerEx = false;
   public boolean triger = false;
   public EntityPlayer player;
   public String Owner;
   public ItemStack inventoryContents;
   public int ticker;
   public int exp = 0;
   public int lastexp = 0;
   public int timer = 0;
   private int[] intt = new int[0];


   public void stopExPower() {
      this.powerEx = false;
      this.player = null;
      this.triger = true;
   }

   public boolean isPlayerCord(EntityPlayer player) {
      int x = (int)player.posX;
      int z = (int)player.posZ;
      int r = x < 0?-1:0;
      int r1 = z < 0?-1:0;
      return super.xCoord == x + r && super.yCoord == (int)player.posY && super.zCoord == z + r1;
   }

   public int getCountEx() {
      int ex = 0;
      if(this.inventoryContents != null) {
         int id = this.inventoryContents.itemID;
         int meta = this.inventoryContents.getItemDamage();
         int count = this.inventoryContents.stackSize;
         if(id == 194 && meta == 2) {
            ex = 1 * count;
         } else if(id == 194 && meta == 3) {
            ex = 30 * count;
         } else if(id == 2656 && meta == 0) {
            ex = 35 * count;
         }
      }

      this.exp = ex * 60;
      return ex;
   }

   public Packet getDescriptionPacket() {
      NBTTagCompound compound = new NBTTagCompound();
      this.write(compound);
      return new Packet132TileEntityData(super.xCoord, super.yCoord, super.zCoord, 0, compound);
   }

   public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
      NBTTagCompound compound = packet.customParam1;
      this.read(compound);
   }

   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      this.read(compound);
   }

   public void writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      this.write(compound);
   }

   private void read(NBTTagCompound compound) {
      this.powerEx = compound.getBoolean("powerEx");
      this.exp = compound.getInteger("Exp");
      if(compound.hasKey("Owner")) {
         this.Owner = compound.getString("Owner");
      }

      if(compound.hasKey("Item")) {
         NBTTagCompound item = (NBTTagCompound)compound.getTag("Item");
         this.inventoryContents = ItemStack.loadItemStackFromNBT(item);
      }

   }

   private void write(NBTTagCompound compound) {
      compound.setBoolean("powerEx", this.powerEx);
      compound.setInteger("Exp", this.exp);
      if(this.Owner != null) {
         compound.setString("Owner", this.Owner);
      }

      if(this.inventoryContents != null) {
         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
         this.inventoryContents.writeToNBT(nbttagcompound1);
         compound.setTag("Item", nbttagcompound1);
      }

   }

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public int getSizeInventory() {
      return 1;
   }

   public ItemStack getStackInSlot(int i) {
      return this.inventoryContents;
   }

   public ItemStack decrStackSize(int i, int j) {
      if(this.inventoryContents != null) {
         ItemStack itemstack;
         if(this.inventoryContents.stackSize <= j) {
            itemstack = this.inventoryContents;
            this.inventoryContents = null;
            this.onInventoryChanged();
            return itemstack;
         } else {
            itemstack = this.inventoryContents.splitStack(j);
            if(this.inventoryContents.stackSize == 0) {
               this.inventoryContents = null;
            }

            this.onInventoryChanged();
            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int i) {
      if(this.inventoryContents != null) {
         ItemStack itemstack = this.inventoryContents;
         this.inventoryContents = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int i, ItemStack itemstack) {
      this.inventoryContents = itemstack;
      if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
         itemstack.stackSize = this.getInventoryStackLimit();
      }

      this.onInventoryChanged();
   }

   public String getInvName() {
      return "inv.Get_Experience";
   }

   public boolean isInvNameLocalized() {
      return false;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return true;
   }

   public int[] getAccessibleSlotsFromSide(int var1) {
      return this.intt;
   }

   public boolean canInsertItem(int i, ItemStack itemstack, int j) {
      return false;
   }

   public boolean canExtractItem(int i, ItemStack itemstack, int j) {
      return false;
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return par1ItemStack.itemID == 194 && par1ItemStack.getItemDamage() == 2?true:(par1ItemStack.itemID == 194 && par1ItemStack.getItemDamage() == 3?true:par1ItemStack.itemID == 2656 && par1ItemStack.getItemDamage() == 0);
   }
}

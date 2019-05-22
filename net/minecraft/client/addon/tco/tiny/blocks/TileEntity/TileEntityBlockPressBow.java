package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityBlockPressBow extends TileEntityBlockBase implements ISidedInventory {

   public ItemStack[] inventoryContents = new ItemStack[2];
   public int tickPowerTime = 0;
   public boolean isWorking = false;
   public int speedreamaningTime = 10;
   private int tick;
   private int startItemID;
   int startItemID2;
   public int percent = 0;
   public int fx = 0;
   public EntityPlayer usePlayer;
   public boolean isuse = false;
   private int[] intt = new int[0];
   public byte thisRenderModel = 0;
   private double[] randomic = new double[]{40.0D, 60.0D};
   public int chance = 100;


   public TileEntityBlockPressBow() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[3];
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
      this.tickPowerTime = compound.getInteger("tickPowerTime");
      this.isWorking = compound.getBoolean("iw");
      this.startItemID = compound.getInteger("sii");
      NBTTagList items = compound.getTagList("Items");

      for(int i = 0; i < items.tagCount(); ++i) {
         NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
         int slot = item.getByte("Slot") & 255;
         if(slot >= 0 && slot < this.inventoryContents.length) {
            this.inventoryContents[slot] = ItemStack.loadItemStackFromNBT(item);
         }
      }

   }

   private void write(NBTTagCompound compound) {
      compound.setInteger("tickPowerTime", this.tickPowerTime);
      compound.setBoolean("iw", this.isWorking);
      compound.setInteger("sii", this.startItemID);
      NBTTagList list = new NBTTagList();

      for(int i = 0; i < this.inventoryContents.length; ++i) {
         NBTTagCompound item;
         if(this.inventoryContents[i] != null) {
            item = new NBTTagCompound();
            item.setByte("Slot", (byte)i);
            this.inventoryContents[i].writeToNBT(item);
            list.appendTag(item);
         } else {
            item = new NBTTagCompound();
            item.setByte("Slot", (byte)i);
            (new ItemStack(0, 0, 0)).writeToNBT(item);
            list.appendTag(item);
         }
      }

      compound.setTag("Items", list);
   }

   @SideOnly(Side.CLIENT)
   public int getPowerScaled(int i) {
      if(this.tickPowerTime > 0 && this.speedreamaningTime > 0) {
         int r = this.tickPowerTime * i / this.speedreamaningTime;
         if(r > i) {
            r = i;
         }

         return r;
      } else {
         return 0;
      }
   }

   @SideOnly(Side.CLIENT)
   public boolean isReamaning() {
      return this.tickPowerTime > 0;
   }

   public void updateEntity() {
      super.updateEntity();
   }

   public void GetProcent(ItemStack par1ItemStack) {
      byte pr = 0;
      if(LavaModMobs.LavaLBowItem.equals(par1ItemStack.getItem())) {
         pr = 60;
      }

      this.percent = pr;
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return LavaModMobs.LavaLBowItem.equals(par1ItemStack.getItem());
   }

   public int getSizeInventory() {
      return this.inventoryContents.length;
   }

   public ItemStack getStackInSlot(int i) {
      return this.inventoryContents[i];
   }

   public ItemStack decrStackSize(int par1, int par2) {
      if(this.inventoryContents[par1] != null) {
         ItemStack itemstack;
         if(this.inventoryContents[par1].stackSize <= par2) {
            itemstack = this.inventoryContents[par1];
            this.inventoryContents[par1] = null;
            this.onInventoryChanged();
            return itemstack;
         } else {
            itemstack = this.inventoryContents[par1].splitStack(par2);
            if(this.inventoryContents[par1].stackSize == 0) {
               this.inventoryContents[par1] = null;
            }

            this.onInventoryChanged();
            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int par1) {
      if(this.inventoryContents[par1] != null) {
         ItemStack itemstack = this.inventoryContents[par1];
         this.inventoryContents[par1] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
      this.inventoryContents[par1] = par2ItemStack;
      if(par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
         par2ItemStack.stackSize = this.getInventoryStackLimit();
      }

      this.onInventoryChanged();
   }

   public String getInvName() {
      return "inv.Press_Bow";
   }

   public boolean isInvNameLocalized() {
      return false;
   }

   public int getInventoryStackLimit() {
      return 1;
   }

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      return false;
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

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return false;
   }

   String getInventoryName() {
      return "container.PressBow";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public boolean isuse(EntityPlayer entityplayer) {
      return false;
   }
}

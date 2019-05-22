package net.minecraft.client.addon.tco.tiny.blocks.slots;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTrade extends Slot {

   public Boolean putstack = Boolean.valueOf(false);
   public int u = 0;
   public TileEntityBlockTrade t;


   public SlotTrade(TileEntityBlockTrade par1iInventory, int par2, int par3, int par4, int u) {
      super(par1iInventory, par2, par3, par4);
      this.u = u;
      this.t = par1iInventory;
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      this.isSlot();
      return this.putstack.booleanValue();
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      this.isSlot();
      return this.putstack.booleanValue();
   }

   public void isSlot() {
      this.putstack = this.t._switch(Integer.valueOf(super.slotNumber), this.u);
   }

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
      if(this.putstack.booleanValue()) {
         super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
      }

   }

   public boolean isSlotInInventory(IInventory par1IInventory, int par2) {
      return this.putstack.booleanValue()?super.isSlotInInventory(par1IInventory, par2):false;
   }
}

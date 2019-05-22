package net.minecraft.client.addon.tco.tiny.entity.inventory;

import net.minecraft.client.addon.tco.tiny.entity.containers.ContainerMerchantAdm;
import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.InventoryMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMerchantResultAdm extends Slot {

   private final InventoryMerchant theMerchantInventory;
   private EntityPlayer thePlayer;
   private int field_75231_g;
   private final IMerchant theMerchant;
   ContainerMerchantAdm containerMerchant;


   public SlotMerchantResultAdm(ContainerMerchantAdm containerMerchantAdm, EntityPlayer par1EntityPlayer, IMerchant par2IMerchant, InventoryMerchant par3InventoryMerchant, int par4, int par5, int par6) {
      super(par3InventoryMerchant, par4, par5, par6);
      this.containerMerchant = containerMerchantAdm;
      this.thePlayer = par1EntityPlayer;
      this.theMerchant = par2IMerchant;
      this.theMerchantInventory = par3InventoryMerchant;
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return false;
   }

   public ItemStack decrStackSize(int par1) {
      if(this.getHasStack()) {
         this.field_75231_g += Math.min(par1, this.getStack().stackSize);
      }

      return super.decrStackSize(par1);
   }

   protected void onCrafting(ItemStack par1ItemStack, int par2) {
      this.field_75231_g += par2;
      this.onCrafting(par1ItemStack);
   }

   protected void onCrafting(ItemStack par1ItemStack) {
      par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75231_g);
      this.field_75231_g = 0;
   }

   public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
      this.onCrafting(par2ItemStack);
      MerchantRecipe merchantrecipe = this.theMerchantInventory.getCurrentRecipe();
      ItemStack itemstack1;
      ItemStack itemstack2;
      if(merchantrecipe != null && (this.func_75230_a(merchantrecipe, itemstack1 = this.theMerchantInventory.getStackInSlot(0), itemstack2 = this.theMerchantInventory.getStackInSlot(1)) || this.func_75230_a(merchantrecipe, itemstack2, itemstack1))) {
         if(itemstack1 != null && itemstack1.stackSize <= 0) {
            itemstack1 = null;
         }

         if(itemstack2 != null && itemstack2.stackSize <= 0) {
            itemstack2 = null;
         }

         this.theMerchantInventory.setInventorySlotContents(0, itemstack1);
         this.theMerchantInventory.setInventorySlotContents(1, itemstack2);
         this.theMerchant.useRecipe(merchantrecipe);
         this.containerMerchant.flaginit = true;
      }

   }

   private boolean func_75230_a(MerchantRecipe par1MerchantRecipe, ItemStack par2ItemStack, ItemStack par3ItemStack) {
      ItemStack itemstack2 = par1MerchantRecipe.getItemToBuy();
      ItemStack itemstack3 = par1MerchantRecipe.getSecondItemToBuy();
      if(par2ItemStack != null && par2ItemStack.itemID == itemstack2.itemID) {
         if(itemstack3 != null && par3ItemStack != null && itemstack3.itemID == par3ItemStack.itemID) {
            par2ItemStack.stackSize -= itemstack2.stackSize;
            par3ItemStack.stackSize -= itemstack3.stackSize;
            return true;
         }

         if(itemstack3 == null && par3ItemStack == null) {
            par2ItemStack.stackSize -= itemstack2.stackSize;
            return true;
         }
      }

      return false;
   }
}

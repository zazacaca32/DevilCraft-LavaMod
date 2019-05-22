package net.minecraft.client.addon.tco.tiny.entity.containers;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.InventoryMerchantAdm;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipeList;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMAUpdateMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMerchantAdm extends Container {

   private IMerchant theMerchant;
   private InventoryMerchantAdm merchantInventory;
   InventoryPlayer par1InventoryPlayer;
   private final World theWorld;
   public boolean flaginit;


   public ContainerMerchantAdm(InventoryPlayer par1InventoryPlayer, IMerchant par2IMerchant, World par3World) {
      this.theMerchant = par2IMerchant;
      this.theWorld = par3World;
      this.par1InventoryPlayer = par1InventoryPlayer;
      this.merchantInventory = new InventoryMerchantAdm(par1InventoryPlayer.player, par2IMerchant);
      this.addSlotToContainer(new Slot(this.merchantInventory, 0, 34, 122));
      this.addSlotToContainer(new Slot(this.merchantInventory, 1, 52, 122));
      this.addSlotToContainer(new Slot(this.merchantInventory, 2, 8, 122));

      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 166 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 224));
      }

      this.flaginit = true;
   }

   public InventoryMerchantAdm getMerchantInventory() {
      return this.merchantInventory;
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
   }

   public void detectAndSendChanges() {
      if(this.flaginit) {
         this.flaginit = false;
         MerchantRecipeList merchantrecipelist;
         if(!this.theWorld.isRemote && (merchantrecipelist = this.theMerchant.getRecipes(this.par1InventoryPlayer.player)) != null) {
            PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateMerchant(super.windowId, merchantrecipelist)).makePacket(), (Player)this.par1InventoryPlayer.player);
         }
      }

      super.detectAndSendChanges();
   }

   public void onCraftMatrixChanged(IInventory par1IInventory) {
      this.merchantInventory.resetRecipeAndSlots();
      super.onCraftMatrixChanged(par1IInventory);
   }

   public void setCurrentRecipeIndex(int par1) {
      this.merchantInventory.setCurrentRecipeIndex(par1);
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {}

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.theMerchant.getCustomer() == par1EntityPlayer;
   }

   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
      ItemStack itemstack = null;
      Slot slot = (Slot)super.inventorySlots.get(par2);
      if(slot != null && slot.getHasStack()) {
         ItemStack itemstack2 = slot.getStack();
         itemstack = itemstack2.copy();
         if(par2 != 2) {
            if(par2 != 0 && par2 != 1) {
               if(par2 >= 3 && par2 < 30) {
                  if(!this.mergeItemStack(itemstack2, 30, 39, false)) {
                     return null;
                  }
               } else if(par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack2, 3, 30, false)) {
                  return null;
               }
            } else if(!this.mergeItemStack(itemstack2, 3, 39, false)) {
               return null;
            }
         } else {
            if(!this.mergeItemStack(itemstack2, 3, 39, true)) {
               return null;
            }

            slot.onSlotChange(itemstack2, itemstack);
         }

         if(itemstack2.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if(itemstack2.stackSize == itemstack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(par1EntityPlayer, itemstack2);
      }

      return itemstack;
   }

   public void onCraftGuiClosed(EntityPlayer par1EntityPlayer) {
      super.onCraftGuiClosed(par1EntityPlayer);
      this.theMerchant.setCustomer((EntityPlayer)null);
      super.onCraftGuiClosed(par1EntityPlayer);
      if(!this.theWorld.isRemote) {
         ItemStack itemstack = this.merchantInventory.getStackInSlotOnClosing(0);
         if(itemstack != null) {
            par1EntityPlayer.dropPlayerItem(itemstack);
         }

         if((itemstack = this.merchantInventory.getStackInSlotOnClosing(1)) != null) {
            par1EntityPlayer.dropPlayerItem(itemstack);
         }
      }

   }

   public IMerchant getIMerchant() {
      return this.theMerchant;
   }
}

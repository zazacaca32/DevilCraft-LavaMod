package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.inventory.InventoryInfo;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotDisable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTraderCloseInfo extends Container {

   public TileEntityBlockTrader tileEntity;
   private boolean dualisJob;
   private short dualTriggerControllerOffer;
   @SideOnly(Side.CLIENT)
   public ArrayList guislots;
   @SideOnly(Side.CLIENT)
   public InventoryInfo invInfo;


   public ContainerTraderCloseInfo(IInventory inventoryPlayer, TileEntityBlockTrader te) {
      this.tileEntity = te;
      InventoryPlayer iplayer = (InventoryPlayer)inventoryPlayer;
      LAItemStack s = null;
      if(Utils.isServer()) {
         s = this.tileEntity.cellinvfake.getStackInSlot(0);
         if(!this.isCard(s) && !this.isCard(s = this.tileEntity.cellinvfake.getStackInSlot(1))) {
            s = null;
         }
      } else {
         this.guislots = new ArrayList(63);
         this.invInfo = new InventoryInfo();

         for(int var71 = 0; var71 < 7; ++var71) {
            for(int j = 0; j < 9; ++j) {
               this.guislots.add(new Slot(this.invInfo, j + var71 * 9, 8 + j * 18, 14 + var71 * 18));
            }
         }
      }

      final ItemStack var7 = Utils.isServer()?(s == null?null:s.getItemStack().copy()):null;
      this.addSlotToContainer(new SlotDisable(new IInventory() {

         public ItemStack stack = var7;

         public int getSizeInventory() {
            return 1;
         }
         public ItemStack getStackInSlot(int i) {
            return this.stack;
         }
         public ItemStack decrStackSize(int i, int j) {
            return null;
         }
         public ItemStack getStackInSlotOnClosing(int i) {
            return null;
         }
         public void setInventorySlotContents(int i, ItemStack itemstack) {
            this.stack = itemstack;
         }
         public String getInvName() {
            return "privateIn.tiny";
         }
         public boolean isInvNameLocalized() {
            return false;
         }
         public int getInventoryStackLimit() {
            return 64;
         }
         public void onInventoryChanged() {}
         public boolean isUseableByPlayer(EntityPlayer entityplayer) {
            return true;
         }
         public void openChest() {}
         public void closeChest() {}
         public boolean isStackValidForSlot(int i, ItemStack itemstack) {
            return false;
         }
      }, 0, 134, 144));
   }

   public boolean isCard(LAItemStack stack) {
      return stack != null && stack.getItemID() == 4361 && (stack.getItemDamage() > 0 && stack.getItemDamage() <= 2 || stack.getItemDamage() == 7);
   }

   public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
      return null;
   }

   public boolean canInteractWith(EntityPlayer player) {
      return this.tileEntity.isUseableByPlayer(player);
   }

   protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
      int i;
      for(i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 166 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 224));
      }

   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualTriggerControllerOffer != this.tileEntity.TriggerControllerOffer()) {
            ;
         }

         if(this.dualisJob == this.tileEntity.isJob()) {
            ;
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         ;
      }

      if(par1 == 3) {
         ;
      }

   }
}

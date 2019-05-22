package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockWeaponColorer;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerBase;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotDisabled;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableID;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInvConsumableIDmeta;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerWeaponColorer extends ContainerBase {

   private TileEntityBlockWeaponColorer tileentity;
   private int dualCookTime = 0;
   private int dualspeedreamaningTime = 0;
   private boolean dualisWorking = false;
   private int dualthisRenderModel = 0;
   private int dualchance = 0;


   public ContainerWeaponColorer(InventoryPlayer inventoryplayer, TileEntityBlockWeaponColorer tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      Slot_R r = null;
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(0);
      SlotInvConsumableIDmeta slot = new SlotInvConsumableIDmeta(r.access, tileentity, 0, 54, 35, r.getFloatIDandDamage());
      this.addSlotToContainer(slot);
      r = (Slot_R)Manager.INSTANCE.Get(tileentity.RecipesIndex).get(1);
      final SlotInvConsumableID slot1 = new SlotInvConsumableID(r.access, tileentity, 1, 112, 35, r.getFloatIDandDamage());
      this.addSlotToContainer(slot1);
      IInventory ModuleInv = new IInventory() {

         ItemStack[] stacks = new ItemStack[1];

         public int getSizeInventory() {
            return this.stacks.length;
         }
         public ItemStack getStackInSlot(int i) {
            if(Utils.isClient() && slot1.getHasStack()) {
               ItemStack item = slot1.getStack();
               NBTTagCompound n = Utils.getOrCreateNbtData(item);
               if(n.getInteger("winv") > 0) {
                  this.stacks[0] = null;
                  this.stacks[0] = new ItemStack(3257, 1, 0);
               }

               return this.stacks[i];
            } else {
               return null;
            }
         }
         public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slot) {
            return null;
         }
         public int getDSS(ItemStack i) {
            NBTTagCompound n = Utils.getOrCreateNbtData(i);
            switch(n.getByte("dss")) {
            case 1:
               return 3305;
            case 2:
               return 3307;
            case 3:
               return 3309;
            case 4:
               return 3311;
            case 5:
               return 3313;
            case 6:
               return 3315;
            case 7:
               return 3317;
            case 8:
               return 3319;
            case 9:
               return 3321;
            case 10:
               return 3323;
            default:
               return 0;
            }
         }
         public ItemStack decrStackSize(int i, int j) {
            return null;
         }
         public ItemStack getStackInSlotOnClosing(int i) {
            return null;
         }
         public void setInventorySlotContents(int i, ItemStack itemstack) {}
         public String getInvName() {
            return "inv.Moduledisabled";
         }
         public boolean isInvNameLocalized() {
            return false;
         }
         public int getInventoryStackLimit() {
            return 64;
         }
         public void onInventoryChanged() {}
         public boolean isUseableByPlayer(EntityPlayer entityplayer) {
            return false;
         }
         public void openChest() {}
         public void closeChest() {}
         public boolean isStackValidForSlot(int slot, ItemStack itemstack) {
            return false;
         }
      };
      int sl = 0;

      for(int i = 0; i < 1; ++i) {
         for(int j = 0; j < 1; ++j) {
            this.addSlotToContainer(new SlotDisabled(ModuleInv, sl++, 112, 54));
         }
      }

      this.CalcFullInv(84);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.tickPowerTime);
      par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.speedreamaningTime);
      par1ICrafting.sendProgressBarUpdate(this, 2, this.tileentity.isWorking?1:0);
      par1ICrafting.sendProgressBarUpdate(this, 3, this.tileentity.thisRenderModel);
      par1ICrafting.sendProgressBarUpdate(this, 4, this.tileentity.chance);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.dualCookTime != this.tileentity.tickPowerTime) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.tickPowerTime);
         }

         if(this.dualspeedreamaningTime != this.tileentity.speedreamaningTime) {
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.speedreamaningTime);
         }

         if(this.dualisWorking != this.tileentity.isWorking) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.isWorking?1:0);
         }

         if(this.dualthisRenderModel != this.tileentity.thisRenderModel) {
            icrafting.sendProgressBarUpdate(this, 3, this.tileentity.thisRenderModel);
         }

         if(this.dualchance != this.tileentity.chance) {
            icrafting.sendProgressBarUpdate(this, 4, this.tileentity.chance);
         }
      }

      this.dualCookTime = this.tileentity.tickPowerTime;
      this.dualspeedreamaningTime = this.tileentity.speedreamaningTime;
      this.dualisWorking = this.tileentity.isWorking;
      this.dualthisRenderModel = this.tileentity.thisRenderModel;
      this.dualchance = this.tileentity.chance;
   }

   @SideOnly(Side.CLIENT)
   public void updateProgressBar(int par1, int par2) {
      if(par1 == 0) {
         this.tileentity.tickPowerTime = par2;
      }

      if(par1 == 1) {
         this.tileentity.speedreamaningTime = par2;
      }

      if(par1 == 2) {
         TileEntityBlockWeaponColorer tileentity = this.tileentity;
         boolean isWorking = false;
         if(par2 <= 0) {
            isWorking = false;
         }

         tileentity.isWorking = isWorking;
      }

      if(par1 == 3) {
         this.tileentity.thisRenderModel = (byte)par2;
      }

      if(par1 == 4) {
         this.tileentity.chance = (byte)par2;
      }

   }

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}

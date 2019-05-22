package net.minecraft.client.addon.tco.tiny.Utils;

import java.util.Arrays;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.Utils.INetworkNotifiable;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LAInternalInventory {

   int maxStack = 64;
   int size;
   INetworkNotifiable te;
   private LAItemStack[] inv;
   public boolean lock = false;


   public LAInternalInventory(INetworkNotifiable te, int size) {
      this.size = size;
      this.te = te;
      this.inv = new LAItemStack[size];
   }

   public int getSize() {
      return this.size;
   }

   public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
      for(int x = 0; x < this.size; ++x) {
         NBTTagCompound c = new NBTTagCompound();
         if(this.inv[x] != null) {
            this.inv[x].writeToNBT(c);
         }

         par1nbtTagCompound.setCompoundTag("#" + x, c);
      }

   }

   public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
      for(int x = 0; x < this.size; ++x) {
         NBTTagCompound c = par1nbtTagCompound.getCompoundTag("#" + x);
         if(c != null) {
            this.inv[x] = LAItemStack.loadItemStackFromNBT(c);
         }
      }

   }

   public LAItemStack[] getAvaleableLA() {
      return this.copyAvaleableLA();
   }

   public List getAvaleableLAList() {
      return Arrays.asList(this.copyAvaleableLA());
   }

   public LAItemStack getStackInSlot(int var1) {
      return this.inv[var1];
   }

   public synchronized LAItemStack[] copyAvaleableLA() {
      return (LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])((LAItemStack[])Arrays.copyOf(this.inv, this.inv.length))))))));
   }

   public int findfirstEmpyslot() {
      for(int i = 0; i < this.size; ++i) {
         if(this.inv[i] == null) {
            return i;
         }
      }

      return -1;
   }

   public int findItemIndexSlot(LAItemStack input) {
      if(input == null) {
         return -1;
      } else {
         for(int i = 0; i < this.size; ++i) {
            if(input.eq(this.inv[i])) {
               return i;
            }
         }

         return -1;
      }
   }

   public synchronized LAItemStack addItems(LAItemStack input) {
      if(input == null) {
         return null;
      } else if(input.getStackSize() == 0L) {
         return null;
      } else {
         int slot = this.findItemIndexSlot(input);
         if(slot == -1) {
            slot = this.findfirstEmpyslot();
            if(slot == -1) {
               return input;
            }

            this.inv[slot] = input;
            this.te.notifyAddItems(input);
         } else {
            this.inv[slot].incStackSize(input.getStackSize());
            this.te.notifyAddItems(this.inv[slot]);
         }

         return null;
      }
   }

   public synchronized LAItemStack extractItems(LAItemStack request) {
      if(request == null) {
         return null;
      } else {
         int slot = -1;

         for(int var9 = 0; var9 < this.size; ++var9) {
            if(this.inv[var9] != null && this.inv[var9].eq(request)) {
               slot = var9;
               break;
            }
         }

         if(slot == -1) {
            return null;
         } else {
            ItemStack var91 = Utils.getSharedItemStack(request);
            int size = var91.stackSize;
            int slimit = var91.getItem().getItemStackLimit();
            if(size > slimit) {
               size = slimit;
            }

            boolean moveBackStacks = true;
            LAItemStack Results = null;
            LAItemStack l = this.inv[slot];
            if(l != null) {
               Results = l.copy();
               if(l.getStackSize() <= (long)size) {
                  Results.setStackSize(l.getStackSize());
                  this.inv[slot].decStackSize(l.getStackSize());
                  if(this.inv[slot].getStackSize() <= 0L) {
                     this.inv[slot] = null;
                  }

                  this.te.notifyExtractItems(Results);
               } else {
                  l.setStackSize(l.getStackSize() - (long)size);
                  Results.setStackSize((long)size);
                  this.te.notifyExtractItems(Results);
               }
            }

            return Results;
         }
      }
   }

   public synchronized LAItemStack extractItemsNoLimit(LAItemStack request) {
      if(request == null) {
         return null;
      } else {
         int slot = -1;

         for(int var10 = 0; var10 < this.size; ++var10) {
            if(this.inv[var10] != null && this.inv[var10].eq(request)) {
               slot = var10;
               break;
            }
         }

         if(slot == -1) {
            return null;
         } else {
            ItemStack var101 = Utils.getSharedItemStack(request);
            long size = (long)var101.stackSize;
            int slimit = var101.getItem().getItemStackLimit();
            if(slimit == 1) {
               size = 1L;
            }

            boolean moveBackStacks = true;
            LAItemStack Results = null;
            LAItemStack l = this.inv[slot];
            if(l != null) {
               Results = l.copy();
               if(l.getStackSize() <= size) {
                  Results.setStackSize(l.getStackSize());
                  this.inv[slot].decStackSize(l.getStackSize());
                  if(this.inv[slot].getStackSize() <= 0L) {
                     this.inv[slot] = null;
                  }

                  this.te.notifyExtractItems(Results);
               } else {
                  l.setStackSize(l.getStackSize() - size);
                  Results.setStackSize(size);
                  this.te.notifyExtractItems(Results);
               }
            }

            return Results;
         }
      }
   }

   public synchronized void setInventorySlotContents(int index, LAItemStack stack) {
      this.inv[index] = stack;
   }
}

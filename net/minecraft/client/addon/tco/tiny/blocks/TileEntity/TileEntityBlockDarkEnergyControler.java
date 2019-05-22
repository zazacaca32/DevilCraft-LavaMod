package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.addon.tco.tiny.IController;
import net.minecraft.client.addon.tco.tiny.IDarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.Coords;
import net.minecraft.client.addon.tco.tiny.Utils.DarkItem;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityBlockDarkEnergyControler extends BaseTileEntityBlock implements ISidedInventory, IDarkNode, IController {

   DarkNode[] allMahines = new DarkNode[10];
   DarkNode[] allMahine = new DarkNode[10];
   ArrayList generators = new ArrayList();
   ArrayList generator = new ArrayList();
   ArrayList cables = new ArrayList();
   public ItemStack[] ItemStacks = new ItemStack[16];
   public ArrayList itemstacks;
   boolean isInit = false;
   long tick = 350L;
   int xRegion;
   int zRegion;
   int x1Region;
   int z1Region;
   public boolean isUpdateFindMashime = true;
   public Coords coords;
   boolean isControllerOnline = false;
   boolean isControllerOnlineTemp = false;
   public long darkEnergy = 0L;
   private long darkEnergyDouble = 0L;
   public HashMap nodes;
   public boolean changerGUI = false;


   public void validate() {
      super.validate();
      if(!this.isInit) {
         if(!super.worldObj.isRemote) {
            this.coords = new Coords(super.xCoord, super.yCoord, super.zCoord);
            DarkNode.add(super.xCoord, super.yCoord, super.zCoord, this);
            this.xRegion = (super.xCoord >> 4) - 2 << 4;
            this.x1Region = (super.xCoord >> 4) + 2 << 4;
            this.zRegion = (super.zCoord >> 4) - 2 << 4;
            this.z1Region = (super.zCoord >> 4) + 2 << 4;
         }

         this.isInit = true;
      }

   }

   public void invalidate() {
      super.invalidate();
      if(this.isInit && !super.worldObj.isRemote) {
         DarkNode.remove(super.xCoord, super.yCoord, super.zCoord, this.getIDNode());
      }

   }

   private void clearallMashines() {
      for(int i = 0; i < 10; ++i) {
         this.allMahines[i] = null;
      }

   }

   private void clearallMashine() {
      for(int i = 0; i < 10; ++i) {
         this.allMahine[i] = null;
      }

   }

   public void updateEntity() {
      super.updateEntity();
      if(this.tick++ > 60L) {
         this.tick = 0L;
         if(super.worldObj.isRemote) {
            return;
         }

         this.configureController();
         if(!this.isControllerOnline) {
            return;
         }
      }

   }

   public void readFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.darkEnergy = nbttagcompound.getLong("dEnerg");
   }

   public void writeToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
      nbttagcompound.setLong("dEnerg", this.darkEnergy);
   }

   public void addDarkEnergyController(short energy) {
      this.darkEnergy += (long)energy;
   }

   public void find(int x, int y, int z, int x1, int y1, int z1, HashMap nodes) {
      boolean index = true;

      for(int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; ++i) {
         if(!this.isControllerOnlineTemp) {
            return;
         }

         DarkNode d = (DarkNode)nodes.get(new Coords(x, y, z, ForgeDirection.VALID_DIRECTIONS[i]));
         if(d != null && !d.isActiv && (x1 != d.coords.x || y1 != d.coords.y || z1 != d.coords.z)) {
            d.isActiv = true;
            if(d.idNode == 2) {
               this.isControllerOnlineTemp = false;
               this.cables.clear();
               this.clearallMashines();
               this.clearallMashine();
               this.generators.clear();
               this.generator.clear();
               this.cables.trimToSize();
               this.generators.trimToSize();
               this.generator.trimToSize();
               break;
            }

            if(d.controller == null || d.controller.getCoords().equals(this.coords)) {
               switch(d.idNode) {
               case 0:
                  if(this.isRegionController(d.coords)) {
                     this.cables.add(d);
                  }
                  break;
               case 1:
               case 2:
                  if(this.isRegionController(d.coords)) {
                     this.generators.add(d);
                  }
                  break;
               case 3:
                  if(this.isRegionController(d.coords)) {
                     if(this.allMahines[3] == null) {
                        this.allMahines[3] = d;
                     } else {
                        d.ref.stop(this);
                     }
                  }
               case 4:
               default:
                  break;
               case 5:
                  if(this.isRegionController(d.coords)) {
                     this.generator.add(d);
                  }
                  break;
               case 6:
                  if(this.isRegionController(d.coords)) {
                     if(this.allMahine[6] == null) {
                        this.allMahine[6] = d;
                     } else {
                        d.ref.stop(this);
                     }
                  }
               }

               this.find(d.coords.x, d.coords.y, d.coords.z, x, y, z, nodes);
            }
         }
      }

   }

   public boolean isRegionController(Coords coords) {
      return coords.x >= this.xRegion && coords.x <= this.x1Region && coords.z >= this.zRegion && coords.z <= this.z1Region;
   }

   public void configureController() {
      try {
         DarkNode node;
         int k;
         if(this.isUpdateFindMashime) {
            this.cables.clear();
            this.clearallMashines();
            this.clearallMashine();
            this.generators.clear();
            this.generator.clear();
            this.nodes = new HashMap(DarkNode.configureController(super.xCoord, super.yCoord, super.zCoord));
            if(this.nodes == null) {
               return;
            }

            if(this.nodes.isEmpty()) {
               return;
            }

            ((DarkNode)this.nodes.get(new Coords(super.xCoord, super.yCoord, super.zCoord))).isActiv = true;
            this.isControllerOnlineTemp = true;
            this.find(super.xCoord, super.yCoord, super.zCoord, super.xCoord, super.yCoord, super.zCoord, this.nodes);
            this.isControllerOnline = this.isControllerOnlineTemp;
            this.isUpdateFindMashime = false;
            this.itemstacks = new ArrayList(2);
            if(this.cables.size() > 0) {
               this.itemstacks.add(new DarkItem((short)2601, (short)0, this.cables.size()));
            }

            if(this.generators.size() > 0) {
               this.itemstacks.add(new DarkItem((short)2603, (short)0, this.generators.size()));
            }

            if(this.generator.size() > 0) {
               this.itemstacks.add(new DarkItem((short)2707, (short)0, this.generator.size()));
            }

            if(this.allMahines[3] != null) {
               this.itemstacks.add(new DarkItem((short)2604, (short)0, 1));
            }

            if(this.allMahine[6] != null) {
               this.itemstacks.add(new DarkItem((short)2708, (short)0, 1));
            }

            this.changerGUI = true;
            if(!this.isControllerOnline) {
               return;
            }

            for(k = 0; k < this.cables.size(); ++k) {
               node = (DarkNode)this.cables.get(k);
               node.controller = this;
               node.isActiv = false;
            }

            for(k = 0; k < this.generators.size(); ++k) {
               node = (DarkNode)this.generators.get(k);
               node.controller = this;
               node.isActiv = false;
            }

            for(k = 0; k < this.allMahines.length; ++k) {
               node = this.allMahines[k];
               if(node != null) {
                  node.controller = this;
                  node.isActiv = false;
               }
            }

            while(k < this.generator.size()) {
               node = (DarkNode)this.generator.get(k);
               node.controller = this;
               node.isActiv = false;
               ++k;
            }

            for(k = 0; k < this.allMahines.length; ++k) {
               node = this.allMahines[k];
               if(node != null) {
                  node.controller = this;
                  node.isActiv = false;
               }
            }

            while(k < this.generators.size()) {
               node = (DarkNode)this.generators.get(k);
               node.controller = this;
               node.isActiv = false;
               ++k;
            }

            for(k = 0; k < this.allMahine.length; ++k) {
               node = this.allMahine[k];
               if(node != null) {
                  node.controller = this;
                  node.isActiv = false;
               }
            }

            while(k < this.generator.size()) {
               node = (DarkNode)this.generator.get(k);
               node.controller = this;
               node.isActiv = false;
               ++k;
            }

            for(k = 0; k < this.allMahine.length; ++k) {
               node = this.allMahine[k];
               if(node != null) {
                  node.controller = this;
                  node.isActiv = false;
               }
            }
         }

         if(!this.isControllerOnline) {
            return;
         }

         if(super.worldObj.getWorldInfo().getDimension() != 0) {
            return;
         }

         for(k = 0; k < this.generators.size(); ++k) {
            ((DarkNode)this.generators.get(k)).ref.update(this);
         }

         for(k = 0; k < this.generator.size(); ++k) {
            ((DarkNode)this.generator.get(k)).ref.update(this);
         }

         for(k = 0; k < this.allMahines.length; ++k) {
            node = this.allMahines[k];
            if(node != null) {
               node.ref.update(this);
            }
         }

         for(k = 0; k < this.allMahine.length; ++k) {
            node = this.allMahine[k];
            if(node != null) {
               node.ref.update(this);
            }
         }

         if(this.darkEnergyDouble != this.darkEnergy) {
            this.changerGUI = true;
            this.darkEnergyDouble = this.darkEnergy;
         }
      } catch (Exception var3) {
         ;
      }

   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public int getSizeInventory() {
      return this.ItemStacks.length;
   }

   public ItemStack getStackInSlot(int i) {
      return this.ItemStacks[i];
   }

   public ItemStack decrStackSize(int i, int j) {
      return null;
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

      if(itemstack != null) {
         itemstack.stackSize = 0;
      }

   }

   public String getInvName() {
      return "TileDarkEnergyControllerInv";
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
      return null;
   }

   public boolean canInsertItem(int i, ItemStack itemstack, int j) {
      return false;
   }

   public boolean canExtractItem(int i, ItemStack itemstack, int j) {
      return false;
   }

   public int getIDNode() {
      return 2;
   }

   public void update(TileEntityBlockDarkEnergyControler controller) {}

   public void updateController() {
      this.isUpdateFindMashime = true;
   }

   public void stop(TileEntityBlockDarkEnergyControler controller) {}

   public Coords getCoords() {
      return this.coords;
   }
}

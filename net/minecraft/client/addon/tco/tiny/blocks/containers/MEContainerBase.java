package net.minecraft.client.addon.tco.tiny.blocks.containers;

import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.Utils.ILANetworkInventory;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.METileEntityBase;
import net.minecraft.client.addon.tco.tiny.blocks.slots.ISlotPlayerSide;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFake;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFakeUP64;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotFakeUP64DonateSpecial;
import net.minecraft.client.addon.tco.tiny.blocks.slots.MESlotTrader;
import net.minecraft.client.addon.tco.tiny.blocks.slots.NullSlot;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotDisabled;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotInaccessable;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotME;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketClickMESlotFakeUP64;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketClickSlotME;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketInvCommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public abstract class MEContainerBase extends Container {

   protected EntityPlayer who;
   protected METileEntityBase worldEntity;
   public boolean forceUpdate = false;
   public boolean wasDragging = false;


   public Slot getSlot(int par1) {
      Object s = null;
      if(super.inventorySlots.size() > par1) {
         s = super.getSlot(par1);
      }

      if(s == null) {
         s = new NullSlot();
      }

      return (Slot)s;
   }

   public Slot getSlotFromInventory(IInventory par1iInventory, int par2) {
      Object s = super.getSlotFromInventory(par1iInventory, par2);
      if(s == null) {
         s = new NullSlot();
      }

      return (Slot)s;
   }

   public boolean func_94531_b(Slot slot) {
      if(slot instanceof MESlotFake) {
         if(this.who != null && this.who.inventory.getItemStack() != null) {
            try {
               this.wasDragging = true;
               ItemStack g = this.who.inventory.getItemStack().copy();
               g.stackSize = 1;
               if(Utils.isClient()) {
                  PacketDispatcher.sendPacketToServer((new PacketInvCommand(PacketInvCommand.PacketInvCommands.DRAG_PLACEMENT, slot.slotNumber)).makePacket());
               }

               ((MESlotFake)slot).putStack(g);
            } catch (Exception var3) {
               ;
            }
         }

         return false;
      } else {
         return !(slot instanceof SlotME);
      }
   }

   public void detectAndSendChanges() {
      for(int i = 0; i < super.inventorySlots.size(); ++i) {
         Slot s = (Slot)super.inventorySlots.get(i);
         if(!(s instanceof SlotME)) {
            ItemStack itemstack = s.getStack();
            ItemStack itemstack1 = (ItemStack)super.inventoryItemStacks.get(i);
            if(!ItemStack.areItemStacksEqual(itemstack1, itemstack)) {
               itemstack1 = itemstack == null?null:itemstack.copy();
               super.inventoryItemStacks.set(i, itemstack1);

               for(int j = 0; j < super.crafters.size(); ++j) {
                  ((ICrafting)super.crafters.get(j)).sendSlotContents(this, i, itemstack1);
               }
            }
         }
      }

   }

   public Packet getSlotMEPacket(SlotME slot, int mouseClick, int shift) throws IOException {
      ItemStack is = slot.getCmpStack();
      ItemStack is1 = this.who.inventory.getItemStack();
      if(is1 != null && is != null && (!is.isItemEqual(is1) || !ItemStack.areItemStackTagsEqual(is, is1))) {
         return null;
      } else {
         NBTTagCompound c = new NBTTagCompound();
         NBTTagCompound i = new NBTTagCompound();
         int isSize = 0;
         if(is != null) {
            isSize = is.stackSize;
            is.writeToNBT(i);
         }

         c.setCompoundTag("#", i);
         i.setInteger("C", isSize);
         return (new PacketClickSlotME(mouseClick, shift, slot.slotNumber, c)).makePacket();
      }
   }

   public void handleSlotMEPacket(int mouseClick, int shift, int slotNumber, NBTTagCompound c) {
      NBTTagCompound i = c.getCompoundTag("#");
      ItemStack s = ItemStack.loadItemStackFromNBT(i);
      if(s != null) {
         s.stackSize = i.getInteger("C");
      }

      if(s != null) {
         if(slotNumber >= super.inventorySlots.size()) {
            return;
         }

         Slot sl = this.getSlot(slotNumber);
         if(sl != null && sl instanceof SlotME) {
            SlotME sme = (SlotME)sl;
            if(sme.attuneSlot(s)) {
               this.slotClick(sme.slotNumber, mouseClick, shift, this.who);
            }

            sme.resetSlot();
         }
      } else if(this.who.inventory.getItemStack() != null) {
         if(slotNumber >= super.inventorySlots.size()) {
            return;
         }

         if(this.getSlot(slotNumber) instanceof SlotME) {
            this.slotClick(slotNumber, mouseClick, shift, this.who);
         }
      }

   }

   public Packet getMESlotFakeUP64Packet(MESlotFakeUP64 slot, int mouseClick, int shift) throws IOException {
      return !slot.isItemValid((ItemStack)null)?null:(new PacketClickMESlotFakeUP64(mouseClick, shift, slot.slotNumber)).makePacket();
   }

   public void handleMESlotFakeUP64Packet(int mouseClick, int shift, int slotNumber) {
      if(slotNumber < super.inventorySlots.size()) {
         Slot sl = this.getSlot(slotNumber);
         if(sl != null && sl instanceof MESlotFakeUP64) {
            this.slotClick(((MESlotFakeUP64)sl).slotNumber, mouseClick, shift, this.who);
         }
      }

   }

   protected MEContainerBase(EntityPlayer _who, TileEntity t) {
      this.who = _who;
      this.worldEntity = (METileEntityBase)t;
   }

   public void addCraftingToCrafters(ICrafting par1iCrafting) {
      super.addCraftingToCrafters(par1iCrafting);
      if(Utils.isServer() && this.worldEntity != null) {
         this.worldEntity.triggerContainerUpdate();
      }

   }

   public ItemStack slotClick(int slotNum, int mouseClick, int holdingShift, EntityPlayer p) {
      if(slotNum >= 0 && slotNum < super.inventorySlots.size()) {
         Slot var141 = (Slot)super.inventorySlots.get(slotNum);
         if(var141 instanceof SlotDisabled) {
            return null;
         }

         ArrayList var151 = new ArrayList();
         int mes;
         Slot held;
         if(var141 instanceof ISlotPlayerSide) {
            for(mes = 0; mes < super.inventorySlots.size(); ++mes) {
               held = (Slot)super.inventorySlots.get(mes);
               if(!(held instanceof ISlotPlayerSide) && !(held instanceof MESlotFake)) {
                  var151.add(held);
               }
            }
         } else {
            for(mes = 0; mes < super.inventorySlots.size(); ++mes) {
               held = (Slot)super.inventorySlots.get(mes);
               if(held instanceof ISlotPlayerSide && !(held instanceof MESlotFake)) {
                  var151.add(held);
               }
            }
         }

         ItemStack r;
         ItemStack var16;
         if(var141 instanceof MESlotFakeUP64DonateSpecial) {
            MESlotFakeUP64DonateSpecial var18 = (MESlotFakeUP64DonateSpecial)var141;
            if(!var18.isItemValid((ItemStack)null)) {
               return null;
            }

            var16 = p.inventory.getItemStack();
            if(var16 != null) {
               if(var18.isItemValid(var16)) {
                  boolean var20 = false;
                  if(var18.getHasStack() && var16.isItemEqual(var18.getStack())) {
                     if(mouseClick > 0) {
                        r = var16.copy();
                        r.stackSize = 1;
                        var18.addToInv(r);
                        var20 = true;
                     } else {
                        var18.addToInv(var16.copy());
                        var20 = true;
                     }
                  } else if(var18.invSlot == 0) {
                     var18.addToInv(var16.copy());
                     var20 = true;
                  }

                  if(var20) {
                     if(var18.getStack().stackSize > 10) {
                        var18.getSl1().addToInvDonateItem(var18.getStack().stackSize);
                     } else if(var16.stackSize > 10) {
                        var18.getSl1().addToInvDonateItem(var16.stackSize);
                     } else {
                        var18.getSl1().addToInvDonateItem(10);
                     }
                  }
               }
            } else if(var18.isCoin()) {
               var18.incCoin();
            } else {
               var18.addToInv((ItemStack)null);
               var18.getSl1().addToInvDonateItem(0);
            }

            this.updateSlot(var141);
            return null;
         }

         ItemStack inSlot;
         if(var141 instanceof MESlotFakeUP64) {
            MESlotFakeUP64 var19 = (MESlotFakeUP64)var141;
            var16 = p.inventory.getItemStack();
            if(var16 != null) {
               if(var19.isItemValid(var16)) {
                  if(mouseClick > 0) {
                     inSlot = var16.copy();
                     inSlot.stackSize = 1;
                     var19.addToInv(inSlot);
                  } else {
                     var19.addToInv(var16);
                  }
               }
            } else if(mouseClick > 0) {
               var19.decrStackSize(1);
            } else {
               var19.addToInv((ItemStack)null);
            }

            this.updateSlot(var141);
            return null;
         }

         if(var141 instanceof MESlotFake) {
            MESlotFake var17 = (MESlotFake)var141;
            var16 = p.inventory.getItemStack();
            if(var16 != null) {
               if(var17.isItemValid(var16)) {
                  if(mouseClick > 0) {
                     inSlot = var16.copy();
                     inSlot.stackSize = 1;
                     var17.addToInv(inSlot);
                  } else {
                     var17.addToInv(var16);
                  }
               }
            } else if(mouseClick > 0) {
               var17.decrStackSize(1);
            } else {
               var17.addToInv((ItemStack)null);
            }

            this.updateSlot(var141);
            return null;
         }

         int halfStack;
         if(var141 instanceof MESlotTrader) {
            MESlotTrader var161 = (MESlotTrader)var141;
            var16 = p.inventory.getItemStack();
            if(holdingShift == 1 && mouseClick > 0) {
               if(var16 == null || Utils.isSameItem(var161.getStack(), var16)) {
                  if(var16 == null) {
                     p.inventory.setItemStack(var161.decrStackSize(1));
                  } else if(var16.stackSize < var16.getMaxStackSize()) {
                     inSlot = var161.decrStackSize(1);
                     if(inSlot != null) {
                        var16.stackSize += inSlot.stackSize;
                     }
                  }
               }
            } else if(var16 != null) {
               if(var161.isItemValid(var16)) {
                  if(mouseClick > 0) {
                     inSlot = var16.splitStack(1);
                     r = var161.addToInv(inSlot);
                     if(r != null) {
                        var16.stackSize += r.stackSize;
                     }

                     if(var16.stackSize <= 0) {
                        p.inventory.setItemStack((ItemStack)null);
                     }
                  } else {
                     inSlot = var161.addToInv(var16);
                     p.inventory.setItemStack(inSlot);
                     if(holdingShift != 1) {
                        ;
                     }
                  }

                  return null;
               }
            } else {
               inSlot = var161.getStack();
               if(inSlot != null) {
                  if(holdingShift != 1) {
                     r = null;
                     if(mouseClick == 0) {
                        r = var161.decrStackSize(inSlot.getMaxStackSize());
                     } else if(mouseClick != 2) {
                        halfStack = inSlot.stackSize > inSlot.getMaxStackSize()?inSlot.getMaxStackSize() / 2:(int)Math.ceil((double)((float)inSlot.stackSize / 2.0F));
                        if(halfStack < 1) {
                           halfStack = 1;
                        }

                        r = var161.decrStackSize(halfStack);
                     }

                     p.inventory.setItemStack(r);
                     return null;
                  }

                  this.transferStackInSlot(p, slotNum);
               }
            }

            return null;
         }

         if(var141 instanceof SlotME) {
            SlotME var14 = (SlotME)var141;
            var16 = p.inventory.getItemStack();
            if(holdingShift == 1 && mouseClick > 0) {
               if(var16 == null || Utils.isSameItem(var14.getStack(), var16)) {
                  if(var16 == null) {
                     p.inventory.setItemStack(var14.decrStackSize(1));
                  } else if(var16.stackSize < var16.getMaxStackSize()) {
                     inSlot = var14.decrStackSize(1);
                     if(inSlot != null) {
                        var16.stackSize += inSlot.stackSize;
                     }
                  }
               }
            } else if(var16 != null) {
               if(var14.isItemValid(var16)) {
                  if(mouseClick > 0) {
                     inSlot = var16.splitStack(1);
                     r = var14.addToInv(inSlot);
                     if(r != null) {
                        var16.stackSize += r.stackSize;
                     }

                     if(var16.stackSize <= 0) {
                        p.inventory.setItemStack((ItemStack)null);
                     }
                  } else {
                     inSlot = var14.addToInv(var16);
                     p.inventory.setItemStack(inSlot);
                     if(holdingShift != 1) {
                        ;
                     }
                  }

                  return null;
               }
            } else {
               inSlot = var14.getStack();
               if(inSlot != null) {
                  if(holdingShift != 1) {
                     r = null;
                     if(mouseClick == 0) {
                        r = var14.decrStackSize(inSlot.getMaxStackSize());
                     } else if(mouseClick != 2) {
                        halfStack = inSlot.stackSize > inSlot.getMaxStackSize()?inSlot.getMaxStackSize() / 2:(int)Math.ceil((double)((float)inSlot.stackSize / 2.0F));
                        if(halfStack < 1) {
                           halfStack = 1;
                        }

                        r = var14.decrStackSize(halfStack);
                     }

                     p.inventory.setItemStack(r);
                     return null;
                  }

                  this.transferStackInSlot(p, slotNum);
               }
            }

            return null;
         }
      }

      if(holdingShift == 2) {
         Iterator var141 = super.inventorySlots.iterator();

         while(var141.hasNext()) {
            Slot var15 = (Slot)var141.next();
            if(var15.isSlotInInventory(p.inventory, mouseClick) && var15 instanceof SlotDisabled) {
               return null;
            }
         }
      }

      return super.slotClick(slotNum, mouseClick, holdingShift, p);
   }

   public final ItemStack transferStackInSlot(EntityPlayer p, int idx) {
      if(Utils.isClient()) {
         return null;
      } else {
         boolean hasMETiles = false;
         Iterator tis = super.inventorySlots.iterator();

         while(tis.hasNext()) {
            Object var171 = tis.next();
            if(var171 instanceof SlotME) {
               hasMETiles = true;
               break;
            }
         }

         if(hasMETiles && Utils.isClient()) {
            return null;
         } else {
            tis = null;
            Slot var17 = (Slot)super.inventorySlots.get(idx);
            if(!(var17 instanceof SlotDisabled) && !(var17 instanceof SlotInaccessable)) {
               if(var17 != null && var17.getHasStack()) {
                  ItemStack is = var17.getStack();
                  if(is == null) {
                     return null;
                  }

                  ItemStack var13;
                  if(var17 instanceof SlotME) {
                     var13 = ((SlotME)var17).decrStackSize(64);
                  } else if(var17 instanceof MESlotTrader) {
                     var13 = ((MESlotTrader)var17).decrStackSize(64);
                  } else {
                     var13 = is.copy();
                  }

                  ArrayList selectedSlots = new ArrayList();
                  int isr;
                  Slot d;
                  if(var17 instanceof ISlotPlayerSide) {
                     for(isr = 0; isr < super.inventorySlots.size(); ++isr) {
                        d = (Slot)super.inventorySlots.get(isr);
                        if(!(d instanceof ISlotPlayerSide) && !(d instanceof MESlotFake) && !(d instanceof MESlotFakeUP64) && !(d instanceof MESlotFakeUP64DonateSpecial) && d.isItemValid(is)) {
                           selectedSlots.add(d);
                        }
                     }
                  } else {
                     for(isr = 0; isr < super.inventorySlots.size(); ++isr) {
                        d = (Slot)super.inventorySlots.get(isr);
                        if(d instanceof ISlotPlayerSide && (!(d instanceof MESlotFake) || !(d instanceof MESlotFakeUP64)) && d.isItemValid(is)) {
                           selectedSlots.add(d);
                        }
                     }
                  }

                  ItemStack tmp;
                  int placeAble;
                  if(selectedSlots.isEmpty() && var17 instanceof ISlotPlayerSide) {
                     ItemStack var18 = var17.getStack();
                     if(var18 != null) {
                        for(placeAble = 0; placeAble < super.inventorySlots.size(); ++placeAble) {
                           Slot var19 = (Slot)super.inventorySlots.get(placeAble);
                           tmp = var19.getStack();
                           if(!(var19 instanceof ISlotPlayerSide) && (var19 instanceof MESlotFake || var19 instanceof MESlotFakeUP64)) {
                              if(Utils.isSameItem(tmp, var18)) {
                                 return null;
                              }

                              if(tmp == null) {
                                 var19.putStack(var18);
                                 var19.onSlotChanged();
                                 this.updateSlot(var19);
                                 return null;
                              }
                           }
                        }
                     }
                  }

                  Iterator var181 = selectedSlots.iterator();

                  int var192;
                  ItemStack var191;
                  while(var181.hasNext()) {
                     d = (Slot)var181.next();
                     if(!(d instanceof SlotDisabled)) {
                        if(d instanceof SlotME) {
                           if(d.isItemValid(var13) && var13 != null) {
                              var13 = ((SlotME)d).addToInv(var13);
                           }
                        } else if(d instanceof MESlotTrader) {
                           if(d.isItemValid(var13) && var13 != null) {
                              var13 = ((MESlotTrader)d).addToInv(var13);
                           }
                        } else if(d.isItemValid(var13) && var13 != null && d.getHasStack()) {
                           var191 = d.getStack();
                           if(var13 != null && Utils.isSameItem(var13, var191)) {
                        	   var192 = var191.getMaxStackSize();
                              if(var192 > d.getSlotStackLimit()) {
                            	  var192 = d.getSlotStackLimit();
                              }

                              placeAble = var192 - var191.stackSize;
                              if(var13.stackSize < placeAble) {
                                 placeAble = var13.stackSize;
                              }

                              var191.stackSize += placeAble;
                              var13.stackSize -= placeAble;
                              if(var13.stackSize <= 0) {
                                 var17.putStack((ItemStack)null);
                                 d.onSlotChanged();
                                 this.updateSlot(var17);
                                 this.updateSlot(d);
                                 return null;
                              }

                              this.updateSlot(d);
                           }
                        }
                     }
                  }
                  var181 = selectedSlots.iterator();

                  while(var181.hasNext()) {
                     d = (Slot)var181.next();
                     if(!(d instanceof SlotDisabled) && !(d instanceof MESlotTrader) && !(d instanceof SlotME) && d.isItemValid(var13) && var13 != null) {
                        if(d.getHasStack()) {
                           var191 = d.getStack();
                           if(var13 != null && Utils.isSameItem(var191, var13)) {
                        	   var192 = var191.getMaxStackSize();
                              if(d.getSlotStackLimit() < var192) {
                            	  var192 = d.getSlotStackLimit();
                              }

                              placeAble = var192 - var191.stackSize;
                              if(var13.stackSize < placeAble) {
                                 placeAble = var13.stackSize;
                              }

                              var191.stackSize += placeAble;
                              var13.stackSize -= placeAble;
                              if(var13.stackSize <= 0) {
                                 var17.putStack((ItemStack)null);
                                 d.onSlotChanged();
                                 this.updateSlot(var17);
                                 this.updateSlot(d);
                                 return null;
                              }

                              this.updateSlot(d);
                           }
                        } else {
                           int var201 = var13.getMaxStackSize();
                           if(var201 > d.getSlotStackLimit()) {
                              var201 = d.getSlotStackLimit();
                           }

                           tmp = var13.copy();
                           if(tmp.stackSize > var201) {
                              tmp.stackSize = var201;
                           }

                           var13.stackSize -= tmp.stackSize;
                           d.putStack(tmp);
                           if(var13.stackSize <= 0) {
                              var17.putStack((ItemStack)null);
                              d.onSlotChanged();
                              this.updateSlot(var17);
                              this.updateSlot(d);
                              return null;
                           }

                           this.updateSlot(d);
                        }
                     }
                  }

                  ArrayList var20;
                  if(var17 instanceof SlotME) {
                     var13 = ((SlotME)var17).addToInv(var13);
                     if(var13 != null && this.worldEntity != null) {
                        var20 = new ArrayList();
                        var20.add(var13);
                        Utils.spawnDrops(p.worldObj, this.worldEntity.xCoord, this.worldEntity.yCoord, this.worldEntity.zCoord, var20);
                     }

                     this.updateSlot(var17);
                     return null;
                  }

                  if(var17 instanceof MESlotTrader) {
                     var13 = ((MESlotTrader)var17).addToInv(var13);
                     if(var13 != null && this.worldEntity != null) {
                        var20 = new ArrayList();
                        var20.add(var13);
                        Utils.spawnDrops(p.worldObj, this.worldEntity.xCoord, this.worldEntity.yCoord, this.worldEntity.zCoord, var20);
                     }

                     this.updateSlot(var17);
                     return null;
                  }

                  var17.putStack(var13);
               }

               this.updateSlot(var17);
               return null;
            } else {
               return null;
            }
         }
      }
   }

   private void updateSlot(Slot s) {
      if(!(s instanceof SlotME) && !(s instanceof MESlotFakeUP64) && !(s instanceof MESlotFakeUP64DonateSpecial) && !Utils.isClient()) {
         EntityPlayerMP p = (EntityPlayerMP)this.getPlayer();
         p.sendSlotContents(this, s.slotNumber, s.getStack());
      }

   }

   public void updateClient() {}

   public void triggerUpdate() {
      if(Utils.isServer()) {
         this.updateClient();
         EntityPlayerMP pmp = (EntityPlayerMP)this.who;
         ArrayList var3 = new ArrayList();

         for(int var4 = 0; var4 < super.inventorySlots.size(); ++var4) {
            Slot s = (Slot)super.inventorySlots.get(var4);
            ItemStack is = s.getStack();
            var3.add(is);
         }

         pmp.sendContainerAndContentsToPlayer(this, var3);
      }

   }

   public EntityPlayer getPlayer() {
      return this.who;
   }

   public METileEntityBase getTile() {
      return this.worldEntity;
   }

   public void setTile(METileEntityBase a) {
      this.worldEntity = a;
   }

   public boolean canInteractWith(EntityPlayer v) {
      return this.getTile().isCommonUseableByPlayer(v);
   }

   public boolean isValid() {
      return true;
   }

   public abstract ILANetworkInventory GetNetworkIME();

   public void update() {}

   public void HandleUpdateInt(int idInt, EntityPlayer pl) {}
}

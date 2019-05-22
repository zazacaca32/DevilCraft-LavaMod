package codechicken.nei;

import codechicken.core.inventory.ContainerExtended;
import codechicken.core.inventory.InventoryNBT;
import codechicken.core.inventory.InventoryUtils;
import codechicken.core.inventory.SlotHandleClicks;
import codechicken.core.packet.PacketCustom;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;

public class ContainerPotionCreator extends ContainerExtended {

   InventoryPlayer playerInv;
   InventoryBasic potionInv;
   IInventory potionStoreInv;


   public ContainerPotionCreator(InventoryPlayer inventoryPlayer, IInventory potionStoreInv) {
      this.playerInv = inventoryPlayer;
      this.potionInv = new InventoryBasic("Potion", true, 1);
      this.potionStoreInv = potionStoreInv;
      this.a1(new ContainerPotionCreator.SlotPotion(this.potionInv, 0, 25, 102));

      for(int i = 0; i < 9; ++i) {
         this.a(new ContainerPotionCreator.SlotPotionStore(potionStoreInv, i, 8 + i * 18, 14));
      }

      this.bindPlayerInventory(inventoryPlayer, 8, 125);
   }

  

   private Object a(ItemStack stack, int i, int j, boolean b) {
	// TODO Auto-generated method stub
	return null;
}

public boolean a(SlotPotionStore slotPotionStore) {
      return true;
   }
   public boolean a1(SlotPotion slotPotion) {
	      return true;
	   }
   public void b(EntityPlayer player) {
   
      if(!player.worldObj.isRemote) {
         InventoryUtils.dropOnClose(player, this.potionInv);
      }

   }

   public void handleInputPacket(PacketCustom packet) {
      ItemStack potion = this.potionInv.getStackInSlot(0);
      if(potion != null) {
         NBTTagList potionTagList = potion.getTagCompound().getTagList("CustomPotionEffects");
         int i;
         PotionEffect pe;
         if(packet.readBoolean()) {
            PotionEffect rmID = new PotionEffect(packet.readUnsignedByte(), packet.readInt(), packet.readUnsignedByte());

            for(i = 0; i < potionTagList.tagCount(); ++i) {
               pe = PotionEffect.readCustomPotionEffectFromNBT((NBTTagCompound)potionTagList.tagAt(i));
               if(pe.getPotionID() == rmID.getPotionID()) {
                  potionTagList.tagList.set(i, rmID.writeCustomPotionEffectToNBT(new NBTTagCompound()));
                  return;
               }
            }

            potionTagList.appendTag(rmID.writeCustomPotionEffectToNBT(new NBTTagCompound()));
         } else {
            int var7 = packet.readUnsignedByte();
            i = 0;

            while(i < potionTagList.tagCount()) {
               pe = PotionEffect.readCustomPotionEffectFromNBT((NBTTagCompound)potionTagList.tagAt(i));
               if(pe.getPotionID() == var7) {
                  potionTagList.removeTag(i);
               } else {
                  ++i;
               }
            }
         }

      }
   }

   public void setPotionEffect(int effectID, int duration, int amplifier) {
      PacketCustom packet = NEICPH.createContainerPacket();
      packet.writeBoolean(true);
      packet.writeByte(effectID);
      packet.writeInt(duration);
      packet.writeByte(amplifier);
      packet.sendToServer();
   }

   public void removePotionEffect(int effectID) {
      PacketCustom packet = NEICPH.createContainerPacket();
      packet.writeBoolean(false);
      packet.writeByte(effectID);
      packet.sendToServer();
   }

   public class SlotPotionStore extends SlotHandleClicks {

      public SlotPotionStore(IInventory inv, int slotIndex, int x, int y) {
         super(inv, slotIndex, x, y);
      }

      public ItemStack slotClick(ContainerExtended container, EntityPlayer player, int button, int modifier) {
         ItemStack held = player.inventory.getItemStack();
         if(button == 0 && modifier == 1) {
            NEIClientUtils.cheatItem(held, button, -1);
         } else if(button == 1) {
            this.c((ItemStack)null);
         } else if(held != null) {
            if(this.a(held)) {
               this.c(InventoryUtils.copyStack(held, 1));
               player.inventory.setItemStack((ItemStack)null);
            }
         } else if(this.d()) {
            player.inventory.setItemStack(held);
         }

         return null;
      }

      private boolean d() {
		// TODO Auto-generated method stub
		return false;
	}

	private void c(ItemStack copyStack) {
		// TODO Auto-generated method stub
		
	}

	public boolean a(ItemStack stack) {
         return stack.getItem() instanceof ItemPotion;
      }
   }

   public class SlotPotion extends Slot {

      public SlotPotion(IInventory inv, int slotIndex, int x, int y) {
         super(inv, slotIndex, x, y);
      }

      public boolean isItemValid(ItemStack stack) {
         return stack.getItem() instanceof ItemPotion;
      }

      public void onSlotChanged() {
         super.onSlotChanged();
         if(this.getHasStack()) {
            ItemStack stack = this.getStack();
            if(!stack.hasTagCompound()) {
               stack.setTagCompound(new NBTTagCompound("tag"));
            }

            if(!stack.getTagCompound().hasKey("CustomPotionEffects")) {
               stack.getTagCompound().setTag("CustomPotionEffects", new NBTTagList());
            }
         }

      }
   }

   public static class InventoryPotionStore extends InventoryNBT {

      public InventoryPotionStore() {
         super(9, NEIClientConfig.saveCompound.getCompoundTag("potionStore"));
      }

      public void k_() {
         NEIClientConfig.saveCompound.setCompoundTag("potionStore", this.tag);
         NEIClientConfig.saveConfig();
      }
   }
}

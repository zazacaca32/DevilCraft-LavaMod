package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tchestplate.donate.api.IDonateUser;
import net.minecraft.client.addon.tchestplate.donate.api.IOffert;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Manager;
import net.minecraft.client.addon.tco.tiny.blocks.recipes.Slot_R;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBlockLavaAnvil extends TileEntityBlockBase implements IOffert {

   public TileEntityBlockLavaAnvil() {
      super.RecipesIndex = Manager.INSTANCE.getIndex(this.getClass());
      super.ItemStacks = new ItemStack[1];
   }

   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readFromNBT(par1NBTTagCompound);
   }

   public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeToNBT(par1NBTTagCompound);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean canUpdate() {
      return false;
   }

   public void updateEntity() {}

   public void openChest() {}

   public void closeChest() {}

   public boolean isStackValidForSlot(int i, ItemStack itemstack) {
      if(i <= 11) {
         return false;
      } else {
         try {
            return ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(i)).EqualSlotAsItemIDandDamage(itemstack);
         } catch (Exception var4) {
            return false;
         }
      }
   }

   String getInventoryName() {
      return "container.LavaAnvil";
   }

   public BaseModelBlock newRenderModel(int blockMetadata) {
      return null;
   }

   public short getEnchantHammer(ItemStack par1ItemStack) {
      return par1ItemStack.stackTagCompound != null && par1ItemStack.stackTagCompound.hasKey("hup")?par1ItemStack.stackTagCompound.getShort("hup"):0;
   }

   public void startOffers(EntityPlayer player) {
      Object[] obj;
      int price;
      if(super.ItemStacks[0] != null && (obj = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItemID(super.ItemStacks[0])) != null && (price = ((Integer)obj[0]).intValue()) > 0) {
         boolean flag = false;
         if(super.ItemStacks[0].stackTagCompound != null) {
            if(super.ItemStacks[0].stackTagCompound.hasKey("lHammerstart")) {
               short user111111 = this.getEnchantHammer(super.ItemStacks[0]);
               if(user111111 > 0) {
                  price *= user111111;
               }

               flag = true;
            } else if(super.ItemStacks[0].stackTagCompound.hasKey("lStaffFriz")) {
               flag = true;
            } else if(super.ItemStacks[0].stackTagCompound.hasKey("lswordstart")) {
               flag = true;
            }
         }

         IDonateUser user1111111 = net.minecraft.client.addon.tchestplate.donate.api.Manager.getDonateUser(player);
         if(flag) {
            user1111111.setStatus(1);
            if(!user1111111.startOffert(price, this)) {
               user1111111.setStatus(10);
               user1111111.setStatus(0);
            }
         } else {
            user1111111.setStatus(9);
            user1111111.setStatus(0);
         }
      }

   }

   public void JobOffer(IDonateUser user, int donate, int getfirstDonate) {
      user.setStatus(2);
      if(super.ItemStacks[0] != null) {
         Object[] obj = ((Slot_R)Manager.INSTANCE.Get(super.RecipesIndex).get(0)).getSlotAsDataItemID(super.ItemStacks[0]);
         if(obj != null) {
            int price = ((Integer)obj[0]).intValue();
            short i = this.getEnchantHammer(super.ItemStacks[0]);
            if(i > 0) {
               price *= i;
            }

            if(price == getfirstDonate && donate == 0) {
               boolean flag = false;
               if(super.ItemStacks[0].stackTagCompound != null) {
                  if(super.ItemStacks[0].stackTagCompound.hasKey("lHammerstart")) {
                     flag = true;
                     super.ItemStacks[0].stackTagCompound.removeTag("lHammerstart");
                  } else if(super.ItemStacks[0].stackTagCompound.hasKey("lStaffFriz")) {
                     flag = true;
                     super.ItemStacks[0].stackTagCompound.removeTag("lStaffFriz");
                  } else if(super.ItemStacks[0].stackTagCompound.hasKey("lswordstart")) {
                     flag = true;
                     super.ItemStacks[0].stackTagCompound.removeTag("lswordstart");
                  }
               }

               if(!flag) {
                  user.setStatus(14);
                  user.setStatus(0);
               }

               user.setStatus(0);
            } else {
               user.setStatus(13);
               user.setStatus(0);
            }
         } else {
            user.setStatus(12);
            user.setStatus(0);
         }
      } else {
         user.setStatus(11);
         user.setStatus(0);
      }

   }
}

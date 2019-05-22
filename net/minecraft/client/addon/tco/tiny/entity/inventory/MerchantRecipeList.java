package net.minecraft.client.addon.tco.tiny.entity.inventory;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipe;
import net.minecraft.client.addon.tco.tiny.packets.newpack.PacketMA;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class MerchantRecipeList extends ArrayList {

   public MerchantRecipeList() {}

   public MerchantRecipeList(NBTTagCompound par1NBTTagCompound) {
      this.readRecipiesFromTags(par1NBTTagCompound);
   }

   public MerchantRecipe canRecipeBeUsed(ItemStack par1ItemStack, ItemStack par2ItemStack, int par3) {
      if(par3 >= 0 && par3 < this.size()) {
         try {
            MerchantRecipe merchantrecipe = (MerchantRecipe)this.get(par3);
            if(par1ItemStack.isItemEqual(merchantrecipe.getItemToBuy()) && ItemStack.areItemStackTagsEqual(par1ItemStack, merchantrecipe.getItemToBuy()) && (par2ItemStack == null && !merchantrecipe.hasSecondItemToBuy() || merchantrecipe.hasSecondItemToBuy() && par2ItemStack != null && par2ItemStack.isItemEqual(merchantrecipe.getSecondItemToBuy()) && ItemStack.areItemStackTagsEqual(par2ItemStack, merchantrecipe.getSecondItemToBuy())) && par1ItemStack.stackSize >= merchantrecipe.getItemToBuy().stackSize && (!merchantrecipe.hasSecondItemToBuy() || par2ItemStack.stackSize >= merchantrecipe.getSecondItemToBuy().stackSize)) {
               return merchantrecipe;
            }

            return null;
         } catch (Exception var5) {
            ;
         }
      }

      return null;
   }

   public void addToListWithCheck(MerchantRecipe par1MerchantRecipe) {
      for(int i = 0; i < this.size(); ++i) {
         MerchantRecipe merchantrecipe1 = (MerchantRecipe)this.get(i);
         if(par1MerchantRecipe.hasSameIDsAs(merchantrecipe1)) {
            if(par1MerchantRecipe.hasSameItemsAs(merchantrecipe1)) {
               this.set(i, par1MerchantRecipe);
            }

            return;
         }
      }

      this.add(par1MerchantRecipe);
   }

   public void writeRecipiesToStream(ByteArrayDataOutput par1DataOutputStream) throws IOException {
      par1DataOutputStream.writeByte((byte)(this.size() & 255));

      for(int i = 0; i < this.size(); ++i) {
         MerchantRecipe merchantrecipe = (MerchantRecipe)this.get(i);
         PacketMA.writeItemStack(merchantrecipe.getItemToBuy(), par1DataOutputStream);
         PacketMA.writeItemStack(merchantrecipe.getItemToSell(), par1DataOutputStream);
         ItemStack itemstack = merchantrecipe.getSecondItemToBuy();
         par1DataOutputStream.writeBoolean(itemstack != null);
         if(itemstack != null) {
            PacketMA.writeItemStack(itemstack, par1DataOutputStream);
         }

         par1DataOutputStream.writeBoolean(merchantrecipe.ismaxTradeUses());
         par1DataOutputStream.writeInt(merchantrecipe.getToolUses());
      }

   }

   @SideOnly(Side.CLIENT)
   public static MerchantRecipeList readRecipiesFromStream(ByteArrayDataInput par0DataInputStream) throws IOException {
      MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
      int i = par0DataInputStream.readByte() & 255;

      for(int j = 0; j < i; ++j) {
         ItemStack itemstack = PacketMA.readItemStack(par0DataInputStream);
         ItemStack itemstack2 = PacketMA.readItemStack(par0DataInputStream);
         ItemStack itemstack3 = null;
         if(par0DataInputStream.readBoolean()) {
            itemstack3 = PacketMA.readItemStack(par0DataInputStream);
         }

         boolean flag = par0DataInputStream.readBoolean();
         int count = par0DataInputStream.readInt();
         MerchantRecipe merchantrecipe = new MerchantRecipe(itemstack, itemstack3, itemstack2);
         if(flag) {
            merchantrecipe.toolUses();
         }

         merchantrecipe.setToolUses(count);
         merchantrecipelist.add(merchantrecipe);
      }

      return merchantrecipelist;
   }

   public void readRecipiesFromTags(NBTTagCompound par1NBTTagCompound) {
      NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Recipes");

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
         this.add(new MerchantRecipe(nbttagcompound1));
      }

   }

   public NBTTagCompound getRecipiesAsTags() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      NBTTagList nbttaglist = new NBTTagList("Recipes");

      for(int i = 0; i < this.size(); ++i) {
         MerchantRecipe merchantrecipe = (MerchantRecipe)this.get(i);
         nbttaglist.appendTag(merchantrecipe.writeToTags());
      }

      nbttagcompound.setTag("Recipes", nbttaglist);
      return nbttagcompound;
   }
}

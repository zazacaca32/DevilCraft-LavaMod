package net.minecraft.client.addon.tco.tiny.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.addon.tco.tiny.Utils.LMerchantRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;

public class LMerchantRecipeList extends ArrayList {

   public LMerchantRecipeList() {}

   public LMerchantRecipeList(NBTTagCompound par1NBTTagCompound) {
      this.readRecipiesFromTags(par1NBTTagCompound);
   }

   public LMerchantRecipe canRecipeBeUsed(ItemStack par1ItemStack, ItemStack par2ItemStack, int par3) {
      if(par3 > 0 && par3 < this.size()) {
         LMerchantRecipe var6 = (LMerchantRecipe)this.get(par3);
         return par1ItemStack.itemID == var6.getItemToBuy().itemID && (par2ItemStack == null && !var6.hasSecondItemToBuy() || var6.hasSecondItemToBuy() && par2ItemStack != null && var6.getSecondItemToBuy().itemID == par2ItemStack.itemID) && par1ItemStack.stackSize >= var6.getItemToBuy().stackSize && (!var6.hasSecondItemToBuy() || par2ItemStack.stackSize >= var6.getSecondItemToBuy().stackSize)?var6:null;
      } else {
         for(int j = 0; j < this.size(); ++j) {
            LMerchantRecipe merchantrecipe2 = (LMerchantRecipe)this.get(j);
            if(par1ItemStack.itemID == merchantrecipe2.getItemToBuy().itemID && par1ItemStack.stackSize >= merchantrecipe2.getItemToBuy().stackSize && (!merchantrecipe2.hasSecondItemToBuy() && par2ItemStack == null || merchantrecipe2.hasSecondItemToBuy() && par2ItemStack != null && merchantrecipe2.getSecondItemToBuy().itemID == par2ItemStack.itemID && par2ItemStack.stackSize >= merchantrecipe2.getSecondItemToBuy().stackSize)) {
               return merchantrecipe2;
            }
         }

         return null;
      }
   }

   public void addToListWithCheck(LMerchantRecipe par1MerchantRecipe) {
      for(int i = 0; i < this.size(); ++i) {
         LMerchantRecipe merchantrecipe1 = (LMerchantRecipe)this.get(i);
         if(par1MerchantRecipe.hasSameIDsAs(merchantrecipe1)) {
            if(par1MerchantRecipe.hasSameItemsAs(merchantrecipe1)) {
               this.set(i, par1MerchantRecipe);
            }

            return;
         }
      }

      this.add(par1MerchantRecipe);
   }

   public void writeRecipiesToStream(DataOutputStream par1DataOutputStream) throws IOException {
      par1DataOutputStream.writeByte((byte)(this.size() & 255));

      for(int i = 0; i < this.size(); ++i) {
         LMerchantRecipe merchantrecipe = (LMerchantRecipe)this.get(i);
         Packet.writeItemStack(merchantrecipe.getItemToBuy(), par1DataOutputStream);
         Packet.writeItemStack(merchantrecipe.getItemToSell(), par1DataOutputStream);
         ItemStack itemstack = merchantrecipe.getSecondItemToBuy();
         par1DataOutputStream.writeBoolean(itemstack != null);
         if(itemstack != null) {
            Packet.writeItemStack(itemstack, par1DataOutputStream);
         }

         par1DataOutputStream.writeBoolean(merchantrecipe.func_82784_g());
      }

   }

   @SideOnly(Side.CLIENT)
   public static LMerchantRecipeList readRecipiesFromStream(DataInputStream par0DataInputStream) throws IOException {
      LMerchantRecipeList merchantrecipelist = new LMerchantRecipeList();
      int i = par0DataInputStream.readByte() & 255;

      for(int j = 0; j < i; ++j) {
         ItemStack itemstack = Packet.readItemStack(par0DataInputStream);
         ItemStack itemstack2 = Packet.readItemStack(par0DataInputStream);
         ItemStack itemstack3 = null;
         if(par0DataInputStream.readBoolean()) {
            itemstack3 = Packet.readItemStack(par0DataInputStream);
         }

         boolean flag = par0DataInputStream.readBoolean();
         LMerchantRecipe merchantrecipe = new LMerchantRecipe(itemstack, itemstack3, itemstack2);
         if(flag) {
            merchantrecipe.func_82785_h();
         }

         merchantrecipelist.add(merchantrecipe);
      }

      return merchantrecipelist;
   }

   public void readRecipiesFromTags(NBTTagCompound par1NBTTagCompound) {
      NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Recipes");

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
         this.add(new LMerchantRecipe(nbttagcompound1));
      }

   }

   public NBTTagCompound getRecipiesAsTags() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      NBTTagList nbttaglist = new NBTTagList("Recipes");

      for(int i = 0; i < this.size(); ++i) {
         LMerchantRecipe merchantrecipe = (LMerchantRecipe)this.get(i);
         nbttaglist.appendTag(merchantrecipe.writeToTags());
      }

      nbttagcompound.setTag("Recipes", nbttaglist);
      return nbttagcompound;
   }
}

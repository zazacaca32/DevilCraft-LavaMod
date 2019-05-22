package net.minecraft.client.addon.tco.tiny.entity.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipe;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipeList;
import net.minecraft.entity.player.EntityPlayer;

public interface IMerchant {

   void setCustomer(EntityPlayer var1);

   EntityPlayer getCustomer();

   MerchantRecipeList getRecipes(EntityPlayer var1);

   @SideOnly(Side.CLIENT)
   void setRecipes(MerchantRecipeList var1);

   void useRecipe(MerchantRecipe var1);
}

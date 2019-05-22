package net.minecraft.client.addon.tco.tiny.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.entity.inventory.IMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.InventoryMerchant;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipe;
import net.minecraft.client.addon.tco.tiny.entity.inventory.MerchantRecipeList;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.CLIENT)
public class NpcMerchant implements IMerchant {

   private InventoryMerchant theMerchantInventory;
   private EntityPlayer customer;
   private MerchantRecipeList recipeList;


   public NpcMerchant(EntityPlayer par1EntityPlayer) {
      this.customer = par1EntityPlayer;
      this.theMerchantInventory = new InventoryMerchant(par1EntityPlayer, this);
   }

   public EntityPlayer getCustomer() {
      return this.customer;
   }

   public void setCustomer(EntityPlayer par1EntityPlayer) {}

   public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer) {
      return this.recipeList;
   }

   public void setRecipes(MerchantRecipeList par1MerchantRecipeList) {
      this.recipeList = par1MerchantRecipeList;
   }

   public void useRecipe(MerchantRecipe par1MerchantRecipe) {}
}

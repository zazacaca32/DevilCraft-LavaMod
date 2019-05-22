package net.minecraft.client.addon.tco.tiny.Utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LMerchantRecipe {

   private ItemStack itemToBuy;
   private ItemStack secondItemToBuy;
   private ItemStack itemToSell;
   private int toolUses;
   private int maxTradeUses;


   public LMerchantRecipe(NBTTagCompound par1NBTTagCompound) {
      this.readFromTags(par1NBTTagCompound);
   }

   public LMerchantRecipe(ItemStack par1ItemStack, ItemStack par2ItemStack, ItemStack par3ItemStack) {
      this.itemToBuy = par1ItemStack;
      this.secondItemToBuy = par2ItemStack;
      this.itemToSell = par3ItemStack;
      this.maxTradeUses = 7;
   }

   public LMerchantRecipe(ItemStack par1ItemStack, ItemStack par2ItemStack) {
      this(par1ItemStack, (ItemStack)null, par2ItemStack);
   }

   public LMerchantRecipe(ItemStack par1ItemStack, Item par2Item) {
      this(par1ItemStack, new ItemStack(par2Item));
   }

   public ItemStack getItemToBuy() {
      return this.itemToBuy;
   }

   public ItemStack getSecondItemToBuy() {
      return this.secondItemToBuy;
   }

   public boolean hasSecondItemToBuy() {
      return this.secondItemToBuy != null;
   }

   public ItemStack getItemToSell() {
      return this.itemToSell;
   }

   public boolean hasSameIDsAs(LMerchantRecipe par1MerchantRecipe) {
      return this.itemToBuy.itemID == par1MerchantRecipe.itemToBuy.itemID && this.itemToSell.itemID == par1MerchantRecipe.itemToSell.itemID && (this.secondItemToBuy == null && par1MerchantRecipe.secondItemToBuy == null || this.secondItemToBuy != null && par1MerchantRecipe.secondItemToBuy != null && this.secondItemToBuy.itemID == par1MerchantRecipe.secondItemToBuy.itemID);
   }

   public boolean hasSameItemsAs(LMerchantRecipe par1MerchantRecipe) {
      return this.hasSameIDsAs(par1MerchantRecipe) && (this.itemToBuy.stackSize < par1MerchantRecipe.itemToBuy.stackSize || this.secondItemToBuy != null && this.secondItemToBuy.stackSize < par1MerchantRecipe.secondItemToBuy.stackSize);
   }

   public void incrementToolUses() {
      ++this.toolUses;
   }

   public void func_82783_a(int par1) {
      this.maxTradeUses += par1;
   }

   public boolean func_82784_g() {
      return this.toolUses >= this.maxTradeUses;
   }

   @SideOnly(Side.CLIENT)
   public void func_82785_h() {
      this.toolUses = this.maxTradeUses;
   }

   public void readFromTags(NBTTagCompound par1NBTTagCompound) {
      NBTTagCompound nbttagcompound1 = par1NBTTagCompound.getCompoundTag("buy");
      this.itemToBuy = ItemStack.loadItemStackFromNBT(nbttagcompound1);
      NBTTagCompound nbttagcompound2 = par1NBTTagCompound.getCompoundTag("sell");
      this.itemToSell = ItemStack.loadItemStackFromNBT(nbttagcompound2);
      if(par1NBTTagCompound.hasKey("buyB")) {
         this.secondItemToBuy = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("buyB"));
      }

      if(par1NBTTagCompound.hasKey("uses")) {
         this.toolUses = par1NBTTagCompound.getInteger("uses");
      }

      this.maxTradeUses = par1NBTTagCompound.hasKey("maxUses")?par1NBTTagCompound.getInteger("maxUses"):7;
   }

   public NBTTagCompound writeToTags() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      nbttagcompound.setCompoundTag("buy", this.itemToBuy.writeToNBT(new NBTTagCompound("buy")));
      nbttagcompound.setCompoundTag("sell", this.itemToSell.writeToNBT(new NBTTagCompound("sell")));
      if(this.secondItemToBuy != null) {
         nbttagcompound.setCompoundTag("buyB", this.secondItemToBuy.writeToNBT(new NBTTagCompound("buyB")));
      }

      nbttagcompound.setInteger("uses", this.toolUses);
      nbttagcompound.setInteger("maxUses", this.maxTradeUses);
      return nbttagcompound;
   }
}

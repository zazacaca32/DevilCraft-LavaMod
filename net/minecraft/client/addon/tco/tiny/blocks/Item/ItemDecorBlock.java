package net.minecraft.client.addon.tco.tiny.blocks.Item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDecorBlock extends ItemBlock {

   public ItemDecorBlock(int par1) {
      super(par1);
      this.setHasSubtypes(true);
   }

   public String getUnlocalizedName(ItemStack itemstack) {
      return this.getUnlocalizedName() + "." + itemstack.getItemDamage();
   }

   public int getMetadata(int par1) {
      return par1;
   }
}

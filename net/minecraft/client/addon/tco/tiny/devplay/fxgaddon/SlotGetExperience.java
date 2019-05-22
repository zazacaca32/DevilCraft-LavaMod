package net.minecraft.client.addon.tco.tiny.devplay.fxgaddon;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGetExperience extends Slot {

   public TileGetExperience tileEntity;


   public SlotGetExperience(TileGetExperience tileEntity, int par2, int par3, int par4) {
      super(tileEntity, par2, par3, par4);
      this.tileEntity = tileEntity;
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return this.tileEntity.isItemValid(par1ItemStack);
   }
}

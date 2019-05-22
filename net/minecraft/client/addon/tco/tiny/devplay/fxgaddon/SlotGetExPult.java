package net.minecraft.client.addon.tco.tiny.devplay.fxgaddon;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExPult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGetExPult extends Slot {

   public TileGetExPult tileEntity;
   private boolean isput = false;


   public SlotGetExPult(boolean isput, TileGetExPult tileEntity, int par2, int par3, int par4) {
      super(tileEntity, par2, par3, par4);
      this.tileEntity = tileEntity;
      this.isput = isput;
   }

   public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
      return this.isput;
   }

   public boolean isItemValid(ItemStack par1ItemStack) {
      return this.isput;
   }
}

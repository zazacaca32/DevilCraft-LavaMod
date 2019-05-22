package net.minecraft.client.addon.tco.tiny.blocks.Item;

import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemBlockTraderControllerMonitor extends ItemBlock {

   public ItemBlockTraderControllerMonitor(int par1) {
      super(par1);
   }

   public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
      if(par3World.isRemote) {
         return true;
      } else {
         TileEntity tile = par3World.getBlockTileEntity(par4, par5, par6);
         return tile != null && tile instanceof TileEntityBlockTraderController && super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
      }
   }
}

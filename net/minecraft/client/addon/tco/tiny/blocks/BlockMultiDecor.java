package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.blocks.BlockBaseSide;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDecor;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMultiDecor extends BlockBaseSide {

   public BlockMultiDecor(int id) {
      super(id);
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockDecor();
   }

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      float f = 0.375F;
      float f1 = 0.625F;
      float f2 = 0.375F;
      float f3 = 0.625F;
      switch(par1IBlockAccess.getBlockMetadata(par2, par3, par4)) {
      case 0:
         this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
         break;
      case 1:
         this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
         break;
      case 2:
         this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
         break;
      default:
         this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
      }

   }

   public int damageDropped(int par1) {
      return par1;
   }

   @SideOnly(Side.CLIENT)
   public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
      par3List.add(new ItemStack(par1, 1, 0));
      par3List.add(new ItemStack(par1, 1, 1));
      par3List.add(new ItemStack(par1, 1, 2));
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("Tile:present_0");
   }

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
      super.breakBlock(par1World, par2, par3, par4, par5, par6, Boolean.valueOf(false));
   }
}

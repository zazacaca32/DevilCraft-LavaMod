package net.minecraft.client.addon.tco.tiny.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDesk;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDesk extends BlockContainer {

   public BlockDesk(int id) {
      super(id, Material.ground);
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      TileEntity te = world.getBlockTileEntity(i, j, k);
      if(te != null && te instanceof BaseTileEntityBlock) {
         ((BaseTileEntityBlock)te).setFacing(facing);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      this.setBlockBounds(-0.0F, 0.0F, -0.0F, 1.0F, 0.5F, 1.0F);
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockDesk();
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("Tile:present_0");
   }
}

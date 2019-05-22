package net.minecraft.client.addon.tco.tiny.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPresentNewYear;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPresent extends BlockContainer {

   public BlockPresent(int id) {
      super(id, Material.ground);
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      boolean chestFacing = false;
      int facing = MathHelper.floor_double((double)entityliving.rotationYaw + 0.5D) & 359;
      TileEntity te = world.getBlockTileEntity(i, j, k);
      if(te != null && te instanceof TileEntityBlockPresentNewYear) {
         ((TileEntityBlockPresentNewYear)te).setFacing(facing);
         world.markBlockForUpdate(i, j, k);
      }

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

   public TileEntity createNewTileEntity(World world) {
      return new TileEntityBlockPresentNewYear();
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return null;
   }

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.6F, 0.1F, 0.6F);
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("Tile:present_0");
   }
}

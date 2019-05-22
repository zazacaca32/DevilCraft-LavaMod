package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.BlockBaseSide;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityPressSpear;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPressSpear extends BlockBaseSide {

   public BlockPressSpear(int id) {
      super(id);
   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityPressSpear var10 = (TileEntityPressSpear)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 601, par1World, par2, par3, par4);
         }

         return true;
      } else {
         par5EntityPlayer.addChatMessage("§cБлок пресса занят!");
         return false;
      }
   }

   public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
      this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
      return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
   }

   @SideOnly(Side.CLIENT)
   public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
      this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
      return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityPressSpear();
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

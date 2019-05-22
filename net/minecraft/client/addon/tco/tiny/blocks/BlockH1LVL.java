package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityH1LVL;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockH1LVL extends BlockContainer {

   private Icon[] icon = new Icon[3];


   public BlockH1LVL(int par1) {
      super(par1, Material.iron);
   }

   public void onBlockAdded(World par1World, int x, int y, int z) {
      super.onBlockAdded(par1World, x, y, z);
      if(!par1World.isRemote) {
         DarkNode.setUpdateController(par1World, x, y, z);
      }

   }

   public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
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

   public void breakBlock(World par1World, int x, int y, int z, int par5, int par6) {
      super.breakBlock(par1World, x, y, z, par5, par6);
      if(!par1World.isRemote) {
         DarkNode.setUpdateController(par1World, x, y, z);
      }

   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityH1LVL var10 = (TileEntityH1LVL)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 11001, par1World, par2, par3, par4);
         }

         return true;
      } else {
         return false;
      }
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityH1LVL();
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

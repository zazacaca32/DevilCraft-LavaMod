package net.minecraft.client.addon.tco.tiny.blocks;

import net.minecraft.client.addon.tco.tiny.blocks.BlockBaseSide;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockInfo extends BlockBaseSide {

   public BlockInfo(int id) {
      super(id);
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockInfo();
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

   public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
      int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      par1World.getBlockMetadata(par2, par3, par4);
      if(l == 0) {
         par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
      }

      if(l == 1) {
         par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
      }

      if(l == 2) {
         par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
      }

      if(l == 3) {
         par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
      }

   }
}

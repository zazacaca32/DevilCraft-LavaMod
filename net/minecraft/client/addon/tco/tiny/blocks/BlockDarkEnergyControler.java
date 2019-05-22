package net.minecraft.client.addon.tco.tiny.blocks;

import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.BlockBaseSide;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockDarkEnergyControler extends BlockBaseSide {

   public BlockDarkEnergyControler(int id) {
      super(id);
   }

   public void onBlockAdded(World par1World, int x, int y, int z) {
      super.onBlockAdded(par1World, x, y, z);
      if(!par1World.isRemote) {
         DarkNode.setUpdateController(par1World, x, y, z);
      }

   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityBlockDarkEnergyControler var10 = (TileEntityBlockDarkEnergyControler)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 10016, par1World, par2, par3, par4);
         }

         return true;
      } else {
         return false;
      }
   }

   public void breakBlock(World par1World, int x, int y, int z, int par5, int par6) {
      super.breakBlock(par1World, x, y, z, par5, par6, Boolean.valueOf(false));
      if(!par1World.isRemote) {
         DarkNode.clearControllerByDarkNodes(x, y, z);
         DarkNode.setUpdateController(par1World, x, y, z);
      }

   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockDarkEnergyControler();
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

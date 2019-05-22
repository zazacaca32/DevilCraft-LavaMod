package net.minecraft.client.addon.tco.tiny.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyHarvest;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockDarkEnergyHarvest extends BlockContainer {

   private Icon[] icon = new Icon[3];


   public BlockDarkEnergyHarvest(int par1) {
      super(par1, Material.cloth);
   }

   public TileEntity createNewTileEntity(World world) {
      return new TileEntityBlockDarkEnergyHarvest();
   }

   public void onBlockAdded(World par1World, int x, int y, int z) {
      super.onBlockAdded(par1World, x, y, z);
      if(!par1World.isRemote) {
         DarkNode.setUpdateController(par1World, x, y, z);
      }

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
         TileEntityBlockDarkEnergyHarvest var10 = (TileEntityBlockDarkEnergyHarvest)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 10017, par1World, par2, par3, par4);
         }

         return true;
      } else {
         return false;
      }
   }

   public void registerIcons(IconRegister register) {
      for(int i = 0; i < this.icon.length; ++i) {
         this.icon[i] = register.registerIcon("Tile:DarkHarvest" + i);
      }

   }

   public Icon getIcon(int side, int meta) {
      return side > 1?this.icon[2]:this.icon[side];
   }
}

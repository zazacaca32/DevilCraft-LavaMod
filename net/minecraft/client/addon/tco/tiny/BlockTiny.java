package net.minecraft.client.addon.tco.tiny;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.TileEntityTiny;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockTiny extends BlockContainer {

   public Icon[] icons = new Icon[6];


   protected BlockTiny(int id) {
      super(id, Material.cloth);
      this.setHardness(2.0F);
      this.setResistance(3.0F);
      this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
      TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
      if(tileEntity != null && !player.isSneaking()) {
         if(world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN)) {
            return true;
         } else if(world.isRemote) {
            return true;
         } else {
            player.openGui(Tiny.instance, 10000, world, x, y, z);
            return true;
         }
      } else {
         return false;
      }
   }

   public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
      super.breakBlock(world, x, y, z, par5, par6);
   }

   public TileEntity createNewTileEntity(World world) {
      return new TileEntityTiny();
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("Tile:shop_0");
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      byte chestFacing = 0;
      int facing = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      if(facing == 0) {
         chestFacing = 2;
      }

      if(facing == 1) {
         chestFacing = 5;
      }

      if(facing == 2) {
         chestFacing = 3;
      }

      if(facing == 3) {
         chestFacing = 4;
      }

      TileEntity te;
      if((te = world.getBlockTileEntity(i, j, k)) != null && te instanceof TileEntityTiny) {
         ((TileEntityTiny)te).setFacing(chestFacing);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public void onBlockAdded(World world, int i, int j, int k) {
      super.onBlockAdded(world, i, j, k);
      world.markBlockForUpdate(i, j, k);
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public int getRenderType() {
      return 22;
   }
}

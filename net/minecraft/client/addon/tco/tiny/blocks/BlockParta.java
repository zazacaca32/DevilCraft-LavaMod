package net.minecraft.client.addon.tco.tiny.blocks;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockParta;
import net.minecraft.client.addon.tco.tiny.entities.EntityParta;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockParta extends BlockContainer {

   public BlockParta(int id) {
      super(id, Material.ground);
   }

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      this.setBlockBounds(-0.0F, 0.0F, -0.0F, 1.0F, 0.5F, 1.0F);
   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else {
         if(!par5EntityPlayer.isSneaking() && (TileEntityBlockParta)par1World.getBlockTileEntity(par2, par3, par4) != null) {
            List list = par1World.getEntitiesWithinAABB(EntityParta.class, AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1)).expand(1.0D, 1.0D, 1.0D));

            EntityParta var11;
            for(Iterator var12 = list.iterator(); var12.hasNext(); var11 = (EntityParta)var12.next()) {
               ;
            }
         }

         return false;
      }
   }

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
      List list = par1World.getEntitiesWithinAABB(EntityParta.class, AxisAlignedBB.getAABBPool().getAABB((double)par2, (double)par3, (double)par4, (double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1)).expand(1.0D, 1.0D, 1.0D));
      Iterator var8 = list.iterator();

      while(var8.hasNext()) {
         EntityParta entityParta = (EntityParta)var8.next();
         if(entityParta != null) {
            par1World.removeEntity(entityParta);
         }
      }

      super.breakBlock(par1World, par2, par3, par4, par5, par6);
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      TileEntity te = world.getBlockTileEntity(i, j, k);
      if(te != null && te instanceof BaseTileEntityBlock) {
         EntityParta parta = new EntityParta(world, (double)i, (double)j, (double)k, facing);
         if(!world.isRemote && world.spawnEntityInWorld(parta)) {
            ;
         }

         ((BaseTileEntityBlock)te).setFacing(facing);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockParta();
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

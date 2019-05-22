package net.minecraft.client.addon.tco.tiny.blocks;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTurning;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTurning extends BlockContainer {

   private final Random furnaceRand = new Random();


   public BlockTurning(int id) {
      super(id, Material.ground);
   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityBlockTurning var10 = (TileEntityBlockTurning)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 10010, par1World, par2, par3, par4);
         }

         return true;
      } else {
         return false;
      }
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      TileEntity te = world.getBlockTileEntity(i, j, k);
      if(te != null && te instanceof BaseTileEntityBlock) {
         ((BaseTileEntityBlock)te).setFacing(facing);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockTurning();
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

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
      TileEntityBlockTurning tileentityfurnace = (TileEntityBlockTurning)par1World.getBlockTileEntity(par2, par3, par4);
      if(tileentityfurnace != null) {
         for(int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1) {
            ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);
            if(itemstack != null) {
               float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
               float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
               float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

               while(itemstack.stackSize > 0) {
                  int k1 = this.furnaceRand.nextInt(21) + 10;
                  if(k1 > itemstack.stackSize) {
                     k1 = itemstack.stackSize;
                  }

                  itemstack.stackSize -= k1;
                  EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
                  if(itemstack.hasTagCompound()) {
                     entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                  }

                  float f3 = 0.05F;
                  entityitem.motionX = (double)((float)this.furnaceRand.nextGaussian() * f3);
                  entityitem.motionY = (double)((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
                  entityitem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * f3);
                  par1World.spawnEntityInWorld(entityitem);
               }
            }
         }

         par1World.func_96440_m(par2, par3, par4, par5);
      }

      super.breakBlock(par1World, par2, par3, par4, par5, par6);
   }
}

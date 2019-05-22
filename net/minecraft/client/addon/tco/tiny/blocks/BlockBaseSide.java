package net.minecraft.client.addon.tco.tiny.blocks;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockBaseSide extends BlockContainer {

   private final Random furnaceRand = new Random();


   public BlockBaseSide(int id) {
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

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6, Boolean Container2) {
      if(Container2.booleanValue()) {
         this.breakBlock(par1World, par2, par3, par4, par5, par6);
      } else {
         super.breakBlock(par1World, par2, par3, par4, par5, par6);
      }

   }

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
      TileEntityBlockBase tileentityBase = (TileEntityBlockBase)par1World.getBlockTileEntity(par2, par3, par4);
      if(tileentityBase != null) {
         for(int j1 = 0; j1 < tileentityBase.getSizeInventory(); ++j1) {
            ItemStack itemstack = tileentityBase.getStackInSlot(j1);
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

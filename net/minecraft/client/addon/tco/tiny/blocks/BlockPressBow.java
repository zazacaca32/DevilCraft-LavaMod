package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressBow;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPressBow extends BlockContainer {

   @SideOnly(Side.CLIENT)
   private Icon icon;


   public BlockPressBow(int par1) {
      super(par1, Material.clay);
      this.setResistance(42.0F);
      this.setStepSound(new StepSound("anvil", 1.0F, 1.0F));
      this.setLightValue(1.0F);
      this.setUnlocalizedName("BlockPressBows");
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("anvil_base");
   }

   @SideOnly(Side.CLIENT)
   public int getRenderType() {
      return -1;
   }

   @SideOnly(Side.CLIENT)
   public int getRenderBlockPass() {
      return 1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
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

   public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
      int metadata = world.getBlockMetadata(x, y, z);
      switch(metadata) {
      case 0:
         this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
         break;
      case 1:
         this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
         break;
      case 2:
         this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
         break;
      case 3:
         this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
      }

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

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!entityplayer.isSneaking()) {
         TileEntityBlockPressBow var10 = (TileEntityBlockPressBow)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            if(!var10.isuse) {
               var10.usePlayer = entityplayer;
               var10.isuse = true;
               entityplayer.openGui(Tiny.instance, 601, par1World, par2, par3, par4);
            } else {
               entityplayer.addChatMessage("§cБлок пресса занят!");
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public TileEntity createNewTileEntity(World world) {
      return new TileEntityBlockPressBow();
   }

   public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
      TileEntity tile = world.getBlockTileEntity(x, y, z);
      if(tile instanceof TileEntityBlockPressBow) {
         for(int i = 0; i < ((TileEntityBlockPressBow)tile).inventoryContents.length; ++i) {
            if(((TileEntityBlockPressBow)tile).inventoryContents[i] != null) {
               return false;
            }
         }
      }

      return super.removeBlockByPlayer(world, player, x, y, z);
   }
}

package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExPult;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGetExPult extends BlockContainer {

   @SideOnly(Side.CLIENT)
   private Icon icon;


   public BlockGetExPult(int id) {
      super(id, Material.iron);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setUnlocalizedName("GetExPultBlock");
      this.setHardness(2.5F);
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = Block.blockIron.getIcon(1, 0);
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileGetExPult();
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

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z) {
      TileGetExPult tile = (TileGetExPult)par1IBlockAccess.getBlockTileEntity(x, y, z);
      if(tile != null) {
         int par1 = tile.rotation;
         if(par1 == 0) {
            this.setBlockBounds(0.2F, 0.0F, 0.6F, 0.8F, 1.2F, 1.0F);
         }

         if(par1 == 1) {
            this.setBlockBounds(0.0F, 0.0F, 0.2F, 0.4F, 1.2F, 0.8F);
         }

         if(par1 == 2) {
            this.setBlockBounds(0.2F, 0.0F, 0.0F, 0.8F, 1.2F, 0.4F);
         }

         if(par1 == 3) {
            this.setBlockBounds(0.6F, 0.0F, 0.2F, 1.0F, 1.2F, 0.8F);
         }
      }

   }

   public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
      int x = par2 - 3;

      for(int i = 0; i < 5; ++i) {
         ++x;
         int z = par4 - 3;

         for(int p = 0; p < 5; ++p) {
            ++z;
            if(par1World.getBlockId(x, par3, z) == super.blockID) {
               return false;
            }
         }
      }

      return super.canPlaceBlockAt(par1World, par2, par3, par4);
   }

   public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
      TileEntity tile = world.getBlockTileEntity(x, y, z);
      if(tile instanceof TileGetExPult) {
         boolean flag = true;

         for(int i = 0; i < 11; ++i) {
            if(((TileGetExPult)tile).inventoryContents[i] != null) {
               flag = false;
            }
         }

         if(!flag) {
            player.sendChatToPlayer("Заберите все итемы чтобы разрушить");
            return false;
         }
      }

      return super.removeBlockByPlayer(world, player, x, y, z);
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player, ItemStack stack) {
      TileGetExPult tile = (TileGetExPult)world.getBlockTileEntity(x, y, z);
      if(tile != null) {
         tile.Owner = player.getEntityName();
         TileEntity tileXe = world.getBlockTileEntity(x + 1, y, z);
         if(tileXe instanceof TileGetExperience) {
            tile.rotation = 3;
            return;
         }

         tileXe = world.getBlockTileEntity(x - 1, y, z);
         if(tileXe instanceof TileGetExperience) {
            tile.rotation = 1;
            return;
         }

         tileXe = world.getBlockTileEntity(x, y, z + 1);
         if(tileXe instanceof TileGetExperience) {
            tile.rotation = 0;
            return;
         }

         tileXe = world.getBlockTileEntity(x, y, z - 1);
         if(tileXe instanceof TileGetExperience) {
            tile.rotation = 2;
            return;
         }
      }

   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
      if(world.isRemote) {
         return true;
      } else if(!player.isSneaking()) {
         TileGetExPult tile = (TileGetExPult)world.getBlockTileEntity(x, y, z);
         if(tile != null) {
            player.openGui(Tiny.instance, 10100, world, x, y, z);
         }

         return true;
      } else {
         return false;
      }
   }
}

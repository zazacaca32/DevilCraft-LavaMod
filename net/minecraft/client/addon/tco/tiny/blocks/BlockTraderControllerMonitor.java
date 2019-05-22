package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderControllerMonitor;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockTraderControllerMonitor extends BlockContainer {

   private ForgeDirection side;
   public Icon[] icon = new Icon[9];


   public BlockTraderControllerMonitor(int par1) {
      super(par1, Material.cloth);
      this.setLightValue(0.5F);
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      boolean flag = false;
      TileEntity te = world.getBlockTileEntity(i - 1, j, k);
      if(te != null && te instanceof TileEntityBlockTraderController) {
         flag = true;
      }

      if(!flag) {
         te = world.getBlockTileEntity(i + 1, j, k);
         if(te != null && te instanceof TileEntityBlockTraderController) {
            flag = true;
         }
      }

      if(!flag) {
         te = world.getBlockTileEntity(i, j, k - 1);
         if(te != null && te instanceof TileEntityBlockTraderController) {
            flag = true;
         }
      }

      if(!flag) {
         te = world.getBlockTileEntity(i, j, k + 1);
         if(te != null && te instanceof TileEntityBlockTraderController) {
            flag = true;
         }
      }

      if(flag && te != null) {
         TileEntity tem = world.getBlockTileEntity(i, j, k);
         if(tem != null && tem instanceof TileEntityBlockTraderControllerMonitor) {
            byte facing = 0;
            if(te.zCoord - k == 1) {
               facing = 2;
            } else if(te.xCoord - i == 1) {
               facing = 4;
            } else if(k - te.zCoord == 1) {
               facing = 3;
            } else if(i - te.xCoord == 1) {
               facing = 5;
            }

            ((TileEntityBlockTraderControllerMonitor)tem).placedByGetController(te.xCoord, te.yCoord, te.zCoord);
            ((TileEntityBlockTraderControllerMonitor)tem).setFcing(facing);
            world.markBlockForUpdate(i, j, k);
         }
      }

   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityBlockTraderControllerMonitor var10 = (TileEntityBlockTraderControllerMonitor)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 10024, par1World, par2, par3, par4);
         }

         return true;
      } else {
         return false;
      }
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

   public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      this.updateLadderBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("Tile:tm1");
      this.icon[0] = par1IconRegister.registerIcon("Tile:tm0");
      this.icon[1] = par1IconRegister.registerIcon("Tile:tm1");
      this.icon[2] = par1IconRegister.registerIcon("Tile:tm2");
      this.icon[3] = par1IconRegister.registerIcon("Tile:tm3");
      this.icon[4] = par1IconRegister.registerIcon("Tile:tm4");
      this.icon[5] = par1IconRegister.registerIcon("Tile:tm5");
   }

   @SideOnly(Side.CLIENT)
   public Icon getIcon(int par1, int par2) {
      return this.icon[1];
   }

   public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
      TileEntity tile = world.getBlockTileEntity(x, y, z);
      boolean facing = false;
      if(tile != null && tile instanceof TileEntityBlockTraderControllerMonitor) {
         int facing1 = ((TileEntityBlockTraderControllerMonitor)tile).getFacing();
         byte result = 0;
         switch(facing1) {
         case 0:
            result = 1;
         case 1:
         default:
            break;
         case 2:
            if(side == 0) {
               result = 0;
            }

            if(side == 1) {
               result = 0;
            }

            if(side == 2) {
               result = 1;
            }

            if(side == 3) {
               result = 2;
            }

            if(side == 4) {
               result = 3;
            }

            if(side == 5) {
               result = 3;
            }
            break;
         case 3:
            if(side == 0) {
               result = 4;
            }

            if(side == 1) {
               result = 4;
            }

            if(side == 2) {
               result = 2;
            }

            if(side == 3) {
               result = 1;
            }

            if(side == 4) {
               result = 5;
            }

            if(side == 5) {
               result = 5;
            }
            break;
         case 4:
            if(side == 0) {
               result = 3;
            }

            if(side == 1) {
               result = 3;
            }

            if(side == 2) {
               result = 3;
            }

            if(side == 3) {
               result = 3;
            }

            if(side == 4) {
               result = 1;
            }

            if(side == 5) {
               result = 2;
            }
            break;
         case 5:
            if(side == 0) {
               result = 5;
            }

            if(side == 1) {
               result = 5;
            }

            if(side == 2) {
               result = 5;
            }

            if(side == 3) {
               result = 5;
            }

            if(side == 4) {
               result = 0;
            }

            if(side == 5) {
               result = 1;
            }
         }

         return this.icon[result];
      } else {
         return this.icon[1];
      }
   }

   public void updateLadderBounds(int par1) {
      float f = 0.22F;
      if(par1 == 2) {
         this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
      }

      if(par1 == 3) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
      }

      if(par1 == 4) {
         this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

      if(par1 == 5) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
      }

   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public int getRenderType() {
      return Tiny.proxy.getRenderId("blocksidecontrollermonitor");
   }

   private boolean isTradeController(World world, int x, int y, int z) {
      TileEntity tile = world.getBlockTileEntity(x, y, z);
      return tile instanceof TileEntityBlockTraderController;
   }

   private boolean isTradeControllerMonitor(World world, int x, int y, int z) {
      TileEntity tile = world.getBlockTileEntity(x, y, z);
      return tile instanceof TileEntityBlockTraderControllerMonitor;
   }

   public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
      int var10001 = par2 - 1;
      ForgeDirection var10004 = this.side;
      int var10003;
      if(!par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.EAST)) {
         var10001 = par2 + 1;
         var10004 = this.side;
         if(!par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.WEST)) {
            var10003 = par4 - 1;
            var10004 = this.side;
            if(!par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.SOUTH)) {
               var10003 = par4 + 1;
               var10004 = this.side;
               if(!par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.NORTH)) {
                  return false;
               }
            }
         }
      }

      var10003 = par2;
      int z = par4;
      if(this.isTradeController(par1World, par2 - 1, par3, par4)) {
         var10003 = par2 - 1;
      } else if(this.isTradeController(par1World, par2 + 1, par3, par4)) {
         var10003 = par2 + 1;
      } else if(this.isTradeController(par1World, par2, par3, par4 - 1)) {
         z = par4 - 1;
      } else if(this.isTradeController(par1World, par2, par3, par4 + 1)) {
         z = par4 + 1;
      }

      return var10003 == par2 && par3 == par3 && z == par4?false:!this.isTradeControllerMonitor(par1World, var10003 - 1, par3, z) && !this.isTradeControllerMonitor(par1World, var10003 + 1, par3, z) && !this.isTradeControllerMonitor(par1World, var10003, par3, z - 1) && !this.isTradeControllerMonitor(par1World, var10003, par3, z + 1);
   }

   public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
      TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
      int facing = 0;
      if(tile != null && tile instanceof TileEntityBlockTraderControllerMonitor) {
         facing = ((TileEntityBlockTraderControllerMonitor)tile).getFacing();
      }

      int j1 = facing;
      int var10003;
      ForgeDirection var10004;
      if(facing == 0 || par5 == 2) {
         var10003 = par4 + 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.NORTH)) {
            j1 = 2;
         }
      }

      if(j1 == 0 || par5 == 3) {
         var10003 = par4 - 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.SOUTH)) {
            j1 = 3;
         }
      }

      int var10001;
      if(j1 == 0 || par5 == 4) {
         var10001 = par2 + 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.WEST)) {
            j1 = 4;
         }
      }

      if(j1 == 0 || par5 == 5) {
         var10001 = par2 - 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.EAST)) {
            j1 = 5;
         }
      }

      return j1;
   }

   public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
      par1World.getBlockMetadata(par2, par3, par4);
      boolean flag = false;
      TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
      int facing = 0;
      if(tile != null && tile instanceof TileEntityBlockTraderControllerMonitor) {
         facing = ((TileEntityBlockTraderControllerMonitor)tile).getFacing();
      }

      int var10003;
      ForgeDirection var10004;
      if(facing == 2) {
         var10003 = par4 + 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.NORTH)) {
            flag = true;
         }
      }

      if(facing == 3) {
         var10003 = par4 - 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.SOUTH)) {
            flag = true;
         }
      }

      int var10001;
      if(facing == 4) {
         var10001 = par2 + 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.WEST)) {
            flag = true;
         }
      }

      if(facing == 5) {
         var10001 = par2 - 1;
         var10004 = this.side;
         if(par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.EAST)) {
            flag = true;
         }
      }

      if(!flag) {
         this.dropBlockAsItem(par1World, par2, par3, par4, facing, 0);
         par1World.setBlockToAir(par2, par3, par4);
      }

      super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
   }

   public int quantityDropped(Random par1Random) {
      return 1;
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockTraderControllerMonitor();
   }
}

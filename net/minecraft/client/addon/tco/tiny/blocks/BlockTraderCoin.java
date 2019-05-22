package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderCoin;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockTraderCoin extends BlockTrader {

   public BlockTraderCoin(int id) {
      super(id);
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      TileEntity te = world.getBlockTileEntity(i, j, k);
      if(te != null && te instanceof BaseTileEntityBlock) {
         ((BaseTileEntityBlock)te).placedBy(entityliving);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
      TileEntity te = par1World.getBlockTileEntity(par2, par3, par4);
      if(te != null && te instanceof TileEntityBlockTraderCoin) {
         TileEntityBlockTraderCoin t = (TileEntityBlockTraderCoin)te;
         TileEntity contr = par1World.getBlockTileEntity(t.contrX, t.contrY, t.contrZ);
         if(contr != null && contr instanceof TileEntityBlockTraderController) {
            ((TileEntityBlockTraderController)contr).mng.makeRemove(par2, par3, par4);
         }
      }

      super.breakBlock(par1World, par2, par3, par4, par5, par6);
   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityBlockTraderCoin var10 = (TileEntityBlockTraderCoin)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            if(par5EntityPlayer.getEntityName().equals(var10.Owner)) {
               par5EntityPlayer.openGui(Tiny.instance, 10025, par1World, par2, par3, par4);
            } else {
               par5EntityPlayer.openGui(Tiny.instance, 10026, par1World, par2, par3, par4);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockTraderCoin();
   }

   @SideOnly(Side.CLIENT)
   public int getRenderType() {
      return Tiny.proxy.getRenderId("blockside");
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      super.icon = new Icon[9];
      super.blockIcon = par1IconRegister.registerIcon("Tile:coin5");
      super.icon[0] = par1IconRegister.registerIcon("Tile:coin6");
      super.icon[1] = par1IconRegister.registerIcon("Tile:coin5");
      super.icon[2] = par1IconRegister.registerIcon("Tile:coin0");
      super.icon[3] = par1IconRegister.registerIcon("Tile:coin4");
      super.icon[4] = par1IconRegister.registerIcon("Tile:coin1");
      super.icon[5] = par1IconRegister.registerIcon("Tile:coin3");
      super.icon[6] = par1IconRegister.registerIcon("Tile:10");
      super.icon[7] = par1IconRegister.registerIcon("Tile:02");
      super.icon[8] = par1IconRegister.registerIcon("Tile:03");
   }

   @SideOnly(Side.CLIENT)
   public Icon getBlockTexture(IBlockAccess ba, int x, int y, int z, int side) {
      BaseTileEntityBlock b = (BaseTileEntityBlock)ba.getBlockTileEntity(x, y, z);
      boolean i = false;
      byte i1;
      switch(BlockTraderCoin.NamelessClass734562206.$SwitchMap$net$minecraftforge$common$ForgeDirection[b.getOrientation().ordinal()]) {
      case 1:
         return super.icon[side];
      case 2:
         int i2;
         if(side == 2) {
            i2 = 3;
         } else if(side == 3) {
            i2 = 2;
         } else if(side == 4) {
            i2 = 5;
         } else if(side == 5) {
            i2 = 4;
         } else {
            i2 = side;
         }

         return super.icon[i2];
      case 3:
         if(side == 5) {
            i1 = 3;
         } else if(side == 3) {
            i1 = 5;
         } else if(side == 0) {
            i1 = 0;
         } else if(side == 1) {
            i1 = 1;
         } else if(side == 4) {
            i1 = 2;
         } else if(side == 2) {
            i1 = 4;
         } else {
            i1 = 2;
         }

         return super.icon[i1];
      case 4:
         if(side == 3) {
            i1 = 5;
         } else if(side == 5) {
            i1 = 2;
         } else if(side == 0) {
            i1 = 0;
         } else if(side == 1) {
            i1 = 1;
         } else if(side == 4) {
            i1 = 3;
         } else if(side == 2) {
            i1 = 4;
         } else {
            i1 = 2;
         }

         return super.icon[i1];
      default:
         return super.icon[side];
      }
   }

   public boolean hasSpecialRenderer(IBlockAccess world, int x, int y, int z) {
      return false;
   }

   public boolean specialRenderer(IBlockAccess world, int x, int y, int z, RenderBlocks renderer) {
      return false;
   }

   static class NamelessClass734562206 {

      static final int[] $SwitchMap$net$minecraftforge$common$ForgeDirection = new int[ForgeDirection.values().length];


      static {
         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.NORTH.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.SOUTH.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.WEST.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.EAST.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}

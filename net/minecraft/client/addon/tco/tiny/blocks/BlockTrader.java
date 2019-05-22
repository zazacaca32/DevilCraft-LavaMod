package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTrader$NamelessClass1582840564;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
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

public class BlockTrader extends BlockContainer {

   @SideOnly(Side.CLIENT)
   public Icon[] icon;


   public BlockTrader(int id) {
      super(id, Material.ground);
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
      if(te != null && te instanceof TileEntityBlockTrader) {
         TileEntityBlockTrader t = (TileEntityBlockTrader)te;
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
         TileEntityBlockTrader var10 = (TileEntityBlockTrader)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            if(par5EntityPlayer.getEntityName().equals(var10.Owner)) {
               par5EntityPlayer.openGui(Tiny.instance, 10020, par1World, par2, par3, par4);
            } else {
               par5EntityPlayer.openGui(Tiny.instance, 10021, par1World, par2, par3, par4);
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
      return new TileEntityBlockTrader();
   }

   @SideOnly(Side.CLIENT)
   public int getRenderType() {
      return Tiny.proxy.getRenderId("blockside");
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      this.icon = new Icon[9];
      super.blockIcon = par1IconRegister.registerIcon("Tile:5");
      this.icon[0] = par1IconRegister.registerIcon("Tile:6");
      this.icon[1] = par1IconRegister.registerIcon("Tile:5");
      this.icon[2] = par1IconRegister.registerIcon("Tile:0");
      this.icon[3] = par1IconRegister.registerIcon("Tile:4");
      this.icon[4] = par1IconRegister.registerIcon("Tile:1");
      this.icon[5] = par1IconRegister.registerIcon("Tile:3");
      this.icon[6] = par1IconRegister.registerIcon("Tile:10");
      this.icon[7] = par1IconRegister.registerIcon("Tile:02");
      this.icon[8] = par1IconRegister.registerIcon("Tile:03");
   }

   @SideOnly(Side.CLIENT)
   public Icon getBlockTexture(IBlockAccess ba, int x, int y, int z, int side) {
      BaseTileEntityBlock b = (BaseTileEntityBlock)ba.getBlockTileEntity(x, y, z);
      boolean i = false;
      byte i1;
      switch(BlockTrader$NamelessClass1582840564.$SwitchMap$net$minecraftforge$common$ForgeDirection[b.getOrientation().ordinal()]) {
      case 1:
         return this.icon[side];
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

         return this.icon[i2];
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

         return this.icon[i1];
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

         return this.icon[i1];
      default:
         return this.icon[side];
      }
   }

   public boolean hasSpecialRenderer(IBlockAccess world, int x, int y, int z) {
      return false;
   }

   public boolean specialRenderer(IBlockAccess world, int x, int y, int z, RenderBlocks renderer) {
      return false;
   }

   static class NamelessClass1942894117 {

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

   static class NamelessClass997116499 {

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

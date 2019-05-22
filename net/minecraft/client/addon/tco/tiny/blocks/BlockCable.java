package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.AabbUtil;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.Direction;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTextureStitched;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityCable;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer {

   private static final Direction[] directions = Direction.values();
   @SideOnly(Side.CLIENT)
   private Icon[] coloredTextures;
   @SideOnly(Side.CLIENT)
   private Icon icon;


   public BlockCable(int par1, Material par2Material) {
      super(par1, par2Material);
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

   public TileEntity createNewTileEntity(World world) {
      return new TileEntityCable();
   }

   public String getTextureFolder() {
      return "wiring/cable";
   }

   public String getTextureName(int index) {
      String ret = this.getUnlocalizedName();
      return ret == null?null:ret.replace("item", "block");
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister iconRegister) {
      super.registerIcons(iconRegister);
      this.coloredTextures = new Icon[6];

      for(int side = 0; side < 6; ++side) {
         String name = "Tile:" + this.getTextureFolder() + "/" + this.getTextureName(0) + "." + side;
         BlockTextureStitched texture = new BlockTextureStitched(name);
         this.coloredTextures[side] = texture;
         ((TextureMap)iconRegister).setTextureEntry(name, texture);
      }

      this.icon = iconRegister.registerIcon("Tile:DarkCable");
   }

   @SideOnly(Side.CLIENT)
   public Icon getBlockTexture(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
      TileEntity te = iBlockAccess.getBlockTileEntity(x, y, z);
      return te instanceof TileEntityCable?this.coloredTextures[side]:null;
   }

   public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 origin, Vec3 absDirection) {
      TileEntity te = world.getBlockTileEntity(x, y, z);
      if(!(te instanceof TileEntityCable)) {
         return null;
      } else {
         TileEntityCable tileEntityCable = (TileEntityCable)te;
         Vec3 direction = Vec3.createVectorHelper(absDirection.xCoord - origin.xCoord, absDirection.yCoord - origin.yCoord, absDirection.zCoord - origin.zCoord);
         double maxLength = direction.lengthVector();
         double halfThickness = (double)tileEntityCable.getCableThickness() / 2.0D;
         boolean hit = false;
         Vec3 intersection = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
         Direction intersectingDirection = AabbUtil.getIntersection(origin, direction, AxisAlignedBB.getAABBPool().getAABB((double)x + 0.5D - halfThickness, (double)y + 0.5D - halfThickness, (double)z + 0.5D - halfThickness, (double)x + 0.5D + halfThickness, (double)y + 0.5D + halfThickness, (double)z + 0.5D + halfThickness), intersection);
         if(intersectingDirection != null && intersection.distanceTo(origin) <= maxLength) {
            hit = true;
         } else if(halfThickness < 0.5D) {
            Direction[] var17 = directions;
            int var18 = var17.length;

            for(int var19 = 0; var19 < var18; ++var19) {
               Direction dir = var17[var19];
               TileEntity target = dir.applyToTileEntity(tileEntityCable);
               if(tileEntityCable.canInteractWith(target)) {
                  AxisAlignedBB bbox = null;
                  switch(BlockCable.NamelessClass1954828170.$SwitchMap$tco$tiny$Utils$Direction[dir.ordinal()]) {
                  case 1:
                     bbox = AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y + 0.5D - halfThickness, (double)z + 0.5D - halfThickness, (double)x + 0.5D, (double)y + 0.5D + halfThickness, (double)z + 0.5D + halfThickness);
                     break;
                  case 2:
                     bbox = AxisAlignedBB.getAABBPool().getAABB((double)x + 0.5D, (double)y + 0.5D - halfThickness, (double)z + 0.5D - halfThickness, (double)x + 1.0D, (double)y + 0.5D + halfThickness, (double)z + 0.5D + halfThickness);
                     break;
                  case 3:
                     bbox = AxisAlignedBB.getAABBPool().getAABB((double)x + 0.5D - halfThickness, (double)y, (double)z + 0.5D - halfThickness, (double)x + 0.5D + halfThickness, (double)y + 0.5D, (double)z + 0.5D + halfThickness);
                     break;
                  case 4:
                     bbox = AxisAlignedBB.getAABBPool().getAABB((double)x + 0.5D - halfThickness, (double)y + 0.5D, (double)z + 0.5D - halfThickness, (double)x + 0.5D + halfThickness, (double)y + 1.0D, (double)z + 0.5D + halfThickness);
                     break;
                  case 5:
                     bbox = AxisAlignedBB.getAABBPool().getAABB((double)x + 0.5D - halfThickness, (double)y + 0.5D - halfThickness, (double)z, (double)x + 0.5D + halfThickness, (double)y + 0.5D, (double)z + 0.5D);
                     break;
                  case 6:
                     bbox = AxisAlignedBB.getAABBPool().getAABB((double)x + 0.5D - halfThickness, (double)y + 0.5D - halfThickness, (double)z + 0.5D, (double)x + 0.5D + halfThickness, (double)y + 0.5D + halfThickness, (double)z + 1.0D);
                  }

                  intersectingDirection = AabbUtil.getIntersection(origin, direction, bbox, intersection);
                  if(intersectingDirection != null && intersection.distanceTo(origin) <= maxLength) {
                     hit = true;
                     break;
                  }
               }
            }
         }

         return hit?new MovingObjectPosition(x, y, z, intersectingDirection.toSideValue(), intersection):null;
      }
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z, int meta) {
      double halfThickness = (double)TileEntityCable.getCableThickness(meta);
      return AxisAlignedBB.getAABBPool().getAABB((double)x + 0.5D - halfThickness, (double)y + 0.5D - halfThickness, (double)z + 0.5D - halfThickness, (double)x + 0.5D + halfThickness, (double)y + 0.5D + halfThickness, (double)z + 0.5D + halfThickness);
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
      return this.getCommonBoundingBoxFromPool(world, x, y, z, false);
   }

   public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
      return this.getCommonBoundingBoxFromPool(world, x, y, z, true);
   }

   public AxisAlignedBB getCommonBoundingBoxFromPool(World world, int x, int y, int z, boolean selectionBoundingBox) {
      TileEntity te = world.getBlockTileEntity(x, y, z);
      if(!(te instanceof TileEntityCable)) {
         return this.getCollisionBoundingBoxFromPool(world, x, y, z, 3);
      } else {
         TileEntityCable cable = (TileEntityCable)te;
         double halfThickness = (double)cable.getCableThickness() / 2.0D;
         double minX = (double)x + 0.5D - halfThickness;
         double minY = (double)y + 0.5D - halfThickness;
         double minZ = (double)z + 0.5D - halfThickness;
         double maxX = (double)x + 0.5D + halfThickness;
         double maxY = (double)y + 0.5D + halfThickness;
         double maxZ = (double)z + 0.5D + halfThickness;
         if(cable.canInteractWith(world.getBlockTileEntity(x - 1, y, z))) {
            minX = (double)x;
         }

         if(cable.canInteractWith(world.getBlockTileEntity(x, y - 1, z))) {
            minY = (double)y;
         }

         if(cable.canInteractWith(world.getBlockTileEntity(x, y, z - 1))) {
            minZ = (double)z;
         }

         if(cable.canInteractWith(world.getBlockTileEntity(x + 1, y, z))) {
            maxX = (double)(x + 1);
         }

         if(cable.canInteractWith(world.getBlockTileEntity(x, y + 1, z))) {
            maxY = (double)(y + 1);
         }

         if(cable.canInteractWith(world.getBlockTileEntity(x, y, z + 1))) {
            maxZ = (double)(z + 1);
         }

         return AxisAlignedBB.getAABBPool().getAABB(minX, minY, minZ, maxX, maxY, maxZ);
      }
   }

   public boolean isBlockNormalCube(World world, int x, int y, int z) {
      return false;
   }

   public Icon getIcon(int side, int meta) {
      return this.icon;
   }

   public int getRenderType() {
      return Tiny.proxy.getRenderId("cable");
   }

   public boolean isOpaqueCube() {
      return false;
   }


   static class NamelessClass1954828170 {

      static final int[] $SwitchMap$tco$tiny$Utils$Direction = new int[Direction.values().length];


      static {
         try {
            $SwitchMap$tco$tiny$Utils$Direction[Direction.XN.ordinal()] = 1;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$tco$tiny$Utils$Direction[Direction.XP.ordinal()] = 2;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$tco$tiny$Utils$Direction[Direction.YN.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$tco$tiny$Utils$Direction[Direction.YP.ordinal()] = 4;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$tco$tiny$Utils$Direction[Direction.ZN.ordinal()] = 5;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$tco$tiny$Utils$Direction[Direction.ZP.ordinal()] = 6;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}

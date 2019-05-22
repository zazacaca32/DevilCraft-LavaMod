package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.blocks.BlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.METileEntityBase;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTradeBase$NamelessClass172032532;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityBlockTradeBase extends METileEntityBase {

   public ForgeDirection orientation;
   public byte spin;
   public boolean hasPower = true;
   public String Owner;
   public short contrX;
   public short contrY;
   public short contrZ;


   public boolean rewriteController(int xCoord, int yCoord, int zCoord) {
      boolean flag = true;
      if(this.contrX == 0 && this.contrY == 0 && this.contrZ == 0) {
         flag = false;
      }

      if(this.contrX == (short)xCoord && this.contrY == (short)yCoord && this.contrZ == (short)zCoord) {
         this.contrX = 0;
         this.contrY = 0;
         this.contrZ = 0;
         return true;
      } else {
         this.contrX = (short)xCoord;
         this.contrY = (short)yCoord;
         this.contrZ = (short)zCoord;
         return flag;
      }
   }

   public void setFacing(int chestFacing) {}

   public boolean isUseableByPlayer(EntityPlayer entityplayer) {
      return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this?false:entityplayer.getDistanceSq((double)super.xCoord + 0.5D, (double)super.yCoord + 0.5D, (double)super.zCoord + 0.5D) <= 64.0D;
   }

   public byte getAERotationFromDirection(ForgeDirection rotation) {
      if(rotation == null) {
         return (byte)0;
      } else {
         switch(TileEntityBlockTradeBase$NamelessClass172032532.$SwitchMap$net$minecraftforge$common$ForgeDirection[rotation.ordinal()]) {
         case 1:
            return (byte)0;
         case 2:
            return (byte)1;
         case 3:
            return (byte)2;
         case 4:
            return (byte)3;
         case 5:
            return (byte)4;
         case 6:
            return (byte)5;
         default:
            return (byte)-1;
         }
      }
   }

   public ForgeDirection getDirectionFromAERotation(byte rotation) {
      switch(rotation) {
      case 0:
         return ForgeDirection.SOUTH;
      case 1:
         return ForgeDirection.WEST;
      case 2:
         return ForgeDirection.NORTH;
      case 3:
         return ForgeDirection.EAST;
      case 4:
         return ForgeDirection.UP;
      case 5:
         return ForgeDirection.DOWN;
      default:
         return ForgeDirection.UNKNOWN;
      }
   }

   public void placedBy(EntityLiving entityliving) {
      byte rotation = (byte)(MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3);
      if(entityliving.rotationPitch > 65.0F) {
         this.spin = rotation;
         rotation = 2;
      } else if(entityliving.rotationPitch < -65.0F) {
         this.spin = rotation;
         rotation = 2;
      }

      this.orientation = this.getDirectionFromAERotation(rotation);
      this.Owner = entityliving.getEntityName();
   }

   public void readFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.contrX = nbttagcompound.getShort("coX");
      this.contrY = nbttagcompound.getShort("coY");
      this.contrZ = nbttagcompound.getShort("coZ");
      this.spin = nbttagcompound.getByte("cpin");
      this.orientation = this.getDirectionFromAERotation(nbttagcompound.getByte("orient"));
      this.hasPower = nbttagcompound.getBoolean("hp");
      this.Owner = nbttagcompound.getString("ow");
   }

   public void writeToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
      nbttagcompound.setShort("coX", this.contrX);
      nbttagcompound.setShort("coY", this.contrY);
      nbttagcompound.setShort("coZ", this.contrZ);
      nbttagcompound.setByte("cpin", this.spin);
      nbttagcompound.setByte("orient", this.getAERotationFromDirection(this.orientation));
      nbttagcompound.setBoolean("hp", this.hasPower);
      if(!nbttagcompound.getString("ow").isEmpty()) {
         nbttagcompound.setString("ow", this.Owner);
      }

   }

   public int getSpin() {
      return this.spin;
   }

   public ForgeDirection getOrientation() {
      return this.orientation;
   }

   @SideOnly(Side.CLIENT)
   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
      renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      renderer.renderStandardBlock(block, x, y, z);
      byte bn = 15;
      Tessellator.instance.setColorOpaque_F(1.0F, 1.0F, 1.0F);
      Tessellator.instance.setBrightness(bn << 20 | bn << 4);
      this.renderFace(block, renderer, this.orientation);
      return true;
   }

   @SideOnly(Side.CLIENT)
   public static boolean defaultRenderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
      block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      renderer.setRenderBoundsFromBlock(block);
      return renderer.renderStandardBlock(block, x, y, z);
   }

   @SideOnly(Side.CLIENT)
   protected void renderFace(Block block, RenderBlocks renderer, ForgeDirection orientation) {
      Icon ico = this.defTextures(orientation);
      switch(TileEntityBlockTradeBase$NamelessClass172032532.$SwitchMap$net$minecraftforge$common$ForgeDirection[orientation.ordinal()]) {
      case 1:
         renderer.renderFaceZPos(block, (double)super.xCoord, (double)super.yCoord, (double)super.zCoord, ico);
         break;
      case 2:
         renderer.renderFaceXNeg(block, (double)super.xCoord, (double)super.yCoord, (double)super.zCoord, ico);
         break;
      case 3:
         renderer.renderFaceZNeg(block, (double)super.xCoord, (double)super.yCoord, (double)super.zCoord, ico);
         break;
      case 4:
         renderer.renderFaceXPos(block, (double)super.xCoord, (double)super.yCoord, (double)super.zCoord, ico);
         break;
      case 5:
         renderer.renderFaceYPos(block, (double)super.xCoord, (double)super.yCoord, (double)super.zCoord, ico);
         break;
      case 6:
         renderer.renderFaceYNeg(block, (double)super.xCoord, (double)super.yCoord, (double)super.zCoord, ico);
      }

   }

   @SideOnly(Side.CLIENT)
   private Icon defTextures(ForgeDirection side) {
      if(side == ForgeDirection.UP) {
         if(this.spin == 0) {
            return ((BlockTrader)this.getBlockType()).icon[2];
         }

         if(this.spin == 1) {
            return ((BlockTrader)this.getBlockType()).icon[6];
         }

         if(this.spin == 2) {
            return ((BlockTrader)this.getBlockType()).icon[7];
         }

         if(this.spin == 3) {
            return ((BlockTrader)this.getBlockType()).icon[8];
         }
      }

      if(side == ForgeDirection.DOWN) {
         if(this.spin == 0) {
            return ((BlockTrader)this.getBlockType()).icon[2];
         }

         if(this.spin == 1) {
            return ((BlockTrader)this.getBlockType()).icon[6];
         }

         if(this.spin == 2) {
            return ((BlockTrader)this.getBlockType()).icon[7];
         }

         if(this.spin == 3) {
            return ((BlockTrader)this.getBlockType()).icon[8];
         }
      }

      return ((BlockTrader)this.getBlockType()).icon[2];
   }

   public abstract void UpdateController(TileEntityBlockTraderController var1);

   public abstract LAItemStack getItem();

   public abstract LAItemStack getItemSale();

   static class NamelessClass1070647378 {

      static final int[] $SwitchMap$net$minecraftforge$common$ForgeDirection = new int[ForgeDirection.values().length];


      static {
         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.SOUTH.ordinal()] = 1;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.WEST.ordinal()] = 2;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.NORTH.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.EAST.ordinal()] = 4;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.UP.ordinal()] = 5;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$net$minecraftforge$common$ForgeDirection[ForgeDirection.DOWN.ordinal()] = 6;
         } catch (NoSuchFieldError var1) {
            ;
         }

      }
   }
}

package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.blocks.models.BaseModelBlock;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

public abstract class BaseTileEntityBlock extends TileEntity {

   public BaseModelBlock model;
   private int facing;
   public int RecipesIndex;


   @SideOnly(Side.CLIENT)
   public AxisAlignedBB getRenderBoundingBox() {
      AxisAlignedBB bb = TileEntity.INFINITE_EXTENT_AABB;
      bb = AxisAlignedBB.getAABBPool().getAABB((double)(super.xCoord - 3), (double)super.yCoord, (double)(super.zCoord - 3), (double)(super.xCoord + 3), (double)(super.yCoord + 8), (double)(super.zCoord + 3));
      return bb;
   }

   public int getFacing() {
      return this.facing;
   }

   public boolean receiveClientEvent(int i, int j) {
      if(i == 3) {
         this.facing = j;
      }

      return true;
   }

   public void setFacing(int chestFacing) {
      this.facing = chestFacing;
   }

   public void readFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.facing = nbttagcompound.getInteger("facing");
   }

   public void writeToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
      nbttagcompound.setInteger("facing", this.facing);
   }

   public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
      this.readFromNBT(pkt.customParam1);
   }

   public Packet getDescriptionPacket() {
      NBTTagCompound data = new NBTTagCompound();
      this.writeToNBT(data);
      Packet132TileEntityData packet = new Packet132TileEntityData(super.xCoord, super.yCoord, super.zCoord, 0, data);
      return packet;
   }

   public abstract BaseModelBlock newRenderModel(int var1);

   public void placedBy(EntityLiving entityliving) {}

   public int getSpin() {
      return 0;
   }

   public ForgeDirection getOrientation() {
      return ForgeDirection.NORTH;
   }
}

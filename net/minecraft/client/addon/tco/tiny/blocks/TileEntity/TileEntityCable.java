package net.minecraft.client.addon.tco.tiny.blocks.TileEntity;

import net.minecraft.client.addon.tco.tiny.IDarkNode;
import net.minecraft.client.addon.tco.tiny.Utils.DarkNode;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCable extends TileEntity implements IDarkNode {

   boolean isInit = false;
   public short cableType = 1;


   public void validate() {
      super.validate();
      if(!this.isInit) {
         if(!super.worldObj.isRemote) {
            DarkNode.add(super.xCoord, super.yCoord, super.zCoord, this);
         }

         this.isInit = true;
      }

   }

   public void invalidate() {
      super.invalidate();
      if(this.isInit && !super.worldObj.isRemote) {
         DarkNode.remove(super.xCoord, super.yCoord, super.zCoord, this.getIDNode());
      }

   }

   public boolean shouldRefresh(int oldID, int newID, int oldMeta, int newMeta, World world, int x, int y, int z) {
      return oldID != newID;
   }

   public boolean canUpdate() {
      return false;
   }

   public boolean canInteractWith(TileEntity te) {
      return te instanceof TileEntityCable?this.canInteractWithCable((TileEntityCable)te):te instanceof IDarkNode;
   }

   public boolean canInteractWithCable(TileEntityCable cable) {
      return true;
   }

   public float getCableThickness() {
      return getCableThickness(this.cableType);
   }

   public static float getCableThickness(int cableType) {
      float p = 1.0F;
      switch(cableType) {
      case 0:
         p = 6.0F;
         break;
      case 1:
         p = 4.0F;
         break;
      case 2:
         p = 3.0F;
         break;
      case 3:
         p = 5.0F;
         break;
      case 4:
         p = 6.0F;
         break;
      case 5:
         p = 6.0F;
         break;
      case 6:
         p = 8.0F;
         break;
      case 7:
         p = 10.0F;
         break;
      case 8:
         p = 12.0F;
         break;
      case 9:
         p = 4.0F;
         break;
      case 10:
         p = 5.0F;
         break;
      case 11:
         p = 8.0F;
         break;
      case 12:
         p = 8.0F;
         break;
      case 13:
         p = 16.0F;
      }

      return p / 16.0F;
   }

   public double getConductionLoss() {
      switch(this.cableType) {
      case 0:
         return 0.2D;
      case 1:
         return 0.3D;
      default:
         return 0.025D;
      }
   }

   public int getIDNode() {
      return 0;
   }

   public void update(TileEntityBlockDarkEnergyControler controller) {}

   public void stop(TileEntityBlockDarkEnergyControler controller) {}
}

package codechicken.core.alg;

import codechicken.core.vec.BlockCoord;
import net.minecraft.nbt.NBTTagCompound;

public class SquareSpiral {

   private int x = 0;
   private int z = 0;
   private int side = 0;
   private int sidelenth = 1;
   private int iside = 0;
   private int i = 0;
   private int imax;


   public SquareSpiral() {
      this.imax = Integer.MAX_VALUE;
   }

   public SquareSpiral(int squareSize) {
      this.imax = squareSize * squareSize;
   }

   public SquareSpiral(NBTTagCompound tag) {
      this.x = tag.getInteger("x");
      this.z = tag.getInteger("z");
      this.i = tag.getInteger("i");
      this.side = tag.getInteger("side");
      this.sidelenth = tag.getInteger("sidelenth");
      this.iside = tag.getInteger("iside");
      this.imax = tag.getInteger("imax");
   }

   public BlockCoord next() {
      BlockCoord r = new BlockCoord(this.x, 0, this.z);
      ++this.i;
      ++this.iside;
      switch(this.side) {
      case 0:
         ++this.x;
         break;
      case 1:
         ++this.z;
         break;
      case 2:
         --this.x;
         break;
      case 3:
         --this.z;
      }

      if(this.iside == this.sidelenth) {
         this.side = (this.side + 1) % 4;
         if(this.side % 2 == 0) {
            ++this.sidelenth;
         }

         this.iside = 0;
      }

      return r;
   }

   public boolean hasNext() {
      return this.i < this.imax;
   }

   public NBTTagCompound saveTag() {
      NBTTagCompound tag = new NBTTagCompound();
      tag.setInteger("x", this.x);
      tag.setInteger("z", this.z);
      tag.setInteger("i", this.i);
      tag.setInteger("side", this.side);
      tag.setInteger("sidelenth", this.sidelenth);
      tag.setInteger("iside", this.iside);
      tag.setInteger("imax", this.imax);
      return tag;
   }
}

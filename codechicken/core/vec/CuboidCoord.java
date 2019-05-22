package codechicken.core.vec;

import codechicken.core.vec.BlockCoord;
import codechicken.core.vec.Vector3;
import net.minecraft.util.AxisAlignedBB;

public class CuboidCoord {

   public BlockCoord min;
   public BlockCoord max;


   public CuboidCoord(BlockCoord min, BlockCoord max) {
      this.min = min;
      this.max = max;
   }

   public CuboidCoord(BlockCoord coord) {
      this(coord, coord.copy());
   }

   public CuboidCoord(int[] ia) {
      this(ia[0], ia[1], ia[2], ia[3], ia[4], ia[5]);
   }

   public CuboidCoord(int x1, int y1, int z1, int x2, int y2, int z2) {
      this(new BlockCoord(x1, y1, z1), new BlockCoord(x2, y2, z2));
   }

   public void expand(int side, int amount) {
      if(side % 2 == 0) {
         this.min = this.min.offset(side, amount);
      } else {
         this.max = this.max.offset(side, amount);
      }

   }

   public void shrink(int side, int amount) {
      if(side % 2 == 0) {
         this.min = this.min.inset(side, amount);
      } else {
         this.max = this.max.inset(side, amount);
      }

   }

   public int size(int s) {
      switch(s) {
      case 0:
      case 1:
         return this.max.y - this.min.y + 1;
      case 2:
      case 3:
         return this.max.z - this.min.z + 1;
      case 4:
      case 5:
         return this.max.x - this.min.x + 1;
      default:
         return 0;
      }
   }

   public int getVolume() {
      return (this.max.x - this.min.x + 1) * (this.max.y - this.min.y + 1) * (this.max.z - this.min.z + 1);
   }

   public Vector3 getCenterVec() {
      return new Vector3((double)this.min.x + (double)(this.max.x - this.min.x + 1) / 2.0D, (double)this.min.y + (double)(this.max.y - this.min.y + 1) / 2.0D, (double)this.min.z + (double)(this.max.z - this.min.z + 1) / 2.0D);
   }

   public BlockCoord getCenter(BlockCoord store) {
      store.set(this.min.x + (this.max.x - this.min.x) / 2, this.min.y + (this.max.y - this.min.y) / 2, this.min.z + (this.max.z - this.min.z) / 2);
      return store;
   }

   public boolean contains(BlockCoord coord) {
      return this.contains(coord.x, coord.y, coord.z);
   }

   public boolean contains(int x, int y, int z) {
      return x >= this.min.x && x <= this.max.x && y >= this.min.y && y <= this.max.y && z >= this.min.z && z <= this.max.z;
   }

   public int[] intArray() {
      return new int[]{this.min.x, this.min.y, this.min.z, this.max.x, this.max.y, this.max.z};
   }

   public CuboidCoord copy() {
      return new CuboidCoord(this.min.copy(), this.max.copy());
   }

   public AxisAlignedBB toAABB() {
      return AxisAlignedBB.getBoundingBox((double)this.min.x, (double)this.min.y, (double)this.min.z, (double)(this.max.x + 1), (double)(this.max.y + 1), (double)(this.max.z + 1));
   }

   public void set(BlockCoord min, BlockCoord max) {
      this.min = min;
      this.max = max;
   }
}

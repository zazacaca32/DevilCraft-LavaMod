package codechicken.core.vec;

import codechicken.core.alg.MathHelper;
import codechicken.core.vec.Vector3;
import net.minecraft.tileentity.TileEntity;

public class BlockCoord implements Comparable {

   public int x;
   public int y;
   public int z;
   public static final BlockCoord[] sideOffsets = new BlockCoord[]{new BlockCoord(0, -1, 0), new BlockCoord(0, 1, 0), new BlockCoord(0, 0, -1), new BlockCoord(0, 0, 1), new BlockCoord(-1, 0, 0), new BlockCoord(1, 0, 0)};


   public BlockCoord(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public BlockCoord(Vector3 v) {
      this(MathHelper.floor_double(v.x), MathHelper.floor_double(v.y), MathHelper.floor_double(v.z));
   }

   public BlockCoord(TileEntity tile) {
      this(tile.xCoord, tile.yCoord, tile.zCoord);
   }

   public BlockCoord(int[] ia) {
      this(ia[0], ia[1], ia[2]);
   }

   public BlockCoord() {}

   public boolean equals(Object obj) {
      if(!(obj instanceof BlockCoord)) {
         return false;
      } else {
         BlockCoord o2 = (BlockCoord)obj;
         return this.x == o2.x && this.y == o2.y && this.z == o2.z;
      }
   }

   public int hashCode() {
      return (this.x ^ this.z) * 31 + this.y;
   }

   public int compareTo(BlockCoord o) {
      return this.x != o.x?(this.x < o.x?1:-1):(this.y != o.y?(this.y < o.y?1:-1):(this.z != o.z?(this.z < o.z?1:-1):0));
   }

   public Vector3 toVector3Centered() {
      return new Vector3((double)this.x + 0.5D, (double)this.y + 0.5D, (double)this.z + 0.5D);
   }

   public BlockCoord multiply(int i) {
      this.x *= i;
      this.y *= i;
      this.z *= i;
      return this;
   }

   public double mag_() {
      return Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
   }

   public int mag2() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public boolean isZero() {
      return this.x == 0 && this.y == 0 && this.z == 0;
   }

   public boolean isAxial() {
      return this.x == 0?this.y == 0 || this.z == 0:this.y == 0 && this.z == 0;
   }

   public BlockCoord add(BlockCoord coord2) {
      this.x += coord2.x;
      this.y += coord2.y;
      this.z += coord2.z;
      return this;
   }

   public BlockCoord add(int i, int j, int k) {
      this.x += i;
      this.y += j;
      this.z += k;
      return this;
   }

   public BlockCoord sub(BlockCoord coord2) {
      this.x -= coord2.x;
      this.y -= coord2.y;
      this.z -= coord2.z;
      return this;
   }

   public BlockCoord sub(int i, int j, int k) {
      this.x -= i;
      this.y -= j;
      this.z -= k;
      return this;
   }

   public BlockCoord offset(int side) {
      return this.offset(side, 1);
   }

   public BlockCoord offset(int side, int amount) {
      BlockCoord offset = sideOffsets[side];
      this.x += offset.x * amount;
      this.y += offset.y * amount;
      this.z += offset.z * amount;
      return this;
   }

   public BlockCoord inset(int side) {
      return this.inset(side, 1);
   }

   public BlockCoord inset(int side, int amount) {
      return this.offset(side, -amount);
   }

   public int[] intArray() {
      return new int[]{this.x, this.y, this.z};
   }

   public BlockCoord copy() {
      return new BlockCoord(this.x, this.y, this.z);
   }

   public BlockCoord set(int i, int j, int k) {
      this.x = i;
      this.y = j;
      this.z = k;
      return this;
   }

   public BlockCoord set(BlockCoord coord) {
      return this.set(coord.x, coord.y, coord.z);
   }

@Override
public int compareTo(Object o) {
	// TODO Auto-generated method stub
	return 0;
}
}

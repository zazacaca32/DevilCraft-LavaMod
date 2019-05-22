package codechicken.core.vec;

import codechicken.core.vec.Transformation;
import codechicken.core.vec.Vector3;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;

public class Cuboid6 {

   public static Cuboid6 full = new Cuboid6(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
   public Vector3 min;
   public Vector3 max;


   public Cuboid6(Vector3 min, Vector3 max) {
      this.min = min;
      this.max = max;
   }

   public Cuboid6(AxisAlignedBB aabb) {
      this.min = new Vector3(aabb.minX, aabb.minY, aabb.minZ);
      this.max = new Vector3(aabb.maxX, aabb.maxY, aabb.maxZ);
   }

   public Cuboid6(Cuboid6 cuboid) {
      this.min = cuboid.min.copy();
      this.max = cuboid.max.copy();
   }

   public Cuboid6(double minx, double miny, double minz, double maxx, double maxy, double maxz) {
      this.min = new Vector3(minx, miny, minz);
      this.max = new Vector3(maxx, maxy, maxz);
   }

   public AxisAlignedBB toAABB() {
      return AxisAlignedBB.getBoundingBox(this.min.x, this.min.y, this.min.z, this.max.x, this.max.y, this.max.z);
   }

   public Cuboid6 copy() {
      return new Cuboid6(this);
   }

   public Cuboid6 add(Vector3 vec) {
      this.min.add(vec);
      this.max.add(vec);
      return this;
   }

   public Cuboid6 sub(Vector3 vec) {
      this.min.subtract(vec);
      this.max.subtract(vec);
      return this;
   }

   public void setBlockBounds(Block block) {
      block.setBlockBounds((float)this.min.x, (float)this.min.y, (float)this.min.z, (float)this.max.x, (float)this.max.y, (float)this.max.z);
   }

   public boolean intersects(Cuboid6 b) {
      return this.max.x - 1.0E-5D > b.min.x && b.max.x - 1.0E-5D > this.min.x && this.max.y - 1.0E-5D > b.min.y && b.max.y - 1.0E-5D > this.min.y && this.max.z - 1.0E-5D > b.min.z && b.max.z - 1.0E-5D > this.min.z;
   }

   public Cuboid6 offset(Cuboid6 o) {
      this.min.add(o.min);
      this.max.add(o.max);
      return this;
   }

   public Vector3 center() {
      return this.min.copy().add(this.max).multiply(0.5D);
   }

   public static boolean intersects(Cuboid6 a, Cuboid6 b) {
      return a != null && b != null && a.intersects(b);
   }

   public String toString() {
      MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
      return "Cuboid: (" + new BigDecimal(this.min.x, cont) + ", " + new BigDecimal(this.min.y, cont) + ", " + new BigDecimal(this.min.z, cont) + ") -> (" + new BigDecimal(this.max.x, cont) + ", " + new BigDecimal(this.max.y, cont) + ", " + new BigDecimal(this.max.z, cont) + ")";
   }

   public Cuboid6 enclose(Vector3 vec) {
      if(this.min.x > vec.x) {
         this.min.x = vec.x;
      }

      if(this.min.y > vec.y) {
         this.min.y = vec.y;
      }

      if(this.min.z > vec.z) {
         this.min.z = vec.z;
      }

      if(this.max.x < vec.x) {
         this.max.x = vec.x;
      }

      if(this.max.y < vec.y) {
         this.max.y = vec.y;
      }

      if(this.max.z < vec.z) {
         this.max.z = vec.z;
      }

      return this;
   }

   public Cuboid6 enclose(Cuboid6 c) {
      if(this.min.x > c.min.x) {
         this.min.x = c.min.x;
      }

      if(this.min.y > c.min.y) {
         this.min.y = c.min.y;
      }

      if(this.min.z > c.min.z) {
         this.min.z = c.min.z;
      }

      if(this.max.x < c.max.x) {
         this.max.x = c.max.x;
      }

      if(this.max.y < c.max.y) {
         this.max.y = c.max.y;
      }

      if(this.max.z < c.max.z) {
         this.max.z = c.max.z;
      }

      return this;
   }

   public Cuboid6 transform(Transformation t) {
      t.apply(this.min);
      t.apply(this.max);
      double temp;
      if(this.min.x > this.max.x) {
         temp = this.min.x;
         this.min.x = this.max.x;
         this.max.x = temp;
      }

      if(this.min.y > this.max.y) {
         temp = this.min.y;
         this.min.y = this.max.y;
         this.max.y = temp;
      }

      if(this.min.z > this.max.z) {
         temp = this.min.z;
         this.min.z = this.max.z;
         this.max.z = temp;
      }

      return this;
   }
}

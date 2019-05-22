package codechicken.core.vec;

import codechicken.core.alg.MathHelper;
import codechicken.core.vec.Quat;
import codechicken.core.vec.Transformation;
import codechicken.core.vec.Translation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Vector3 {

   public static Vector3 zero = new Vector3();
   public static Vector3 one = new Vector3(1.0D, 1.0D, 1.0D);
   public static Vector3 center = new Vector3(0.5D, 0.5D, 0.5D);
   public double x;
   public double y;
   public double z;


   public Vector3() {}

   public Vector3(double d, double d1, double d2) {
      this.x = d;
      this.y = d1;
      this.z = d2;
   }

   public Vector3(Vector3 vec) {
      this.x = vec.x;
      this.y = vec.y;
      this.z = vec.z;
   }

   public Vector3(Vec3 vec) {
      this.x = vec.xCoord;
      this.y = vec.yCoord;
      this.z = vec.zCoord;
   }

   public Vector3 copy() {
      return new Vector3(this);
   }

   public static Vector3 fromEntity(Entity e) {
      return new Vector3(e.posX, e.posY, e.posZ);
   }

   public static Vector3 fromEntityCenter(Entity e) {
      return new Vector3(e.posX, e.posY - (double)e.yOffset + (double)(e.height / 2.0F), e.posZ);
   }

   public static Vector3 fromTileEntity(TileEntity e) {
      return new Vector3((double)e.xCoord, (double)e.yCoord, (double)e.zCoord);
   }

   public static Vector3 fromTileEntityCenter(TileEntity e) {
      return new Vector3((double)e.xCoord + 0.5D, (double)e.yCoord + 0.5D, (double)e.zCoord + 0.5D);
   }

   @Deprecated
   public static Vector3 fromVec3D(Vec3 vec) {
      return new Vector3(vec);
   }

   public Vector3 set(double d, double d1, double d2) {
      this.x = d;
      this.y = d1;
      this.z = d2;
      return this;
   }

   public Vector3 set(Vector3 vec) {
      this.x = vec.x;
      this.y = vec.y;
      this.z = vec.z;
      return this;
   }

   public double dotProduct(Vector3 vec) {
      double d = vec.x * this.x + vec.y * this.y + vec.z * this.z;
      if(d > 1.0D && d < 1.00001D) {
         d = 1.0D;
      } else if(d < -1.0D && d > -1.00001D) {
         d = -1.0D;
      }

      return d;
   }

   public double dotProduct(double d, double d1, double d2) {
      return d * this.x + d1 * this.y + d2 * this.z;
   }

   public Vector3 crossProduct(Vector3 vec) {
      double d = this.y * vec.z - this.z * vec.y;
      double d1 = this.z * vec.x - this.x * vec.z;
      double d2 = this.x * vec.y - this.y * vec.x;
      this.x = d;
      this.y = d1;
      this.z = d2;
      return this;
   }

   public Vector3 add(double d, double d1, double d2) {
      this.x += d;
      this.y += d1;
      this.z += d2;
      return this;
   }

   public Vector3 add(Vector3 vec) {
      this.x += vec.x;
      this.y += vec.y;
      this.z += vec.z;
      return this;
   }

   public Vector3 add(double d) {
      return this.add(d, d, d);
   }

   public Vector3 sub(Vector3 vec) {
      return this.subtract(vec);
   }

   public Vector3 subtract(Vector3 vec) {
      this.x -= vec.x;
      this.y -= vec.y;
      this.z -= vec.z;
      return this;
   }

   public Vector3 negate(Vector3 vec) {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
      return this;
   }

   public Vector3 multiply(double d) {
      this.x *= d;
      this.y *= d;
      this.z *= d;
      return this;
   }

   public Vector3 multiply(Vector3 f) {
      this.x *= f.x;
      this.y *= f.y;
      this.z *= f.z;
      return this;
   }

   public Vector3 multiply(double fx, double fy, double fz) {
      this.x *= fx;
      this.y *= fy;
      this.z *= fz;
      return this;
   }

   public double mag() {
      return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
   }

   public double magSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public Vector3 normalize() {
      double d = this.mag();
      if(d != 0.0D) {
         this.multiply(1.0D / d);
      }

      return this;
   }

   public String toString() {
      MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
      return "Vector3(" + new BigDecimal(this.x, cont) + ", " + new BigDecimal(this.y, cont) + ", " + new BigDecimal(this.z, cont) + ")";
   }

   public Vector3 perpendicular() {
      return this.z == 0.0D?this.zCrossProduct():this.xCrossProduct();
   }

   public Vector3 xCrossProduct() {
      double d = this.z;
      double d1 = -this.y;
      this.x = 0.0D;
      this.y = d;
      this.z = d1;
      return this;
   }

   public Vector3 zCrossProduct() {
      double d = this.y;
      double d1 = -this.x;
      this.x = d;
      this.y = d1;
      this.z = 0.0D;
      return this;
   }

   public Vector3 yCrossProduct() {
      double d = -this.z;
      double d1 = this.x;
      this.x = d;
      this.y = 0.0D;
      this.z = d1;
      return this;
   }

   public Vector3 rotate(double angle, Vector3 axis) {
      Quat.aroundAxis(axis.copy().normalize(), angle).rotate(this);
      return this;
   }

   public Vector3 rotate(Quat rotator) {
      rotator.rotate(this);
      return this;
   }

   public Vec3 toVec3D() {
      return Vec3.createVectorHelper(this.x, this.y, this.z);
   }

   public double angle(Vector3 vec) {
      return Math.acos(this.copy().normalize().dotProduct(vec.copy().normalize()));
   }

   public boolean isZero() {
      return this.x == 0.0D && this.y == 0.0D && this.z == 0.0D;
   }

   public boolean isAxial() {
      return this.x == 0.0D?this.y == 0.0D || this.z == 0.0D:this.y == 0.0D && this.z == 0.0D;
   }

   @SideOnly(Side.CLIENT)
   public Vector3f vector3f() {
      return new Vector3f((float)this.x, (float)this.y, (float)this.z);
   }

   @SideOnly(Side.CLIENT)
   public Vector4f vector4f() {
      return new Vector4f((float)this.x, (float)this.y, (float)this.z, 1.0F);
   }

   public Vector3 YZintercept(Vector3 end, double px) {
      double dx = end.x - this.x;
      double dy = end.y - this.y;
      double dz = end.z - this.z;
      if(dx == 0.0D) {
         return null;
      } else {
         double d = (px - this.x) / dx;
         if(MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
            return this;
         } else if(!MathHelper.between(0.0D, d, 1.0D)) {
            return null;
         } else {
            this.x = px;
            this.y += d * dy;
            this.z += d * dz;
            return this;
         }
      }
   }

   public Vector3 XZintercept(Vector3 end, double py) {
      double dx = end.x - this.x;
      double dy = end.y - this.y;
      double dz = end.z - this.z;
      if(dy == 0.0D) {
         return null;
      } else {
         double d = (py - this.y) / dy;
         if(MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
            return this;
         } else if(!MathHelper.between(0.0D, d, 1.0D)) {
            return null;
         } else {
            this.x += d * dx;
            this.y = py;
            this.z += d * dz;
            return this;
         }
      }
   }

   public Vector3 XYintercept(Vector3 end, double pz) {
      double dx = end.x - this.x;
      double dy = end.y - this.y;
      double dz = end.z - this.z;
      if(dz == 0.0D) {
         return null;
      } else {
         double d = (pz - this.z) / dz;
         if(MathHelper.between(-1.0E-5D, d, 1.0E-5D)) {
            return this;
         } else if(!MathHelper.between(0.0D, d, 1.0D)) {
            return null;
         } else {
            this.x += d * dx;
            this.y += d * dy;
            this.z = pz;
            return this;
         }
      }
   }

   public Vector3 negate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
      return this;
   }

   public Translation translation() {
      return new Translation(this);
   }

   public double scalarProject(Vector3 b) {
      double l = b.mag();
      return l == 0.0D?0.0D:this.dotProduct(b) / l;
   }

   public Vector3 project(Vector3 b) {
      double l = b.magSquared();
      if(l == 0.0D) {
         this.set(0.0D, 0.0D, 0.0D);
         return this;
      } else {
         double m = this.dotProduct(b) / l;
         this.set(b).multiply(m);
         return this;
      }
   }

   public boolean equals(Object o) {
      if(!(o instanceof Vector3)) {
         return false;
      } else {
         Vector3 v = (Vector3)o;
         return this.x == v.x && this.y == v.y && this.z == v.z;
      }
   }

   public boolean equalsT(Vector3 v) {
      return MathHelper.between(this.x - 1.0E-5D, v.x, this.x + 1.0E-5D) && MathHelper.between(this.y - 1.0E-5D, v.y, this.y + 1.0E-5D) && MathHelper.between(this.z - 1.0E-5D, v.z, this.z + 1.0E-5D);
   }

   public Vector3 apply(Transformation t) {
      t.apply(this);
      return this;
   }
}

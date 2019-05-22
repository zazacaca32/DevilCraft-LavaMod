package codechicken.core.raytracer;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class ExtendedMOP extends MovingObjectPosition {

   public Object data;


   public ExtendedMOP(Entity entity, Object data) {
      super(entity);
      this.setData(data);
   }

   public ExtendedMOP(int x, int y, int z, int side, Vec3 hit, Object data) {
      super(x, y, z, side, hit);
      this.setData(data);
   }

   public ExtendedMOP(MovingObjectPosition mop, Object data) {
      super(0, 0, 0, 0, mop.hitVec);
      super.typeOfHit = mop.typeOfHit;
      super.blockX = mop.blockX;
      super.blockY = mop.blockY;
      super.blockZ = mop.blockZ;
      super.sideHit = mop.sideHit;
      super.subHit = mop.subHit;
      this.setData(data);
   }

   public void setData(Object data) {
      if(data instanceof Integer) {
         super.subHit = ((Integer)data).intValue();
      }

      this.data = data;
   }

   public static Object getData(MovingObjectPosition mop) {
      return mop instanceof ExtendedMOP?((ExtendedMOP)mop).data:Integer.valueOf(mop.subHit);
   }
}

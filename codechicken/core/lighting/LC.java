package codechicken.core.lighting;

import codechicken.core.render.CCModel;
import codechicken.core.vec.Rotation;
import codechicken.core.vec.Vector3;

public class LC {

   public int side;
   public float fa;
   public float fb;
   public float fc;
   public float fd;


   public LC(int s, float e, float f, float g, float h) {
      this.side = s;
      this.fa = e;
      this.fb = f;
      this.fc = g;
      this.fd = h;
   }

   public static LC compute(Vector3 vec, Vector3 normal) {
      int side = CCModel.findSide(normal);
      return side < 0?new LC(12, 1.0F, 0.0F, 0.0F, 0.0F):compute(vec, side);
   }

   public static LC compute(Vector3 vec, int side) {
      boolean offset = false;
      switch(side) {
      case 0:
         offset = vec.y <= 0.0D;
         break;
      case 1:
         offset = vec.y >= 1.0D;
         break;
      case 2:
         offset = vec.z <= 0.0D;
         break;
      case 3:
         offset = vec.z >= 1.0D;
         break;
      case 4:
         offset = vec.x <= 0.0D;
         break;
      case 5:
         offset = vec.x >= 1.0D;
      }

      if(!offset) {
         side += 6;
      }

      return computeO(vec, side);
   }

   public static LC computeO(Vector3 vec, int side) {
      Vector3 v1 = Rotation.axes[((side & 14) + 3) % 6];
      Vector3 v2 = Rotation.axes[((side & 14) + 5) % 6];
      float d1 = (float)vec.scalarProject(v1);
      float d2 = 1.0F - d1;
      float d3 = (float)vec.scalarProject(v2);
      float d4 = 1.0F - d3;
      return new LC(side, d2 * d4, d2 * d3, d1 * d4, d1 * d3);
   }
}
